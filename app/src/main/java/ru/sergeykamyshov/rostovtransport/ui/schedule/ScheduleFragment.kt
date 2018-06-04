package ru.sergeykamyshov.rostovtransport.ui.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.schedule_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ScheduleAdapter(activity, getTestCities())
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        return view
    }

    // TODO: удалить после тестирования
    private fun getTestCities(): List<String> {
        var cities = mutableListOf<String>()
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