package com.marvel.presentation.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.domain.characters.model.entity.ComicsEntity
import com.marvel.domain.characters.model.entity.SeriesEntity
import com.marvel.presentation.detail.adapter.ComicsAdapter
import com.marvel.presentation.detail.adapter.SeriesAdapter
import com.marvel.presentation.model.CharacterViewObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : DetailContract.View, AppCompatActivity() {

    @Inject
    lateinit var presenter: DetailPresenter
    private lateinit var character: CharacterViewObject
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getCharacter()
        setupPresenter()
        setupToolbar()
        setupImage()
        setupFab()
        showName()
        showDescription()
        setupComicsRecyclerView()
        setupSeriesRecyclerView()
    }

    private fun setupPresenter() {
        presenter.attach(this)
        presenter.loadComics(character)
        presenter.loadSeries(character)
    }

    private fun getCharacter() {
        character = intent.getParcelableExtra(CHARACTER) as CharacterViewObject
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

    override fun showName() {
        collapsingToolbar.title = character.name
    }

    override fun showDescription() {
        if (character.description.isNotEmpty()) {
            infoTextView.text = HtmlCompat.fromHtml(
                character.description,
                FROM_HTML_MODE_LEGACY
            )
        } else {
            infoTextView.text = getString(R.string.no_info_message)
        }
    }

    override fun showSeries(series: List<SeriesEntity>) {
        seriesAdapter.updateSeries(series)
    }

    override fun showComics(comics: List<ComicsEntity>) {
        comicsAdapter.updateComics(comics)
    }

    override fun hideComics() {
        comicsTextView.visibility = GONE
        comicsRecyclerView.visibility = GONE
    }

    override fun hideSeries() {
        seriesTextView.visibility = GONE
        seriesRecyclerView.visibility = GONE
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
        collapsingToolbar.setExpandedTitleColor(Color.WHITE)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun setupComicsRecyclerView() {
        comicsAdapter = ComicsAdapter()
        comicsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        comicsRecyclerView.setHasFixedSize(true)
        comicsRecyclerView.adapter = comicsAdapter
    }

    private fun setupSeriesRecyclerView() {
        seriesAdapter = SeriesAdapter()
        seriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        seriesRecyclerView.setHasFixedSize(true)
        seriesRecyclerView.adapter = seriesAdapter
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

    companion object {
        const val CHARACTER = "character_param"
    }
}
