package ru.sergeykamyshov.rostovtransport.data.transport

import io.reactivex.Single
import ru.sergeykamyshov.rostovtransport.domain.transport.Transport
import ru.sergeykamyshov.rostovtransport.domain.transport.TransportDataSource

class TransportRepository(private val transportApi: TransportApi) : TransportDataSource {

    override fun getTransport(route: String): Single<List<Transport>> {
        return transportApi.getTransport(route)
                .map { it.map { item -> item.toTransport() } }
    }

}