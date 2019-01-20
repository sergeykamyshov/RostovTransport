package ru.sergeykamyshov.rostovtransport.domain.news

import io.reactivex.Single

class GetRecentNews(private val newsDataSource: NewsDataSource) {

    fun execute(): Single<List<Post>> {
        return newsDataSource.getRecentNews()
    }

}