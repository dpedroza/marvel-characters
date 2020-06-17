package com.marvel.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.marvel.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = MainViewPagerAdapter(
            supportFragmentManager,
            this
        )
    }

    fun showLoading() {

        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {

        progressBar.visibility = View.INVISIBLE
    }
}
