package com.example.pivnyxa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView

class souvenirGlassesActivity : AppCompatActivity() {
    private lateinit var textGlassFuller : TextView
    private lateinit var textGlassFourchette : TextView
    private lateinit var textGlassGoliath : TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_souvenir_glasses)

        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollViewSouvenirGlasses)
        val yourLayout = findViewById<LinearLayout>(R.id.yourLayoutSouvenirGlasses)
        var initialY = 0f
        var lastY = 0f // Сохраняем последнее смещение

        nestedScrollView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaY = event.y - initialY + lastY // Применяем последнее смещение
                    yourLayout.translationY = deltaY
                }
                MotionEvent.ACTION_UP -> {
                    lastY = yourLayout.translationY // Сохраняем текущее смещение
                }
            }
            true
        }
        textGlassFuller = findViewById(R.id.textGlassFuller)
        textGlassFourchette = findViewById(R.id.textGlassFourchette)
        textGlassGoliath = findViewById(R.id.textGlassGoliath)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        textGlassFuller.text = sharedPref.getString("textGlassFuller", "0")
        textGlassFourchette.text = sharedPref.getString("textGlassFourchette", "0")
        textGlassGoliath.text = sharedPref.getString("textGlassGoliath", "0")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние TextView перед уничтожением активити
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("totalPrice", MainActivity.totalPrice)
            putString("textGlassFuller", textGlassFuller.text.toString())
            putString("textGlassFourchette", textGlassFourchette.text.toString())
            putString("textGlassGoliath", textGlassGoliath.text.toString())
            apply()
        }
    }
    //------------------
    fun plussGlassFuller(view: View){
        plus(449,  textGlassFuller)
    }
    fun minusGlassFuller(view: View){
        minus(449, textGlassFuller)
    }
    //------------------
    fun plussGlassFourchette(view: View){
        plus(675,  textGlassFourchette)
    }
    fun minusGlassFourchette(view: View){
        minus(675, textGlassFourchette)
    }
    //------------------
    fun plussGlassGoliath(view: View){
        plus(749,  textGlassGoliath)
    }
    fun minusGlassGoliath(view: View){
        minus(749, textGlassGoliath)
    }
    //------------------

    fun plus(changeTotalPrice:Int, textCount: TextView){
        val newCount: Int = textCount.text.toString().toInt() + 1
        textCount.text = newCount.toString()
        MainActivity.totalPrice += changeTotalPrice
        Log.i("totalPrice", "${MainActivity.totalPrice})")
    }
    fun minus(changeTotalPrice:Int, textCount: TextView){
        if (textCount.text.toString().toInt() > 0){
            val newCount: Int = textCount.text.toString().toInt() - 1
            textCount.text = newCount.toString()
            MainActivity.totalPrice -= changeTotalPrice
            Log.i("totalPrice", "${MainActivity.totalPrice})")
        }
    }
    fun goToSnaksActivity(view: View){
        val intent = Intent(this, snaks::class.java)
        startActivity(intent)
    }
    fun goToMainActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun applicationActivity(view: View){
        val intent = Intent(this, applicationActivity::class.java)
        startActivity(intent)
    }
}