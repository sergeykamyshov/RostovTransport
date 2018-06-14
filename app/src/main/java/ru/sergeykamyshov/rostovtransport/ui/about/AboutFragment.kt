package ru.sergeykamyshov.rostovtransport.ui.about

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        setActionBarTitle(R.string.title_about)

        val progressBar = view.findViewById<ProgressBar>(R.id.about_progress)
        val teamPhoto = view.findViewById<ImageView>(R.id.img_about_team)
        val shortDesc = view.findViewById<TextView>(R.id.tv_short_desc)
        val importantInfo = view.findViewById<TextView>(R.id.tv_important)
        val fullDesc = view.findViewById<TextView>(R.id.tv_full_desc)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(AboutViewModel::class.java)
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it?.photoUrl == null || it.photoUrl.isEmpty()) {
                teamPhoto.visibility = View.GONE
            } else {
                Picasso.get().load(it.photoUrl).resize(400, 200).centerCrop().into(teamPhoto)
            }
            shortDesc.text = it?.shortDescription
            importantInfo.text = it?.importantInfo
            fullDesc.text = it?.fullDescription
            progressBar.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }
}