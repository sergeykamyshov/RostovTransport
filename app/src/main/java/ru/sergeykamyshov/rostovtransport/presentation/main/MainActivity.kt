package ru.sergeykamyshov.rostovtransport.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
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

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Устанавливает обработку пунктов меню. ID у destination и menu item должны совпадать
        NavigationUI.setupWithNavController(bottom_navigation, navController)
    }

    override fun onBackPressed() {
        if (R.id.newsFragment != bottom_navigation.selectedItemId) {
            bottom_navigation.selectedItemId = R.id.newsFragment
        } else {
            super.onBackPressed()
        }
    }

    fun showAppBarLayout() {
        main_app_bar.setExpanded(true)
    }

}
