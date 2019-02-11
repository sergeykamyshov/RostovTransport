package ru.sergeykamyshov.rostovtransport.presentation.complain

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import kotlinx.android.synthetic.main.fragment_complain.*
import kotlinx.android.synthetic.main.fragment_complain.view.*
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.base.extentions.sendEmail
import ru.sergeykamyshov.rostovtransport.base.extentions.sendEvent
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.collections.ArrayList

class ComplainFragment : BaseFragment(), Contract.View {

    private val SEND_COMPLAIN_EVENT = "send_complaint"
    private val violationsCheckedPositions = "violations_checked_positions"

    private lateinit var presenter: Contract.Presenter
    private lateinit var adapter: ViolationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_complain, container, false)

        setActionBarTitle(R.string.title_complain)

        presenter = ComplainPresenter()
        presenter.attachView(this)

        view.btn_send_complain.onClickDebounce {
            presenter.sendComplaint()
        }

        // Настраиваем выбор времени
        val calendar = Calendar.getInstance()
        view.edt_time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
        val dialog = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { v, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            view.edt_time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
        view.edt_time.onClickDebounce {
            dialog.show()
        }

        // Настраиваем RecyclerView
        view.recycler_offences.layoutManager = LinearLayoutManager(activity)
        view.recycler_offences.setHasFixedSize(true)
        view.recycler_offences.isNestedScrollingEnabled = false

        // Подготавливаем данные для адаптера
        val violations = getViolations()
        fillViolations(violations, savedInstanceState)
        adapter = ViolationsAdapter(violations)
        view.recycler_offences.adapter = adapter

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val positions = adapter.getCheckedPositions()
        outState.putIntegerArrayList(violationsCheckedPositions, ArrayList(positions))

        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        val inputMethodManger = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManger.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
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

    override fun getCommentTitle(): String {
        return txt_comment_title.text.toString()
    }

    override fun getCommentString(): String {
        return edt_comment.text.toString()
    }

    override fun getSignString(): String {
        return getString(R.string.complain_sign)
    }

    override fun showCheckErrorToast() {
        Toast.makeText(activity, getString(R.string.complain_error_check_fill), Toast.LENGTH_LONG).show()
    }

    override fun sendComplaintViaEmail(text: String) {
        if (!BuildConfig.DEBUG) {
            App.firebaseAnalytics.sendEvent(
                    SEND_COMPLAIN_EVENT,
                    mapOf(
                            "transport_type" to getTransportTypeString(),
                            "route_number" to getRouteString().toLowerCase()
                    )
            )
            Answers.getInstance().logCustom(CustomEvent(SEND_COMPLAIN_EVENT)
                    .putCustomAttribute("transport_type", getTransportTypeString())
                    .putCustomAttribute("route_number", getRouteString().toLowerCase())
            )
        }
        val result = activity?.sendEmail(
                getString(R.string.complain_email),
                getString(R.string.complain_email_subject),
                text)
        if (result != null && !result) {
            Toast.makeText(
                    activity,
                    getString(R.string.error_no_email_client),
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val TAG = "ComplainFragment"

        fun newInstance() = ComplainFragment()
    }

}