package ru.sergeykamyshov.rostovtransport.domain.transport

import io.reactivex.Single

class GetTransport(private val dataSource: TransportDataSource) {

    fun execute(route: String): Single<List<Transport>> {
        return dataSource.getTransport(route)
    }

}