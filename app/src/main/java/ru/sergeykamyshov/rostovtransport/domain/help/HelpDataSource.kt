package ru.sergeykamyshov.rostovtransport.domain.help

import io.reactivex.Single

interface HelpDataSource {

    fun getDepartments(): Single<List<Contact>>
//    fun getStations(): Single<List<String>>
//    fun getBusiness(): Single<List<String>>

}