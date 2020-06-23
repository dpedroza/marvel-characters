package com.marvel.presentation.ui.main.characters

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.marvel.R
import com.marvel.presentation.application.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.hideKeyboard
import com.marvel.presentation.ui.detail.DetailActivity
import com.marvel.presentation.ui.main.adapter.CharacterAdapter
import com.marvel.presentation.ui.main.characters.view.DelayedOnQueryTextListener
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharacterFragment : Fragment(), CharactersContract.View {

    @Inject
    lateinit var presenter: CharactersContract.Presenter
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencyInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupSearchView()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.resetPagination()
        adapter.clear()
        presenter.loadCharacters()
    }

    private fun setupPresenter() {
        presenter.attach(this)
    }

    private fun setupSearchView() {

        searchView.setOnQueryTextListener(object : DelayedOnQueryTextListener() {

            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return false
            }

            override fun onDelayerQueryTextChange(query: String?) {
                search(query)
            }
        })
    }

    private fun search(query: String?) {
        adapter.clear()
        presenter.resetPagination()
        presenter.loadCharacters(query)
    }

    private fun setupRecyclerView() {
        val spanCount = requireActivity().resources.getInteger(R.integer.spanCount)
        adapter = CharacterAdapter({ onOpenDetails(it) }, { onFavorite(it) })
        charactersRecyclerView.layoutManager = GridLayoutManager(context, spanCount)
        charactersRecyclerView.setHasFixedSize(true)
        charactersRecyclerView.adapter = adapter
        charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView.requestFocus()
                hideKeyboard()
                if (!charactersRecyclerView.canScrollVertically(1))
                    if (!presenter.isLoading()) {
                        val query = searchView.query as? String
                        presenter.loadCharacters(query)
                    }
            }
        })
    }

    private fun setupSwipeRefreshLayout() {
        val color = requireContext().let { ContextCompat.getColor(it, R.color.colorPrimary) }
        swipeRefreshLayout.setColorSchemeColors(color)
        swipeRefreshLayout.setOnRefreshListener {
            adapter.clear()
            presenter.resetPagination()
            presenter.loadCharacters()
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    private fun onOpenDetails(characterViewObject: CharacterViewObject) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.CHARACTER, characterViewObject)
        startActivity(intent)
    }

    private fun onFavorite(characterViewObject: CharacterViewObject) {
        presenter.onUpdateFavorite(characterViewObject)
        adapter.onUpdateFavorite(characterViewObject)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
        errorImageView.visibility = GONE
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showToast(messageId: Int, name: String) {
        Toast.makeText(context, getString(messageId, name), LENGTH_SHORT).show()
    }

    override fun showCharacters(characters: List<CharacterViewObject>) {
        errorImageView.visibility = GONE
        emptyText.visibility = GONE
        charactersRecyclerView.visibility = VISIBLE
        adapter.updateCharacters(characters)
    }

    override fun showEmptyState() {
        errorImageView.visibility = GONE
        charactersRecyclerView.visibility = GONE
        emptyText.visibility = VISIBLE
    }

    override fun showMessage(messageId: Int) {
        emptyText.visibility = GONE
        charactersRecyclerView.visibility = GONE
        errorImageView.visibility = VISIBLE
        Snackbar.make(
            fragment_character,
            getString(messageId),
            BaseTransientBottomBar.LENGTH_INDEFINITE
        )
            .setActionTextColor(Color.WHITE)
            .setAction(getString(R.string.retry_label)) {
                presenter.loadCharacters()
            }
            .show()
        hideKeyboard()
    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
