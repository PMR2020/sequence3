package fr.ec.app.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import fr.ec.app.data.model.Post
import fr.ec.app.data.model.toContentValues

class PostDao(context: Context) {

    private val productHuntDbHelper = ProductHuntDbHelper(context)

    fun save(post: Post): Long {
        return productHuntDbHelper.writableDatabase
            .insertWithOnConflict(
                DataBaseContract.PostTable.TABLE_NAME,
                null,
                post.toContentValues(),
                CONFLICT_REPLACE
            )
    }

    fun retrievePosts(): List<Post> {
        val cursor = productHuntDbHelper.readableDatabase
            .query(
                DataBaseContract.PostTable.TABLE_NAME,
                DataBaseContract.PostTable.PROJECTIONS,
                null,
                null,
                null,
                null,
                null
            )
        val posts: MutableList<Post> = mutableListOf()
        if (cursor.moveToFirst()) {
            do {
                val post = Post(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                posts.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return posts
    }

}