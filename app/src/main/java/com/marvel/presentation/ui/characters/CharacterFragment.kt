package com.marvel.presentation.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.marvel.MarvelApplication
import com.marvel.R
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
        val application = (activity?.application as MarvelApplication)
        application.applicationComponent.uiComponent().inject(this)
        presenter.attach(this)
        presenter.loadCharacters()
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersRecyclerView.layoutManager = GridLayoutManager(context, 2)
        charactersRecyclerView.adapter = CharacterAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showLoading() {
//        progressBar.visibility = VISIBLE
    }

    override fun hideLoading() {
//        progressBar.visibility = INVISIBLE
    }

    override fun showCharacters(characters: List<CharacterViewObject>) {
        val adapter = charactersRecyclerView.adapter as CharacterAdapter
        adapter.updateCharacters(characters)
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, "Erro Sinistro", Toast.LENGTH_SHORT).show()
    }
}
