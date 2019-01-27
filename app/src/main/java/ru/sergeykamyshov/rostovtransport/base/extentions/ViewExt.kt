package ru.sergeykamyshov.rostovtransport.base.extentions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

val DEFAULT_DELAY_MS = 750L

inline fun View.onClickDebounce(delayMs: Long = DEFAULT_DELAY_MS, crossinline l: (View?) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var notClicked = true
        override fun onClick(view: View) {
            if (notClicked) {
                notClicked = false
                l(view)
                view.postDelayed({ notClicked = true }, delayMs)
            }
        }
    })
}