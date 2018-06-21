package ru.sergeykamyshov.rostovtransport.ui.routes

import android.content.res.Configuration
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
        // Используем childFragmentManager для корректного поведения фрагментов на вкладках,
        // т.к. они находятся внутри фрагмента "Маршруты"
        viewPager.adapter = RoutesAdapter(activity, childFragmentManager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_routes)
        tabLayout?.setupWithViewPager(viewPager)
        // В портретной ориентации все вкладки не вмещаются, поэтому по умолчанию они scrollable,
        // но в горизонтальной ориентации больше места, поэтому меняем настройку только при повороте экрана
        if (Configuration.ORIENTATION_LANDSCAPE == activity?.resources?.configuration?.orientation) {
            tabLayout.tabMode = TabLayout.MODE_FIXED
        }

        return view
    }
}