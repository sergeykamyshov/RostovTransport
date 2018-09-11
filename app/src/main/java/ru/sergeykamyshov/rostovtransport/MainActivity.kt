package ru.sergeykamyshov.rostovtransport

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.ui.about.AboutFragment
import ru.sergeykamyshov.rostovtransport.ui.card.TransportCardFragment
import ru.sergeykamyshov.rostovtransport.ui.complain.ComplainFragment
import ru.sergeykamyshov.rostovtransport.ui.help.HelpFragment
import ru.sergeykamyshov.rostovtransport.ui.news.list.NewsFragment
import ru.sergeykamyshov.rostovtransport.ui.online.TransportOnlineFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.RoutesFragment
import ru.sergeykamyshov.rostovtransport.ui.schedule.ScheduleFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restService: RestService

    private lateinit var mDrawer: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var mAppBar: AppBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настраивает Toolbar
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mAppBar = findViewById(R.id.main_app_bar)

        // Настраиваем Drawer
        mDrawer = findViewById(R.id.drawer_layout)
        mToggle = ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawer.addDrawerListener(mToggle)

        // Обрабатываем выбор пункта меню
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_news -> startFragment(NewsFragment.newInstance())
                R.id.nav_item_routes -> startFragment(RoutesFragment.newInstance())
                R.id.nav_item_schedule -> startFragment(ScheduleFragment.newInstance())
                R.id.nav_item_transport_online -> startFragment(TransportOnlineFragment.newInstance())
                R.id.nav_item_help -> startFragment(HelpFragment.newInstance())
                R.id.nav_item_transport_card -> startFragment(TransportCardFragment.newInstance())
                R.id.nav_item_complain -> startFragment(ComplainFragment.newInstance())
                R.id.nav_item_about -> startFragment(AboutFragment.newInstance())
                else -> super.onOptionsItemSelected(menuItem)
            }
            menuItem.isChecked = true
            mDrawer.closeDrawers()
            true
        }

        // Dagger
        App.daggerComponent.inject(this)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) != null) {
            // Восстанавливаем фрагмент после поворота экрана
            supportFragmentManager.popBackStack()
        } else {
            // Экран по умолчанию - Новости
            startFragment(NewsFragment.newInstance())
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mToggle.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun startFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        return true
    }

    fun showAppBarLayout() {
        mAppBar.setExpanded(true)
    }
}
