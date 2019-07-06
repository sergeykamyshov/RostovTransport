package ru.sergeykamyshov.rostovtransport.domain.transport

data class Transport(
        val lon: Double,
        val lat: Double,
        val speed: String,
        val time: String,
        val angle: String,
        val name: String,
        val type: String,
        val num: String,
        val prev: Prev?,
        val gray: Boolean
)

data class Prev(
        val lon: Double,
        val lat: Double,
        val speed: String,
        val angle: String,
        val time: String
)