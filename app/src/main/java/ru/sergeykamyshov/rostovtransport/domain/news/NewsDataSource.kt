package ru.sergeykamyshov.rostovtransport.domain.news

import io.reactivex.Single

interface NewsDataSource {

    fun getRecentNews(): Single<List<Post>>
    fun getPost(id: String): Single<Post>

}