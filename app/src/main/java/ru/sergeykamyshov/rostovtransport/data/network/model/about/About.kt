package ru.sergeykamyshov.rostovtransport.data.network.model.about

import com.google.gson.annotations.SerializedName
import java.util.*

class About {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("shortDescription")
    lateinit var shortDescription: String

    @SerializedName("importantInfo")
    lateinit var importantInfo: String

    @SerializedName("fullDescription")
    lateinit var fullDescription: String

    @SerializedName("contacts")
    lateinit var contacts: List<Contact>

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