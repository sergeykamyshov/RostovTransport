package ru.sergeykamyshov.rostovtransport.presentation.help

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_help.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import timber.log.Timber

class HelpFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("HP. onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("HP. onCreateView")
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        setActionBarTitle(R.string.title_help)

        // Настраиваем переключение по вкладкам
        val viewPager = view.vp_help
        // Используем childFragmentManager для корректного поведения фрагментов на вкладках,
        // т.к. они находятся внутри фрагмента "Справка"
        viewPager.adapter = HelpTabsAdapter(activity, childFragmentManager)
        val tabLayout = view.tab_help
        tabLayout?.setupWithViewPager(viewPager)
        // В портретной ориентации все вкладки не вмещаются, поэтому по умолчанию они scrollable,
        // но в горизонтальной ориентации больше места, поэтому меняем настройку только при повороте экрана
        if (Configuration.ORIENTATION_LANDSCAPE == activity?.resources?.configuration?.orientation) {
            tabLayout.tabMode = TabLayout.MODE_FIXED
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("HP. onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("HP. onDestroy")
    }

    companion object {
        const val TAG = "HelpFragment"

        fun newInstance() = HelpFragment()
    }

}