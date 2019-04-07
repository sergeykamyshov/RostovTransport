package ru.sergeykamyshov.rostovtransport.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.openMarketURL
import ru.sergeykamyshov.rostovtransport.base.extentions.sendEmail
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class MenuFragment : BaseFragment(), MenuAdapter.Callback {

    private lateinit var navController: NavController
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_menu)

        navController = NavHostFragment.findNavController(this)

        setupRecycler()

        adapter.items = listOf(
                SimpleOption(OptionType.HELP, R.drawable.ic_nav_help_24dp, R.string.nav_item_help),
                SimpleOption(OptionType.CARD, R.drawable.ic_card_24dp, R.string.nav_item_transport_card),
                SimpleOption(OptionType.ABOUT, R.drawable.ic_people_black_24dp, R.string.nav_item_about),
                SimpleOption(OptionType.RATE, R.drawable.ic_star_border_black_24dp, R.string.menu_rate_app),
                SimpleOption(OptionType.DEVELOPER, R.drawable.ic_mail_outline_black_24dp, R.string.menu_mail_dev)
        )
    }

    override fun onOptionClick(type: OptionType) {
        when (type) {
            OptionType.HELP -> navController.navigate(R.id.action_menuFragment_to_helpFragment)
            OptionType.CARD -> navController.navigate(R.id.action_menuFragment_to_transportCardFragment)
            OptionType.ABOUT -> navController.navigate(R.id.action_menuFragment_to_aboutFragment)
            OptionType.RATE -> activity?.openMarketURL()
            OptionType.DEVELOPER -> activity?.sendEmail(getString(R.string.email_developer))
        }
    }

    private fun setupRecycler() {
        rv_menu.layoutManager = LinearLayoutManager(requireContext())
        adapter = MenuAdapter(requireContext(), this)
        rv_menu.adapter = adapter
    }

}