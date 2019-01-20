package ru.sergeykamyshov.rostovtransport.presentation.card.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.card.CardQuestions
import ru.sergeykamyshov.rostovtransport.data.models.card.CardQuestions.Question

class CardQuestionsViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<List<Question>>()

    fun getData(): LiveData<List<Question>> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getCardQuestions()
        call.enqueue(object : Callback<CardQuestions> {
            override fun onResponse(call: Call<CardQuestions>?, response: Response<CardQuestions>?) {
                val cardQuestions = response?.body()
                data.postValue(cardQuestions?.questions)
            }

            override fun onFailure(call: Call<CardQuestions>?, t: Throwable?) {
            }
        })
    }

}