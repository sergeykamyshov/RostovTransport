package ru.sergeykamyshov.rostovtransport.presentation.help

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.help.business.BusinessListFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.departments.DepartmentsListFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.stations.StationsListFragment

/**
 * Адаптер экрана "Справка" для формирования вкладок
 */
class HelpTabsAdapter(
        var context: Context?,
        fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        when (position) {
            0 -> return DepartmentsListFragment.newInstance()
            1 -> return StationsListFragment.newInstance()
            2 -> return BusinessListFragment.newInstance()
        }
        return DepartmentsListFragment.newInstance()
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context?.getString(R.string.help_tab_title_departments)
            1 -> return context?.getString(R.string.help_tab_title_stations)
            2 -> return context?.getString(R.string.help_tab_title_business)
        }
        return super.getPageTitle(position)
    }
}