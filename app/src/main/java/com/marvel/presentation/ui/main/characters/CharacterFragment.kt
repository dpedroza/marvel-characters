package com.marvel.presentation.ui.main.characters

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.marvel.R
import com.marvel.presentation.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.hideKeyboard
import com.marvel.presentation.ui.main.adapter.CharacterAdapter
import com.marvel.presentation.ui.main.view.DelayedOnQueryTextListener
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharacterFragment : Fragment(), CharactersContract.View {

    @Inject
    lateinit var presenter: CharacterPresenter
    lateinit var adapter: CharacterAdapter

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

    private fun setupPresenter() {
        presenter.attach(this)
        presenter.loadCharacters()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : DelayedOnQueryTextListener() {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    presenter.loadCharacters(query = query, resetAdapter = true)
                }
                return false
            }

            override fun onDelayerQueryTextChange(query: String?) {
                presenter.loadCharacters(query = query, resetAdapter = true)
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter({ onFavorite(it) })
        charactersRecyclerView.layoutManager = GridLayoutManager(context, 2)
        charactersRecyclerView.setHasFixedSize(true)
        charactersRecyclerView.adapter = adapter
        charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView.requestFocus()
                hideKeyboard()
                if (!charactersRecyclerView.canScrollVertically(1))
                    if (!presenter.isLoading) {
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
            presenter.loadCharacters(resetAdapter = true)
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    private fun onFavorite(characterViewObject: CharacterViewObject) {
        presenter.updateFavorite(characterViewObject)
        adapter.updateCharacters()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showToast(messageId: Int, name: String) {
        Toast.makeText(context, getString(messageId, name), LENGTH_SHORT).show()
    }

    override fun showCharacters(characters: List<CharacterViewObject>, clear: Boolean) {
        errorImageView.visibility = GONE
        emptyText.visibility = GONE
        charactersRecyclerView.visibility = VISIBLE
        adapter.updateCharacters(characters, clear)
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
        errorImageView.requestFocus()
        val view = requireActivity().findViewById<View>(R.id.fragment_character)
        val message = getString(messageId)
        val action = getString(R.string.retry_label)
        Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_INDEFINITE)
            .setActionTextColor(Color.WHITE)
            .setAction(action) { presenter.loadCharacters(resetAdapter = true) }
            .show()
        hideKeyboard()
    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
