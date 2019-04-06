package ru.sergeykamyshov.rostovtransport.domain.news

import io.reactivex.Single

class GetPost(private val newsDataSource: NewsDataSource) {

    fun execute(id: Long): Single<Post> {
        return newsDataSource.getPost(id)
    }

}