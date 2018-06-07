package ru.sergeykamyshov.rostovtransport.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.ui.schedule.city.CityScheduleActivity

class ScheduleFragment : BaseFragment(), OnItemClickListener {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        setActionBarTitle(R.string.title_schedule)
        setHasOptionsMenu(true)

        val recycler = view.findViewById<RecyclerView>(R.id.schedule_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ScheduleAdapter(activity, getTestCities(), this)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_schedule, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemClick(cityName: String) {
        val intent = Intent(activity, CityScheduleActivity::class.java)
        intent.putExtra(CityScheduleActivity.city, cityName)
        startActivity(intent)
    }

    // TODO: удалить после тестирования
    private fun getTestCities(): List<String> {
        val cities = mutableListOf<String>()
        for (x in 1..100) {
            if (x % 2 == 0) {
                cities.add("Краснодар $x")
            } else {
                cities.add("Москва $x")
            }
        }
        return cities
    }

}