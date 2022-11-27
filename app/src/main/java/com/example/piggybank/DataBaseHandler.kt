package com.example.piggybank

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME = "piggyDB"
val TABLE_NAME = "product"
val COL_PRODUCT="ProductName"
val COL_PRICE="ProductPrice"
val COL_DATE="Date"
val COL_ID="id"
class DataBaseHandler (var context: Context) :SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_PRODUCT + " VARCHAR(256)," +
                COL_PRICE + " INTEGER," +
                COL_DATE + " VARCHAR(256))";

        p0?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addData(user : userInfo){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_PRODUCT, user.prodName)
        cv.put(COL_PRICE, user.price)
        cv.put(COL_DATE, user.date)

        var result = db.insert(TABLE_NAME, null, cv)
        if(result==(-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

    }

}