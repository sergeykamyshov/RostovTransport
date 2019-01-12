package ru.sergeykamyshov.rostovtransport

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_container.*
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.ui.about.AboutFragment
import ru.sergeykamyshov.rostovtransport.ui.card.TransportCardFragment
import ru.sergeykamyshov.rostovtransport.ui.complain.ComplainFragment
import ru.sergeykamyshov.rostovtransport.ui.help.HelpFragment
import ru.sergeykamyshov.rostovtransport.ui.news.list.NewsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restService: RestService

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
                R.id.nav_item_about -> startFragment(AboutFragment.newInstance())
//                R.id.nav_item_routes -> startFragment(RoutesFragment.newInstance())
//                R.id.nav_item_schedule -> startFragment(ScheduleFragment.newInstance())
//                R.id.nav_item_transport_online -> startFragment(TransportOnlineFragment.newInstance())
            }
            true
        }

        // Dagger
        App.daggerComponent.inject(this)

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
