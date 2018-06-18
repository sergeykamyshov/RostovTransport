package ru.sergeykamyshov.rostovtransport.ui.help

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class HelpFragment : BaseFragment() {

    companion object {
        fun newInstance() = HelpFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        setActionBarTitle(R.string.title_help)

        // Настраиваем переключение по вкладкам
        val viewPager = view.findViewById<ViewPager>(R.id.vp_help)
        // Используем childFragmentManager для корректного поведения фрагментов на вкладках, т.к. они находятся внутри фрагмента "Маршруты"
        viewPager.adapter = HelpAdapter(activity, childFragmentManager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_help)
        tabLayout?.setupWithViewPager(viewPager)

        return view
    }
}