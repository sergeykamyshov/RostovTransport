package ru.sergeykamyshov.rostovtransport.data.news

import io.reactivex.Single
import ru.sergeykamyshov.rostovtransport.domain.news.NewsDataSource
import ru.sergeykamyshov.rostovtransport.domain.news.Post

class NewsRepository(private val newsApi: NewsApi) : NewsDataSource {

    override fun getRecentNews(): Single<List<Post>> {
        return newsApi.getRecentNews()
                .map { it.posts.map { item -> item.toPost() } }
    }

    override fun getPost(id: String): Single<Post> {
        return newsApi.getPost(id)
                .map { it.post.toPost() }
    }

}