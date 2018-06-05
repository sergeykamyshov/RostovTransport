package ru.sergeykamyshov.rostovtransport.ui.schedule.city

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class CityScheduleActivity : AppCompatActivity() {

    companion object {
        const val city: String = "city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_schedule)

        val cityName = findViewById<TextView>(R.id.tv_city_name_test)

        cityName.text = intent.getStringExtra(city)
    }
}