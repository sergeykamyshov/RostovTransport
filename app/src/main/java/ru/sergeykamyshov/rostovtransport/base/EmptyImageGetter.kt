package ru.sergeykamyshov.rostovtransport.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.Html

class EmptyImageGetter : Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable {
        return ColorDrawable(Color.TRANSPARENT)
    }
}