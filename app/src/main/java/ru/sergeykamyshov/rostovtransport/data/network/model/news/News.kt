package ru.sergeykamyshov.rostovtransport.data.network.model.news

import com.google.gson.annotations.SerializedName

class News {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("posts")
    lateinit var posts: List<Post>

    class Post {

        @SerializedName("id")
        lateinit var id: String

        @SerializedName("title")
        lateinit var title: String

        @SerializedName("content")
        lateinit var content: String

        @SerializedName("date")
        lateinit var date: String

        @SerializedName("author")
        lateinit var author: Author

        @SerializedName("attachments")
        lateinit var attachments: List<Attachment>

        @SerializedName("thumbnail")
        var thumbnail: String? = ""

        @SerializedName("thumbnail_images")
        lateinit var thumbnailImages: ThumbnailImage

        class Author {
            @SerializedName("name")
            lateinit var name: String
        }

        class Attachment {
            @SerializedName("images")
            var images: Image? = null
        }

        class Image {
            @SerializedName("thumbnail")
            lateinit var thumbnail: Thumbnail

            @SerializedName("medium")
            lateinit var medium: Thumbnail
        }

        class ThumbnailImage {
            @SerializedName("medium")
            lateinit var medium: Thumbnail
        }

        class Thumbnail {
            @SerializedName("url")
            lateinit var url: String
        }
    }

}