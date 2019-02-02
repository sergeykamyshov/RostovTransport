package ru.sergeykamyshov.rostovtransport.domain.card

import io.reactivex.Single

class GetDepositAddresses(private val dataSource: CardDataSource) {

    fun execute(): Single<List<DepositAddress>> {
        return dataSource.getDepositAddresses()
    }

}