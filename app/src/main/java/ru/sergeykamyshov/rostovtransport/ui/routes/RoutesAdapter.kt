package ru.sergeykamyshov.rostovtransport.ui.routes

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.routes.bus.BusListFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.shuttle.ShuttleListFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.tram.TramListFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.trolleybus.TrolleybusListFragment

class RoutesAdapter(var mContext: Context?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ShuttleListFragment()
            1 -> return BusListFragment()
            2 -> return TrolleybusListFragment()
            3 -> return TramListFragment()
        }
        return ShuttleListFragment()
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext?.getString(R.string.routes_tab_title_shuttle)
            1 -> return mContext?.getString(R.string.routes_tab_title_bus)
            2 -> return mContext?.getString(R.string.routes_tab_title_trolleybus)
            3 -> return mContext?.getString(R.string.routes_tab_title_tram)
        }
        return super.getPageTitle(position)
    }
}