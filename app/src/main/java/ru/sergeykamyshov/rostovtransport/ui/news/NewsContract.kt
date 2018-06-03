package ru.sergeykamyshov.rostovtransport.ui.news

interface NewsContract {

    interface MvpPresenter {
        fun onAttach(view: MvpView)
        fun onViewReady()
        fun onDetach()
    }

    interface MvpView {
    }
}