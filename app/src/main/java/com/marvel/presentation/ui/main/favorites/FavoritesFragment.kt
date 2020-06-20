package com.marvel.presentation.ui.main.favorites

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.marvel.R
import com.marvel.presentation.application.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.hideKeyboard
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
        hideKeyboard()
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
    }

    private fun setupPresenter() {
        presenter.attach(this)
    }

    private fun setupRecyclerView() {
        favoritesRecyclerView.layoutManager = GridLayoutManager(context, 2)
        favoritesRecyclerView.setHasFixedSize(true)
        favoritesRecyclerView.adapter = CharacterAdapter(hideStars = true)
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    override fun showFavorites(characters: List<CharacterViewObject>) {
        emptyText.visibility = GONE
        errorImageView.visibility = GONE
        favoritesRecyclerView.visibility = VISIBLE
        val adapter = favoritesRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters, clearList = true)
    }

    override fun showEmptyState() {
        errorImageView.visibility = GONE
        favoritesRecyclerView.visibility = GONE
        emptyText.visibility = VISIBLE
    }

    override fun showMessage(messageId: Int) {
        favoritesRecyclerView.visibility = GONE
        emptyText.visibility = GONE
        errorImageView.visibility = VISIBLE
        activity?.let {
            val view = it.findViewById<View>(R.id.fragment_favorite)
            val message = getString(messageId)
            val action = getString(R.string.retry_label)
            Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.WHITE)
                .setAction(action) { presenter.loadFavorites() }
                .show()
        }
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
