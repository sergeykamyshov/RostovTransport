package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardQuestions
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardQuestions.Question

class CardQuestionsViewModel : ViewModel() {

    val restService: RestService = App.restService
    private var data = MutableLiveData<List<Question>>()

    fun getData(): LiveData<List<Question>> {
        return data
    }

    fun loadData() {
        val call = restService.getCardQuestions()
        call.enqueue(object : Callback<CardQuestions> {
            override fun onResponse(call: Call<CardQuestions>?, response: Response<CardQuestions>?) {
                val cardQuestions = response?.body()
                Log.i("CardQuestionsViewModel", "Last update: ${cardQuestions?.lastUpdate}")
                data.postValue(cardQuestions?.questions)
            }

            override fun onFailure(call: Call<CardQuestions>?, t: Throwable?) {
                Log.i("CardQuestionsViewModel", "Failed to get card questions: $t")
            }
        })
    }

}