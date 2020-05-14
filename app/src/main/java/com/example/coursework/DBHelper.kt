package com.example.coursework

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    companion object{
        val DB_NAME = "PriceList.db"
        val DB_VERSION = 1

        val TABLE_NAME = "PriceList"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE ${TABLE_NAME} (" +
                "${COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COLUMN_NAME} TEXT, " +
                "${COLUMN_DESCRIPTION} TEXT, " +
                "${COLUMN_PRICE} INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}