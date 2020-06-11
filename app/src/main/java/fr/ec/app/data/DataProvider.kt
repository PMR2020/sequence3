package fr.ec.app.data

import android.app.Application
import androidx.room.Room
import fr.ec.app.data.api.ServiceProductHunt
import fr.ec.app.data.api.model.PostResponse
import fr.ec.app.data.database.ProductHuntRoomDatabase
import fr.ec.app.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CancellationException


class DataProvider(context: Application) {
    val BASE_URL = "https://api.producthunt.com/"

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceProductHunt::class.java)

    private val roomDatabase =
        Room.databaseBuilder(context, ProductHuntRoomDatabase::class.java, "database").build()

    suspend fun getPosts(): List<Post> = try {
        service.getPostsResponse().posts.toPost().also {
            roomDatabase.postDao().save(it)
        }
    } catch (e: Exception) {
        if (e is CancellationException) {
            throw e
        }

        roomDatabase.postDao().getPosts()
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