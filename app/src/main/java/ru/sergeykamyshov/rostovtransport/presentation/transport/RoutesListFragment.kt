package ru.sergeykamyshov.rostovtransport.presentation.transport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_routes_list.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class RoutesListFragment : BaseFragment(), RoutesListAdapter.Callback {

    private lateinit var adapter: RoutesListAdapter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_routes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_transport)

        navController = NavHostFragment.findNavController(this)

        setupRecycler()
        addTestData()
    }

    override fun onRouteClick(route: Route) {
        navController.navigate(R.id.action_routesListFragment_to_transportMapActivity)
    }

    override fun onFavoriteClick(route: Route) {
        Toast.makeText(requireContext(), "On Favorite Click", Toast.LENGTH_SHORT).show()
    }

    override fun onNotFavoriteClick(route: Route) {
        Toast.makeText(requireContext(), "On Not Favorite Click", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecycler() {
        rv_transport.layoutManager = LinearLayoutManager(requireContext())
        rv_transport.adapter = RoutesListAdapter(requireContext(), this).also {
            adapter = it
        }
    }

    private fun addTestData() {
        val listOf = mutableListOf<Route>()
        for (i in 1..20) {
            listOf.add(Route("$i", "$i"))
        }
        adapter.items = listOf
    }
}