package ru.sergeykamyshov.rostovtransport.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class PicassoImageGetter(view: TextView) : Html.ImageGetter {

    var mTargetView = view

    override fun getDrawable(source: String?): Drawable {
        val placeHolder = BitmapDrawablePlaceHolder()
        Picasso.get().load(source).into(placeHolder)
        return placeHolder
    }

    inner class BitmapDrawablePlaceHolder : BitmapDrawable(), Target {

        private var mDrawable: Drawable? = null

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            setDrawable(BitmapDrawable(bitmap))
        }

        private fun setDrawable(drawable: Drawable) {
            mDrawable = drawable
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
            mTargetView.text = mTargetView.text
        }

        override fun draw(canvas: Canvas?) {
            mDrawable?.draw(canvas)
        }
    }
}