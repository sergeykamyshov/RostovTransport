package ru.sergeykamyshov.rostovtransport.presentation.base

import androidx.appcompat.app.AppCompatActivity

open class ViewStateActivity : AppCompatActivity() {

    protected var viewState: ViewState = ViewState()

    override fun onStart() {
        super.onStart()
        viewState.init(this)
    }

}