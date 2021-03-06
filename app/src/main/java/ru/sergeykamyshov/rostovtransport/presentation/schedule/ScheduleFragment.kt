package ru.sergeykamyshov.rostovtransport.presentation.schedule

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.data.models.schedule.Directions
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class ScheduleFragment : BaseFragment() {

    val jsonDataApi = App.provider.api.jsonDataApi
    lateinit var adapter: ScheduleAdapter

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        setActionBarTitle(R.string.title_schedule)
        setHasOptionsMenu(true)

        schedule_recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = ScheduleAdapter(requireContext(), ArrayList())
        schedule_recycler.adapter = adapter
        schedule_recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        schedule_recycler.setHasFixedSize(true)

        val call = jsonDataApi.getDirections()

        call.enqueue(object : Callback<Directions> {
            override fun onResponse(call: Call<Directions>?, response: Response<Directions>?) {
                val body = response?.body()
                val directions = body?.directions
                if (directions != null) {
                    adapter.updateData(directions)
                    schedule_progress.hide()
                }
            }

            override fun onFailure(call: Call<Directions>?, t: Throwable?) {
            }
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_schedule, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

//    override fun onItemClick(cityName: String) {
//        val intent = Intent(activity, CityScheduleActivity::class.java)
//        intent.putExtra(CityScheduleActivity.city, cityName)
//        startActivity(intent)
//    }

}