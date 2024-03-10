package com.example.pivnyxa

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class applicationActivity : AppCompatActivity() {
    private lateinit var textPhone: EditText
    private lateinit var textAddress : EditText
    private lateinit var Products : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        textPhone = findViewById(R.id.editTextPhone)
        textAddress = findViewById(R.id.editTextAddress)

    }
    fun sendARequest(view: View){
        val dbHelper = MyDbHelper(this)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(MyDbHelper.COLUMN_ID, 1)
            put(MyDbHelper.COLUMN_PHONE, textPhone.text.toString())
            put(MyDbHelper.COLUMN_ADDRESS, textAddress.text.toString())
            put(MyDbHelper.COLUMN_PRODUCTS, Products)
        }
        db.insert(MyDbHelper.TABLE_NAME, null, values)
    }
}