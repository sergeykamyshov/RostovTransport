package ru.sergeykamyshov.rostovtransport.domain.help

import com.google.gson.annotations.SerializedName

data class Contact(
        @SerializedName("name") val name: String,
        @SerializedName("desc") val desc: String,
        @SerializedName("phones") val phones: List<String>,
        @SerializedName("address") val address: String,
        @SerializedName("site") val site: String,
        @SerializedName("emails") val emails: List<String>
)