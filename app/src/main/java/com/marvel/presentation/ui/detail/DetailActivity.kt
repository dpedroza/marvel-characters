package com.marvel.presentation.ui.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.presentation.application.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_character.view.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DetailActivity: DetailContract.View, AppCompatActivity() {

    @Inject
    lateinit var presenter: DetailPresenter
    lateinit var character: CharacterViewObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupDependencyInjection()
        character = intent.getParcelableExtra(CHARACTER) ?: throw IllegalArgumentException()
        presenter.attach(this)
        setSupportActionBar(toolbar)
        setupCollapsingToolbar()

        Glide.with(this)
            .load(character.bannerURL)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .centerCrop()
            .into(collapsingImageView)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun showMessage(messageId: Int) {
        val favorite = R.drawable.ic_baseline_star_24
        val unpopular = R.drawable.ic_baseline_star_white_24
        val drawable = if (character.isFavorite) {
            ContextCompat.getDrawable(this, favorite)
        } else {
            ContextCompat.getDrawable(this, unpopular)
        }

        fab.setImageDrawable(drawable)
        Toast.makeText(this, getString(messageId, character.name), Toast.LENGTH_SHORT).show()
    }

    private fun setupCollapsingToolbar() {
        collapsingToolbar.title = character.name
        collapsingToolbar.setExpandedTitleColor(Color.WHITE)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

        val favorite = R.drawable.ic_baseline_star_24
        val unpopular = R.drawable.ic_baseline_star_white_24
        val drawable = if (character.isFavorite) {
            ContextCompat.getDrawable(this, favorite)
        } else {
            ContextCompat.getDrawable(this, unpopular)
        }

        fab.setImageDrawable(drawable)

        fab.setOnClickListener {
            presenter.updateFavorite(character)
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    companion object {
        const val CHARACTER = "character_param"
    }
}