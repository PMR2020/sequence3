package fr.ec.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ec.app.data.model.Post

@Database(
    entities = [
        Post::class
    ], version = 1
)
abstract class ProductHuntRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}