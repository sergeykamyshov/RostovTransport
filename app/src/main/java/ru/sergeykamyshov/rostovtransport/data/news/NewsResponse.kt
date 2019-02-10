package ru.sergeykamyshov.rostovtransport.data.news

import com.google.gson.annotations.SerializedName
import ru.sergeykamyshov.rostovtransport.domain.news.Post as DomainPost

data class NewsResponse(
        @SerializedName("posts") val posts: List<Post>
)

data class PostResponse(
        @SerializedName("post") val post: Post
)

data class Post(
        @SerializedName("id") val id: String,
        @SerializedName("url") val url: String,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("date") val date: String,
        @SerializedName("author") val author: Author,
        @SerializedName("thumbnail") val thumbnail: String?,
        @SerializedName("thumbnail_images") val thumbnailImages: ThumbnailImages?
) {
    fun toPost() = DomainPost(
            id,
            url,
            title,
            content,
            date,
            author.name,
            thumbnail,
            thumbnailImages?.medium?.url,
            thumbnailImages?.mediumLarge?.url
    )
}

data class Author(
        @SerializedName("name") val name: String
)

data class ThumbnailImages(
        @SerializedName("medium") val medium: ThumbnailImage?,
        @SerializedName("medium_large") val mediumLarge: ThumbnailImage?
)

data class ThumbnailImage(
        @SerializedName("url") val url: String?
)
