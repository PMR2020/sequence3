package fr.ec.app.data.model

import android.content.ContentValues
import fr.ec.app.data.database.DataBaseContract


data class Post(
    val id : Long,
    val title: String,
    val subTitle: String,
    val imageUrl: String
)
fun Post.toContentValues(): ContentValues {
    val contentValues = ContentValues()
    contentValues.put(DataBaseContract.PostTable.ID_COLUMN, id)
    contentValues.put(DataBaseContract.PostTable.TITLE_COLUMN, title)
    contentValues.put(DataBaseContract.PostTable.SUBTITLE_COLUMN, subTitle)
    contentValues.put(DataBaseContract.PostTable.IMAGE_URL_COLUMN, imageUrl)
    return contentValues
}
