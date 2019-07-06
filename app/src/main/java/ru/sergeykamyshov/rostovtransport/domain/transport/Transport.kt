package ru.sergeykamyshov.rostovtransport.domain.transport

data class Transport(
        val lon: Double,
        val lat: Double,
        var speed: String,
        var time: String,
        var angle: String,
        var name: String,
        var type: String,
        var num: String,
        var prev: Prev
)

data class Prev(
        var lon: Double,
        var lat: Double,
        var speed: String,
        var angle: String,
        var time: String
)