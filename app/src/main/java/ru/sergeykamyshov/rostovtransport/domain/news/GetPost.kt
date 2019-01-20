package ru.sergeykamyshov.rostovtransport.domain.news

import io.reactivex.Single

class GetPost(private val newsDataSource: NewsDataSource) {

    fun execute(id: String): Single<Post> {
        return newsDataSource.getPost(id)
    }

}