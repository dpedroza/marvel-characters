package com.marvel.presentation.ui.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.presentation.application.MarvelApplication
import com.marvel.presentation.model.CharacterViewObject
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DetailContract.View, AppCompatActivity() {

    @Inject
    lateinit var presenter: DetailPresenter
    private lateinit var character: CharacterViewObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupDependencyInjection()
        presenter.attach(this)
        character = intent.getParcelableExtra(CHARACTER) as CharacterViewObject
        setupToolbar()
        setupImage()
        setupFab()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMessage(messageId: Int) {
        fab.setImageDrawable(getStarDrawable())
        Toast.makeText(this, getString(messageId, character.name), Toast.LENGTH_SHORT).show()
    }

    private fun setupImage() {
        Glide.with(this)
            .load(character.bannerURL)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .centerCrop()
            .into(collapsingImageView)
    }

    private fun setupToolbar() {

        descriptionTextView.text = character.description
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        collapsingToolbar.title = character.name
        collapsingToolbar.setExpandedTitleColor(Color.WHITE)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun setupFab() {
        fab.setImageDrawable(getStarDrawable())
        fab.setOnClickListener {
            presenter.updateFavorite(character)
        }
    }

    private fun getStarDrawable(): Drawable? {
        val favorite = R.drawable.ic_baseline_star_24
        val unpopular = R.drawable.ic_baseline_star_white_24
        return if (character.isFavorite) {
            ContextCompat.getDrawable(this, favorite)
        } else {
            ContextCompat.getDrawable(this, unpopular)
        }
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    companion object {
        const val CHARACTER = "character_param"
    }
}