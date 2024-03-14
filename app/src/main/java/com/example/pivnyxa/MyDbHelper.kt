package com.example.pivnyxa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "UserData.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "UserTable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PRODUCTS = "products"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_PHONE TEXT, $COLUMN_ADDRESS TEXT,$COLUMN_PRODUCTS TEXT)"
        db.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addingAnApplication(phone: String, address: String, products: String ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_ADDRESS, address)
        values.put(COLUMN_PRODUCTS, products)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}