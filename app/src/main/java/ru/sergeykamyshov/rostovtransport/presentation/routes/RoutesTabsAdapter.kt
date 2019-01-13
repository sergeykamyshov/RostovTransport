package ru.sergeykamyshov.rostovtransport.presentation.routes

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.routes.bus.BusListFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.shuttle.ShuttleListFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.tram.TramListFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.trolleybus.TrolleybusListFragment

/**
 * Адаптер экрана "Маршруты" для формирования вкладок
 */
class RoutesTabsAdapter(
        var context: Context?,
        fm: FragmentManager?
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ShuttleListFragment.newInstance()
            1 -> return BusListFragment.newInstance()
            2 -> return TrolleybusListFragment.newInstance()
            3 -> return TramListFragment.newInstance()
        }
        return ShuttleListFragment.newInstance()
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context?.getString(R.string.routes_tab_title_shuttle)
            1 -> return context?.getString(R.string.routes_tab_title_bus)
            2 -> return context?.getString(R.string.routes_tab_title_trolleybus)
            3 -> return context?.getString(R.string.routes_tab_title_tram)
        }
        return super.getPageTitle(position)
    }
}