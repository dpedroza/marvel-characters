package com.marvel.presentation.ui.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
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
        setupCharacterInfo()
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
        val drawableId = if (character.isFavorite) {
            favorite
        } else {
            unpopular
        }
        return ContextCompat.getDrawable(this, drawableId)
    }

    private fun setupDependencyInjection() {
        MarvelApplication.component.presentationComponent().inject(this)
    }

    private fun setupCharacterInfo() {
        with(character) {
            if (
                description.isNullOrEmpty() &&
                comics.isNullOrEmpty() &&
                series.isNullOrEmpty()
            ) {
                infoTextView.text = getString(R.string.no_info_message)
            } else {
                 val builder = StringBuilder()
                if (!description.isNullOrEmpty()) {
                    builder.append(getString(R.string.description))
                    builder.append("\n")
                    builder.append("\n")
                    builder.append(description)
                    builder.append("\n")
                    builder.append("\n")
                    builder.append("\n")
                }
                if (!comics.isNullOrEmpty()) {
                    builder.append(getString(R.string.comics))
                    builder.append("\n")
                    builder.append("\n")
                    comics.map {
                        builder.append(it.name)
                        builder.append("\n")
                    }
                    builder.append("\n")
                    builder.append("\n")
                    builder.append("\n")
                }
                if (!series.isNullOrEmpty()) {
                    builder.append(getString(R.string.series))
                    builder.append("\n")
                    builder.append("\n")
                    series.map {
                        builder.append(it.name)
                        builder.append("\n")
                    }
                }
                infoTextView.text = builder.toString()
            }
        }
    }

    companion object {
        const val CHARACTER = "character_param"
    }
}