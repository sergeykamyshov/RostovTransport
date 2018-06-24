package ru.sergeykamyshov.rostovtransport.data.network.model.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardQuestions {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("questions")
    lateinit var questions: List<Question>

    class Question {

        @SerializedName("question")
        lateinit var question: String

        @SerializedName("answer")
        lateinit var answer: String

    }
}