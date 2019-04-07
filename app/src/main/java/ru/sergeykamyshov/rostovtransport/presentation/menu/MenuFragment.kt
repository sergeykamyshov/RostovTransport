package ru.sergeykamyshov.rostovtransport.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class MenuFragment : BaseFragment() {

    private lateinit var adapter: MenuAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_menu)

        setupRecycler()

        adapter.items = listOf(
                SimpleOption(R.drawable.ic_nav_help_24px, R.string.nav_item_help),
                SimpleOption(R.drawable.ic_nav_transport_card_24px, R.string.nav_item_transport_card),
                SimpleOption(R.drawable.ic_nav_about_project_24px, R.string.nav_item_about),
                SimpleOption(R.drawable.ic_star_border_black_24dp, R.string.menu_rate_app)
        )
    }

    private fun setupRecycler() {
        rv_menu.layoutManager = LinearLayoutManager(requireContext())
        adapter = MenuAdapter(requireContext())
        rv_menu.adapter = adapter
    }

}