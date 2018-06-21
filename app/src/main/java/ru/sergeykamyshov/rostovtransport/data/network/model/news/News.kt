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
        lateinit var attachments: List<Attachments>

        @SerializedName("thumbnail")
        var thumbnail: String? = ""

        class Author {
            @SerializedName("name")
            lateinit var name: String
        }

        class Attachments {
            @SerializedName("images")
            var images: Images? = null

            class Images {
                @SerializedName("thumbnail")
                lateinit var thumbnail: Thumbnail

                @SerializedName("medium")
                lateinit var medium: Thumbnail

                class Thumbnail {
                    @SerializedName("url")
                    lateinit var url: String
                }
            }
        }
    }

}