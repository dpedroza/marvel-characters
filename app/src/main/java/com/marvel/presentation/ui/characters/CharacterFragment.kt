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
import com.marvel.presentation.ui.MainActivity
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
        val application = (activity?.application as MarvelApplication)
        application.component.presentationComponent().inject(this)
        setupPresenter()
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    private fun setupPresenter() {
        presenter.attach(this)
        presenter.loadCharacters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val spanCount = 2
        val adapter = CharacterAdapter()
        val layoutManager = GridLayoutManager(context, spanCount)
        charactersRecyclerView.layoutManager = layoutManager
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

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showLoading() {
        val activity = activity as MainActivity
        activity.showLoading()
    }

    override fun hideLoading() {
        val activity = activity as MainActivity
        activity.hideLoading()
    }

    override fun showCharacters(characters: List<CharacterViewObject>) {
        val adapter = charactersRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters)
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, getString(R.string.heroes), Toast.LENGTH_SHORT).show()
    }
}
