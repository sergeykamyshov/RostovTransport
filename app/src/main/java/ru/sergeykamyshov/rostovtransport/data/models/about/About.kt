package ru.sergeykamyshov.rostovtransport.data.models.about

import com.google.gson.annotations.SerializedName
import java.util.*

class About {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("cards")
    lateinit var cards: List<Card>

    @SerializedName("contacts")
    lateinit var contacts: List<Contact>

    class Card {
        @SerializedName("title")
        lateinit var title: String

        @SerializedName("content")
        lateinit var content: String
    }

    class Contact {
        @SerializedName("position")
        lateinit var position: String

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("email")
        lateinit var email: String

        @SerializedName("phone")
        lateinit var phone: String
    }
}