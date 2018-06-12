package ru.sergeykamyshov.rostovtransport.data.network.news

import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("date")
    lateinit var date: String

    @SerializedName("author")
    lateinit var author: Author

    @SerializedName("attachments")
    lateinit var attachments: List<Attachments>

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

            class Thumbnail {
                @SerializedName("url")
                lateinit var url: String
            }
        }
    }
}
