package com.example.coursework

import android.content.ContentValues
import android.content.Context

class TablePrice(context: Context) {
    val dbHelper = DBHelper(context)
    val db = dbHelper.writableDatabase

    var cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)

    val indexId = cursor.getColumnIndex(DBHelper.COLUMN_ID)
    val indexName = cursor.getColumnIndex(DBHelper.COLUMN_NAME)
    val indexDescription = cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPTION)
    val indexPrice = cursor.getColumnIndex(DBHelper.COLUMN_PRICE)

    var priceSingleton = PriceSingleton.getInstance()

    fun load_from_table(){
        cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_NAME}", null)
        cursor.moveToFirst()

        var i = 0
        while(!cursor.isAfterLast){
            priceSingleton.id[i] = cursor.getInt(indexId)
            priceSingleton.name[i] = cursor.getString(indexName)
            priceSingleton.description[i] = cursor.getString(indexDescription)
            priceSingleton.price[i] = cursor.getString(indexPrice)

            i++
            cursor.moveToNext()
        }
    }

    fun add_to_table(name: String, description: String, price: Int){
        val cv = ContentValues().apply {
            put(DBHelper.COLUMN_NAME, name)
            put(DBHelper.COLUMN_DESCRIPTION, description)
            put(DBHelper.COLUMN_PRICE, price)
        }

        db.insert(DBHelper.TABLE_NAME, null, cv)
    }

    fun count(): Int{
        cursor = db.rawQuery("SELECT COUNT(*) FROM ${DBHelper.TABLE_NAME}", null)
        cursor.moveToFirst()

        return cursor.getInt(0)
    }

    fun returnItemData(id: Int): Array<String>{
        cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_NAME} " +
                "WHERE ${DBHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        cursor.moveToFirst()

        return arrayOf(cursor.getString(indexName),
            cursor.getString(indexDescription),
            cursor.getInt(indexPrice).toString())
    }

    fun changeName(id: Int, name: String){
        val cv = ContentValues().apply {
            put(DBHelper.COLUMN_NAME, name)
        }

        db.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = " + id.toString(), null)
    }

    fun changeDescription(id: Int, description: String){
        val cv = ContentValues().apply {
            put(DBHelper.COLUMN_DESCRIPTION, description)
        }

        db.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = " + id.toString(), null)
    }

    fun changePrice(id: Int, price: Int){
        val cv = ContentValues().apply {
            put(DBHelper.COLUMN_PRICE, price)
        }

        db.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = " + id.toString(), null)
    }

    fun deletePosition(id: Int){
        cursor = db.rawQuery("DELETE FROM ${DBHelper.TABLE_NAME} " +
                "WHERE ${DBHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        cursor.moveToFirst()

    }

    fun updateTableData(){
        cursor = db.rawQuery(" SELECT * FROM ${DBHelper.TABLE_NAME}", null)
        val size = cursor.count
        cursor.moveToFirst()

        var names: ArrayList<String> = arrayListOf()
        var descriptions: ArrayList<String> = arrayListOf()
        var prices: ArrayList<Int> = arrayListOf()

        while(!cursor.isAfterLast){
            names.add(cursor.getString(indexName))
            descriptions.add(cursor.getString(indexDescription))
            prices.add(cursor.getInt(indexPrice))

            cursor.moveToNext()
        }

        deleteTableData()

        for(i in 0..size - 1){
            val cv = ContentValues().apply {
                put(DBHelper.COLUMN_NAME, names[i])
                put(DBHelper.COLUMN_DESCRIPTION, descriptions[i])
                put(DBHelper.COLUMN_PRICE, prices[i])
            }

            db.insert(DBHelper.TABLE_NAME, null, cv)
        }
    }

    fun deleteTableData(){
        cursor = db.rawQuery("DELETE FROM ${DBHelper.TABLE_NAME}", null)
        cursor.moveToFirst()

        cursor =db.rawQuery("UPDATE sqlite_sequence " +
                "SET seq = 0 " +
                "WHERE Name = ?", arrayOf(DBHelper.TABLE_NAME))
        cursor.moveToFirst()
    }

    fun checkId(id: Int): Boolean{
            cursor = db.rawQuery(
                "SELECT * FROM ${DBHelper.TABLE_NAME}", null)
            cursor.moveToFirst()

        return !(id == 0 || id > cursor.count)
    }
}