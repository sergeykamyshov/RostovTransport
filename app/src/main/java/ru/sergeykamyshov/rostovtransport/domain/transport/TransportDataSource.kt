package ru.sergeykamyshov.rostovtransport.domain.transport

import io.reactivex.Single

interface TransportDataSource {

    fun getTransport(route: String): Single<List<Transport>>

}