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
    private lateinit var Products: Array<String>
    var ProductsAll: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        textPhone = findViewById(R.id.editTextPhone)
        textAddress = findViewById(R.id.editTextAddress)

        val textTotalPrice: TextView = findViewById(R.id.textViewTotalPrice)
        textTotalPrice.text = totalPrice.toString()

        Products = Array(33, { "" })

        val sharedPref = getSharedPreferences("MyApp", MODE_PRIVATE)
        for (i in Products.indices) {
            if(i <= 19){
                Products[i] = sharedPref.getString("textView_$i", "0").toString()
            }
            if(i in 20..27){
                Products[i] = sharedPref.getString("textViewSnaks_$i", "0").toString()
            }
            if(i > 27){
                Products[i] = sharedPref.getString("textViewGlasses_$i", "0").toString()
            }
        }
        for (i in Products.indices){
            ProductsAll += Products[i] + " "
        }
    }
    fun sendARequest(view: View){
        val dbHelper = DatabaseHelper(this)
        dbHelper.addingAnApplication(textPhone.text.toString(), textAddress.text.toString(),ProductsAll)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}