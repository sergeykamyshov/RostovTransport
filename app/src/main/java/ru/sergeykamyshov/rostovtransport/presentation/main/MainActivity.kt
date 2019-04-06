package ru.sergeykamyshov.rostovtransport.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настраивает Toolbar
        setSupportActionBar(main_toolbar)

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        // Обрабатываем выбор пункта меню
        vBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_news -> navController.navigate(R.id.newsFragment)
                R.id.nav_item_help -> navController.navigate(R.id.helpFragment)
                R.id.nav_item_transport_card -> navController.navigate(R.id.transportCardFragment)
                R.id.nav_item_complain -> navController.navigate(R.id.complainFragment)
                R.id.nav_item_about -> navController.navigate(R.id.aboutFragment)
//                R.id.nav_item_routes -> showFragment(RoutesFragment.newInstance())
//                R.id.nav_item_schedule -> showFragment(ScheduleFragment.newInstance())
//                R.id.nav_item_transport_online -> showFragment(TransportOnlineFragment.newInstance())
            }
            true
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
