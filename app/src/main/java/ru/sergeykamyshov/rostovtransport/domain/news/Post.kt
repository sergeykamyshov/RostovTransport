package ru.sergeykamyshov.rostovtransport.domain.news

class Post(
        val id: String,
        val url: String,
        val title: String,
        val content: String,
        val date: String,
        val authorName: String,
        val thumbnail: String?,
        val thumbnailMedium: String?,
        val thumbnailMediumLarge: String?
)