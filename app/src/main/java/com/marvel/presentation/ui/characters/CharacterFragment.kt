package com.marvel.presentation.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.R
import com.marvel.presentation.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.CharacterAdapter
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharacterFragment : Fragment(), CharactersContract.View {

    @Inject
    lateinit var presenter: CharacterPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    private fun setupPresenter() {
        presenter.attach(this)
        presenter.loadCharacters()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupRecyclerView() {
        charactersRecyclerView.layoutManager = GridLayoutManager(context, 2)
        charactersRecyclerView.setHasFixedSize(true)
        charactersRecyclerView.adapter = CharacterAdapter(
            onFavorite = {
                it.isFavorite = !it.isFavorite
                presenter.updateFavorite(it)
                val adapter = charactersRecyclerView.adapter as CharacterAdapter
                adapter.updateCharacters()
                if (it.isFavorite) {
                    Toast.makeText(context, "${it.name} adicionado aos favoritos", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "${it.name} removido dos favoritos", Toast.LENGTH_SHORT).show()
                }
            }
        )
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
        val adapter = charactersRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters, clear)
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, getString(R.string.heroes), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = CharacterFragment()
    }
}
