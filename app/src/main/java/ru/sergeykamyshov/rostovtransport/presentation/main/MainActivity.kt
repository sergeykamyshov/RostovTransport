package ru.sergeykamyshov.rostovtransport.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.R
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
                R.id.nav_item_news -> startFragment(NewsFragment.newInstance())
                R.id.nav_item_help -> startFragment(HelpFragment.newInstance())
                R.id.nav_item_transport_card -> startFragment(TransportCardFragment.newInstance())
                R.id.nav_item_complain -> startFragment(ComplainFragment.newInstance())
//                R.id.nav_item_about -> startFragment(AboutFragment.newInstance())
                R.id.nav_item_about -> startFragment(ru.sergeykamyshov.rostovtransport.presentation.help2.HelpFragment.newInstance())
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
            startFragment(NewsFragment.newInstance())
        }

    }

    private fun startFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment)
                .commit()
        return true
    }

    fun showAppBarLayout() {
        main_app_bar.setExpanded(true)
    }
}
