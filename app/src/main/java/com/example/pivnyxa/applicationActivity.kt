package com.example.pivnyxa

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.pivnyxa.MainActivity.Companion.totalPrice

class applicationActivity : AppCompatActivity() {
    private lateinit var textPhone: EditText
    private lateinit var textAddress : EditText
    companion object{
        var Products : String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        textPhone = findViewById(R.id.editTextPhone)
        textAddress = findViewById(R.id.editTextAddress)

        val textTotalPrice: TextView = findViewById(R.id.textViewTotalPrice)
        textTotalPrice.text = totalPrice.toString()

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

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
    /*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние TextView перед уничтожением активити
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("totalPrice", totalPrice)
            apply()
        }
    }
     */
}