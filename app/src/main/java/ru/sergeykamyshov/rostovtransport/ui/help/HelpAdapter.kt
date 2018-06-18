package ru.sergeykamyshov.rostovtransport.ui.help

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.help.business.BusinessListFragment
import ru.sergeykamyshov.rostovtransport.ui.help.departments.DepartmentsListFragment
import ru.sergeykamyshov.rostovtransport.ui.help.stations.StationsListFragment

class HelpAdapter(var mContext: Context?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DepartmentsListFragment.newInstance()
            1 -> return StationsListFragment.newInstance()
            2 -> return BusinessListFragment.newInstance()
        }
        return DepartmentsListFragment()
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext?.getString(R.string.help_tab_title_departments)
            1 -> return mContext?.getString(R.string.help_tab_title_stations)
            2 -> return mContext?.getString(R.string.help_tab_title_business)
        }
        return super.getPageTitle(position)
    }
}