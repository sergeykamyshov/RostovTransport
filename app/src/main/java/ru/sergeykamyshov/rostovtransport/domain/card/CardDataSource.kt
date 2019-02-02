package ru.sergeykamyshov.rostovtransport.domain.card

import io.reactivex.Single

interface CardDataSource {

    fun getDepositAddresses(): Single<List<DepositAddress>>

    fun getBuyAddresses(): Single<List<BuyAddress>>

}