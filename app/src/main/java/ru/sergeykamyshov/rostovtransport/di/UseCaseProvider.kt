package ru.sergeykamyshov.rostovtransport.di

import ru.sergeykamyshov.rostovtransport.domain.card.GetDepositAddresses
import ru.sergeykamyshov.rostovtransport.domain.help.GetBusiness
import ru.sergeykamyshov.rostovtransport.domain.help.GetDepartments
import ru.sergeykamyshov.rostovtransport.domain.help.GetStations
import ru.sergeykamyshov.rostovtransport.domain.news.GetPost
import ru.sergeykamyshov.rostovtransport.domain.news.GetRecentNews

class UseCaseProvider(dataSource: DataSourceProvider) {

    val getRecentNews = GetRecentNews(dataSource.news)
    val getPost = GetPost(dataSource.news)

    val getDepartments = GetDepartments(dataSource.help)
    val getStations = GetStations(dataSource.help)
    val getBusiness = GetBusiness(dataSource.help)

    val getDepositAddresses = GetDepositAddresses(dataSource.card)

}