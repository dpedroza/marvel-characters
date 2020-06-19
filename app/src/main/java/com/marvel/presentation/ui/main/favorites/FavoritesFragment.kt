package com.marvel.presentation.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.marvel.R
import com.marvel.presentation.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.main.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : Fragment(), FavoritesContract.View {

    @Inject
    lateinit var presenter: FavoritesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencyInjection()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavorites()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupPresenter() {
        presenter.attach(this)
    }

    private fun setupRecyclerView() {
        favoritesRecyclerView.layoutManager = GridLayoutManager(context, 2)
        favoritesRecyclerView.setHasFixedSize(true)
        favoritesRecyclerView.adapter = CharacterAdapter(hideStars = true)
    }

    private fun setupSwipeRefreshLayout() {
        val color = requireContext().let { ContextCompat.getColor(it, R.color.colorPrimary) }
        swipeRefreshLayout.setColorSchemeColors(color)
        swipeRefreshLayout.setOnRefreshListener {
            presenter.loadFavorites()
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showFavorites(characters: List<CharacterViewObject>) {
        emptyText.visibility = GONE
        favoritesRecyclerView.visibility = VISIBLE
        val adapter = favoritesRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters, clearList = true)
    }

    override fun showEmptyState() {
        favoritesRecyclerView.visibility = GONE
        emptyText.visibility = VISIBLE
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, getString(R.string.heroes), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
