package ru.sergeykamyshov.rostovtransport.presentation.schedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.schedule.Directions
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.presentation.schedule.city.CityScheduleActivity

class ScheduleFragment : BaseFragment(), OnItemClickListener {

    lateinit var adapter: ScheduleAdapter

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        setActionBarTitle(R.string.title_schedule)
        setHasOptionsMenu(true)

        schedule_recycler.layoutManager = LinearLayoutManager(activity)
        adapter = ScheduleAdapter(activity, ArrayList(), this)
        schedule_recycler.adapter = adapter
        schedule_recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        schedule_recycler.setHasFixedSize(true)

        val call = (activity as MainActivity).restService.getDirections()

        call.enqueue(object : Callback<Directions> {
            override fun onResponse(call: Call<Directions>?, response: Response<Directions>?) {
                Log.i("ScheduleNetworkTest", "Responce ${response?.isSuccessful}")
                val body = response?.body()
                val directions = body?.directions
                if (directions != null) {
                    adapter.updateData(directions)
                    schedule_progress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Directions>?, t: Throwable?) {
                Log.i("ScheduleNetworkTest", "Failed $t")
            }
        })

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

}