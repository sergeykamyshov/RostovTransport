package ru.sergeykamyshov.rostovtransport.domain.card

class BuyAddress(
        val type: Int,
        val desc: String,
        val note: String,
        val locations: List<String>
)