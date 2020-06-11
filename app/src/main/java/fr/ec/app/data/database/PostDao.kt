package fr.ec.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ec.app.data.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM post ")
    suspend fun getPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(posts: List<Post>)
}