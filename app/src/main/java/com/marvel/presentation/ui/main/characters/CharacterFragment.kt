package com.marvel.presentation.ui.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.R
import com.marvel.presentation.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.main.adapter.CharacterAdapter
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
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupPresenter() {
        presenter.attach(this)
        presenter.loadCharacters()
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter(onFavorite = { onFavorite(it) })
        charactersRecyclerView.layoutManager = GridLayoutManager(context, 2)
        charactersRecyclerView.setHasFixedSize(true)
        charactersRecyclerView.adapter = adapter
        charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!charactersRecyclerView.canScrollVertically(1))
                    presenter.loadCharacters()
            }
        })
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.loadCharacters(reset = true)
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    private fun onFavorite(characterViewObject: CharacterViewObject) {
        val isFavorite = characterViewObject.isFavorite.not()
        val name = characterViewObject.name
        val message = if (isFavorite) {
            getString(R.string.favorite_added, name)
        } else {
            getString(R.string.favorite_removed, name)
        }
        characterViewObject.isFavorite = isFavorite

        presenter.updateFavorite(characterViewObject)
        adapter.updateCharacters()

        Toast.makeText(context, message, LENGTH_SHORT).show()
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

    override fun showCharacters(characters: List<CharacterViewObject>, clear: Boolean) {
        adapter.updateCharacters(characters, clear)
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, getString(R.string.heroes), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
