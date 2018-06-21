package ru.sergeykamyshov.rostovtransport.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.schedule.Directions
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.ui.schedule.city.CityScheduleActivity

class ScheduleFragment : BaseFragment(), OnItemClickListener {

    lateinit var adapter: ScheduleAdapter
    lateinit var mProgress: ProgressBar

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        setActionBarTitle(R.string.title_schedule)
        setHasOptionsMenu(true)

        mProgress = view.findViewById(R.id.schedule_progress)

        val recycler = view.findViewById<RecyclerView>(R.id.schedule_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = ScheduleAdapter(activity, ArrayList(), this)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val call = (activity as MainActivity).restService.getDirections()

        call.enqueue(object : Callback<Directions> {
            override fun onResponse(call: Call<Directions>?, response: Response<Directions>?) {
                Log.i("ScheduleNetworkTest", "Responce ${response?.isSuccessful}")
                val body = response?.body()
                val directions = body?.directions
                if (directions != null) {
                    adapter.updateData(directions)
                    mProgress.visibility = View.GONE
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