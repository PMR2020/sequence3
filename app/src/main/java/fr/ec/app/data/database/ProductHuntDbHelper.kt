package fr.ec.app.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductHuntDbHelper(context: Context) : SQLiteOpenHelper(
    context,
    DataBaseContract.DATABASE_NAME,
    null,
    DataBaseContract.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DataBaseContract.PostTable.SQL_CREATE_POST_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL(DataBaseContract.PostTable.SQL_DROP_POST_TABLE)
        onCreate(db)
    }
}