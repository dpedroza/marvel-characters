package com.marvel.presentation.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marvel.ApplicationComponent
import com.marvel.MarvelApplication
import com.marvel.R
import com.marvel.presentation.model.CharacterViewObject
import javax.inject.Inject

class CharacterFragment : Fragment(), CharactersContract.View {

    @Inject
    lateinit var presenter: CharacterPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MarvelApplication).applicationComponent.uiComponent().inject(this)
        presenter.attach(this)
        presenter.loadCharacters()
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showCharacters(heroes: List<CharacterViewObject>) {
        TODO("Not yet implemented")
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(context, "Erro Sinistro", Toast.LENGTH_SHORT).show()
    }
}
