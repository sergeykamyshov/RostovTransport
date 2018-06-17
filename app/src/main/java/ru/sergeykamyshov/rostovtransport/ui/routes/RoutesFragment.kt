package ru.sergeykamyshov.rostovtransport.ui.routes

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class RoutesFragment : BaseFragment() {

    companion object {
        fun newInstance() = RoutesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_routes, container, false)

        setActionBarTitle(R.string.title_routes)

        // Настраиваем переключение по вкладкам
        val viewPager = view.findViewById<ViewPager>(R.id.vp_routes)
        viewPager.adapter = RoutesAdapter(activity, activity?.supportFragmentManager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_routes)
        tabLayout?.setupWithViewPager(viewPager)

        return view
    }
}