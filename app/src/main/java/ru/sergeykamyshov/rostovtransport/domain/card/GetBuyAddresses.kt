package ru.sergeykamyshov.rostovtransport.domain.card

import io.reactivex.Single

class GetBuyAddresses(private val dataSource: CardDataSource) {

    fun execute(): Single<List<BuyAddress>> {
        return dataSource.getBuyAddresses()
    }

}