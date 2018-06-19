package ru.sergeykamyshov.rostovtransport.data.network.help

import com.google.gson.annotations.SerializedName
import java.util.*

class Help {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("contacts")
    lateinit var contacts: List<Contact>

    class Contact {

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("desc")
        lateinit var desc: String

        @SerializedName("phones")
        lateinit var phones: List<String>

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("site")
        lateinit var site: String

        @SerializedName("emails")
        lateinit var emails: List<String>
    }

}