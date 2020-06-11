package fr.ec.app.data

import android.content.Context
import fr.ec.app.data.api.ServiceProductHunt
import fr.ec.app.data.api.model.PostResponse
import fr.ec.app.data.database.PostDao
import fr.ec.app.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class DataProvider(context: Context) {
    val BASE_URL = "https://api.producthunt.com/"

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceProductHunt::class.java)

    private val dao = PostDao(context)

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        var listPost = emptyList<Post>()
        try {
            listPost =  service.getPostsResponse().posts.toPost()
            listPost.forEach {
                dao.save(it)
            }
        }catch (e : Exception) {
            listPost = dao.retrievePosts()
        }

        listPost
    }

    private fun List<PostResponse>.toPost() = this.map { postResponse ->
        Post(
            id = postResponse.id,
            title = postResponse.title,
            subTitle = postResponse.subTitle,
            imageUrl = postResponse.thumbnail.imageUrl
        )

    }
}