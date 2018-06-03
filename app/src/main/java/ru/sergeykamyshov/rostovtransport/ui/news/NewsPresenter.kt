package ru.sergeykamyshov.rostovtransport.ui.news

import ru.sergeykamyshov.rostovtransport.ui.news.NewsContract.MvpView

class NewsPresenter : NewsContract.MvpPresenter {

    private var mView: MvpView? = null

    override fun onAttach(view: MvpView) {
        mView = view
    }

    override fun onViewReady() {

    }

    override fun onDetach() {
        mView = null
    }

}