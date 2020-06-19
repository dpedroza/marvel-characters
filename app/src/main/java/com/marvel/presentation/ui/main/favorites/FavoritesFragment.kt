package com.marvel.presentation.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        favoritesRecyclerView.adapter = CharacterAdapter({})
    }

    private fun setupSwipeRefreshLayout() {
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
        val adapter = favoritesRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters)
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, getString(R.string.heroes), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
