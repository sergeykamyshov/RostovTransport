package ru.sergeykamyshov.rostovtransport.presentation.help2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import timber.log.Timber

class HelpFragment : BaseFragment() {

    companion object {
        fun newInstance() = HelpFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Test GetDepartments use case
        App.provider.useCase.getDepartments.execute()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Success. ${it.size} items")
                }, {
                    Timber.e(it)
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        setActionBarTitle(R.string.title_help)
        return view
    }
}