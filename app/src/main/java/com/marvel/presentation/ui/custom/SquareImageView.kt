package com.marvel.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class SquareImageView(context: Context, attributes: AttributeSet) : ImageView(context, attributes) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)
    }
}
