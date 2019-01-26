package ru.sergeykamyshov.rostovtransport.domain.help

import io.reactivex.Single

class GetDepartments(private val dataSource: HelpDataSource) {

    fun execute(): Single<List<Contact>> {
        return dataSource.getDepartments()
    }

}