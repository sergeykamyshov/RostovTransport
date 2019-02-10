package ru.sergeykamyshov.rostovtransport.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.about.AboutFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.TransportCardFragment
import ru.sergeykamyshov.rostovtransport.presentation.complain.ComplainFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.HelpFragment
import ru.sergeykamyshov.rostovtransport.presentation.news.list.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настраивает Toolbar
        setSupportActionBar(main_toolbar)

        // Обрабатываем выбор пункта меню
        vBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_news -> showFragment(NewsFragment.newInstance(), NewsFragment.TAG)
                R.id.nav_item_help -> showFragment(HelpFragment.newInstance(), HelpFragment.TAG)
                R.id.nav_item_transport_card -> showFragment(TransportCardFragment.newInstance(), TransportCardFragment.TAG)
                R.id.nav_item_complain -> showFragment(ComplainFragment.newInstance(), ComplainFragment.TAG)
                R.id.nav_item_about -> showFragment(AboutFragment.newInstance(), AboutFragment.TAG)
//                R.id.nav_item_routes -> showFragment(RoutesFragment.newInstance())
//                R.id.nav_item_schedule -> showFragment(ScheduleFragment.newInstance())
//                R.id.nav_item_transport_online -> showFragment(TransportOnlineFragment.newInstance())
            }
            true
        }

        if (supportFragmentManager.findFragmentById(R.id.flContainer) != null) {
            // Восстанавливаем фрагмент после поворота экрана
            supportFragmentManager.popBackStack()
        } else {
            // Экран по умолчанию - Новости
            showFragment(NewsFragment.newInstance(), NewsFragment.TAG)
        }

    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val fragmentByTag = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentByTag != null) {
            supportFragmentManager
                    .beginTransaction()
                    .show(fragmentByTag)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, fragment, tag)
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (R.id.nav_item_news != vBottomNavigation.selectedItemId) {
            vBottomNavigation.selectedItemId = R.id.nav_item_news
        } else {
            super.onBackPressed()
        }
    }

    fun showAppBarLayout() {
        main_app_bar.setExpanded(true)
    }
}
