package fr.ec.app.data.database

import android.provider.BaseColumns

object DataBaseContract {
    const val DATABASE_NAME = "database"
    const val DATABASE_VERSION = 1

    /** Description de la table des Posts  */
    object PostTable : BaseColumns {
        const val TABLE_NAME = "post"
        const val ID_COLUMN = "id"
        const val TITLE_COLUMN = "title"
        const val SUBTITLE_COLUMN = "subtitle"
        const val IMAGE_URL_COLUMN = "imageUrl"

        val SQL_CREATE_POST_TABLE = """
             |CREATE TABLE $TABLE_NAME ( 
                                     |$ID_COLUMN INTEGER PRIMARY KEY,
                                     |$TITLE_COLUMN TEXT, 
                                     |$SUBTITLE_COLUMN TEXT, 
                                     |$IMAGE_URL_COLUMN  TEXT
                                |)
             """.trimMargin()

        const val SQL_DROP_POST_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

        val PROJECTIONS = arrayOf(
            ID_COLUMN,
            TITLE_COLUMN,
            SUBTITLE_COLUMN,
            IMAGE_URL_COLUMN
        )
    }
}