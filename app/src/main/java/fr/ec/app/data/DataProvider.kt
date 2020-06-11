package fr.ec.app.data

import fr.ec.app.data.api.ServiceProductHunt
import fr.ec.app.data.api.model.PostResponse
import fr.ec.app.data.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DataProvider {
    val BASE_URL = "https://api.producthunt.com/"

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceProductHunt::class.java)

    suspend fun getPostFromApi(): List<Post> = service.getPostsResponse().posts.toPost()

    private fun List<PostResponse>.toPost() = this.map { postResponse ->
        Post(
            title = postResponse.title,
            subTitle = postResponse.subTitle,
            imageUrl = postResponse.thumbnail.imageUrl
        )

    }
}