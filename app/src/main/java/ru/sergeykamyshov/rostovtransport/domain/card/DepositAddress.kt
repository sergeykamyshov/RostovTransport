package ru.sergeykamyshov.rostovtransport.domain.card

class DepositAddress(
        val type: Int,
        val desc: String,
        val address: String,
        val schedule: String,
        val location: String
)