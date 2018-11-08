package ru.sergeykamyshov.rostovtransport.ui.complain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_complain.*
import kotlinx.android.synthetic.main.fragment_complain.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class ComplainFragment : BaseFragment(), Contract.View {
    companion object {
        fun newInstance() = ComplainFragment()
    }

    private lateinit var presenter: Contract.Presenter
    private val violationsCheckedPositions = "violations_checked_positions"
    private lateinit var adapter: ViolationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_complain, container, false)

        setActionBarTitle(R.string.title_complain)

        presenter = ComplainPresenter()
        presenter.attachView(this)

        view.btn_generate_complain.setOnClickListener { initSendingComplain() }

        // Настраиваем RecyclerView
        view.recycler_offences.layoutManager = LinearLayoutManager(activity)
        view.recycler_offences.setHasFixedSize(true)
        view.recycler_offences.isNestedScrollingEnabled = false

        // Подготавливаем данные для адаптера
        val violations = getViolations()
        fillViolations(violations, savedInstanceState)
        adapter = ViolationsAdapter(violations)
        view.recycler_offences.adapter = adapter
        view.recycler_offences.invalidate()

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val positions = adapter.getCheckedPositions()
        outState.putIntegerArrayList(violationsCheckedPositions, ArrayList(positions))

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun getViolations(): MutableList<ViolationItem> {
        val entries = resources.getStringArray(R.array.violation_entries)
        val violations = mutableListOf<ViolationItem>()
        for (violation in entries) {
            violations.add(ViolationItem(violation))
        }
        return violations
    }

    private fun fillViolations(violations: MutableList<ViolationItem>, savedInstanceState: Bundle?) {
        val checkedPositions = savedInstanceState?.getIntegerArrayList(violationsCheckedPositions)
        checkedPositions?.let {
            for (position in it) {
                violations[position].checked = true
            }
        }
    }

    private fun initSendingComplain() {
        presenter.sendComplaint()
    }

    override fun getTransportTypeTitle(): String {
        return txt_transport_type_title.text.toString()
    }

    override fun getTransportTypeString(): String {
        return when (group_transport_types.checkedRadioButtonId) {
            R.id.rb_transport_type_bus -> rb_transport_type_bus.text.toString()
            R.id.rb_transport_type_shuttlebus -> rb_transport_type_shuttlebus.text.toString()
            R.id.rb_transport_type_trolleybus -> rb_transport_type_trolleybus.text.toString()
            R.id.rb_transport_type_tram -> rb_transport_type_tram.text.toString()
            else -> ""
        }
    }

    override fun getRouteTitle(): String {
        return txt_route_number_title.text.toString()
    }

    override fun getRouteString(): String {
        return edt_route.text.toString()
    }

    override fun getTransportNumberTitle(): String {
        return txt_transport_number_title.text.toString()
    }

    override fun getTransportNumberString(): String {
        return edt_transport_number.text.toString()
    }

    override fun getTimeTitle(): String {
        return txt_time_title.text.toString()
    }

    override fun getTimeString(): String {
        return edt_time.text.toString()
    }

    override fun getPlaceTitle(): String {
        return txt_place_title.text.toString()
    }

    override fun getPlaceString(): String {
        return edt_place.text.toString()
    }

    override fun getViolationsTitle(): String {
        return txt_offence_title.text.toString()
    }

    override fun getCheckedItems(): List<ViolationItem> {
        return adapter.getCheckedItems()
    }

    override fun showCheckErrorToast() {
        Toast.makeText(activity, getString(R.string.complain_error_check_fill), Toast.LENGTH_LONG).show()
    }

    override fun sendComplaintViaEmail(text: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:mail@rostov-transport.info"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Пожаловаться на транспорт")
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(intent)
    }
}