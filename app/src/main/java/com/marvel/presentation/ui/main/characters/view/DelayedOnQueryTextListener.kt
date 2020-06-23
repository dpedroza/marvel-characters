package com.marvel.presentation.ui.main.characters.view

import android.os.Handler
import androidx.appcompat.widget.SearchView

abstract class DelayedOnQueryTextListener : SearchView.OnQueryTextListener {
    private val handler: Handler = Handler()
    private var runnable: Runnable? = null

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onQueryTextChange(query: String?): Boolean {
        handler.removeCallbacks(runnable)
        runnable = Runnable { onDelayerQueryTextChange(query) }
        handler.postDelayed(runnable, 400)
        return true
    }

    abstract fun onDelayerQueryTextChange(query: String?)
}
