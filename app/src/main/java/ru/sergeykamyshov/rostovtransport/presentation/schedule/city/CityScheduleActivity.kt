package ru.sergeykamyshov.rostovtransport.presentation.schedule.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_city_schedule.*
import ru.sergeykamyshov.rostovtransport.R

class CityScheduleActivity : AppCompatActivity() {

    companion object {
        const val city: String = "city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_schedule)

        tv_city_name_test.text = intent.getStringExtra(city)
    }
}