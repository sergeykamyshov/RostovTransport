package ru.sergeykamyshov.rostovtransport.domain.help

import io.reactivex.Single

class GetBusiness(private val dataSource: HelpDataSource) {

    fun execute(): Single<List<Contact>> {
        return dataSource.getBusiness()
    }

}