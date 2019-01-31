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
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настраивает Toolbar
        setSupportActionBar(main_toolbar)

        // Обрабатываем выбор пункта меню
        vBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_news -> startFragment(NewsFragment.newInstance(), NewsFragment.TAG)
                R.id.nav_item_help -> startFragment(HelpFragment.newInstance(), HelpFragment.TAG)
                R.id.nav_item_transport_card -> startFragment(TransportCardFragment.newInstance(), TransportCardFragment.TAG)
                R.id.nav_item_complain -> startFragment(ComplainFragment.newInstance(), ComplainFragment.TAG)
                R.id.nav_item_about -> startFragment(AboutFragment.newInstance(), AboutFragment.TAG)
//                R.id.nav_item_routes -> startFragment(RoutesFragment.newInstance())
//                R.id.nav_item_schedule -> startFragment(ScheduleFragment.newInstance())
//                R.id.nav_item_transport_online -> startFragment(TransportOnlineFragment.newInstance())
            }
            true
        }

        if (supportFragmentManager.findFragmentById(R.id.flContainer) != null) {
            // Восстанавливаем фрагмент после поворота экрана
            supportFragmentManager.popBackStack()
        } else {
            // Экран по умолчанию - Новости
            startFragment(NewsFragment.newInstance(), NewsFragment.TAG)
        }

    }

    private fun startFragment(fragment: Fragment, tag: String) {
        Timber.d("Try to find TAG = $tag")
        val fragmentByTag = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentByTag != null) {
            Timber.d("fragmentByTag != null. Show it")
            supportFragmentManager
                    .beginTransaction()
                    .show(fragmentByTag)
                    .commit()
        } else {
            Timber.d("fragmentByTag is null. Replace it")
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, fragment, tag)
                    .commit()
        }
    }

    fun showAppBarLayout() {
        main_app_bar.setExpanded(true)
    }
}
