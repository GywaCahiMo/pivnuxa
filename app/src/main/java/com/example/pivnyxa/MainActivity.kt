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

class MainActivity : AppCompatActivity() {
    companion object {
        var totalPrice: Int = 0
    }

    private lateinit var textLindeman : TextView
    private lateinit var textFuller : TextView
    private lateinit var textChimay : TextView
    private lateinit var textSchineider : TextView
    private lateinit var textChimayB : TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val yourLayout = findViewById<LinearLayout>(R.id.yourLayout)
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
        textLindeman = findViewById(R.id.textLindeman)
        textFuller  = findViewById(R.id.textFuller)
        textChimay = findViewById(R.id.textChimay)
        textSchineider = findViewById(R.id.textSchineider)
        textChimayB = findViewById(R.id.textChimayB)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        textLindeman.text = sharedPref.getString("textLindeman", "0")
        textFuller.text = sharedPref.getString("textFuller", "0")
        textChimay.text = sharedPref.getString("textChimay", "0")
        textSchineider.text = sharedPref.getString("textSchineider", "0")
        textChimayB.text = sharedPref.getString("textChimayB", "0")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние TextView перед уничтожением активити
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("textLindeman", textLindeman.text.toString())
            putString("textFuller", textFuller.text.toString())
            putString("textChimay", textChimay.text.toString())
            putString("textSchineider", textSchineider.text.toString())
            putString("textChimayB", textChimayB.text.toString())
            apply()
        }
    }
    //------------------
    fun plussLindeman(view: View){
        plus(305,  textLindeman)
    }
    fun minusLindeman(view: View){
        minus(305, textLindeman)
    }

    //------------------
    fun plussFuller(view: View){
        plus(399,  textFuller)
    }
    fun minusFuller(view: View){
        minus(399, textFuller)
    }
    //------------------
    fun plussChimay(view: View){
        plus(415,  textChimay)
    }
    fun minusChimay(view: View){
        minus(415, textChimay)
    }
    //------------------
    fun plussSchineider(view: View){
        plus(505,  textSchineider)
    }
    fun minusSchineider(view: View){
        minus(505, textSchineider)
    }
    //------------------
    fun plussChimayB(view: View){
        plus(595,  textChimayB)
    }
    fun minusChimayB(view: View){
        minus(595, textChimayB)
    }
    //------------------
    fun plus(changeTotalPrice:Int, textCount: TextView){
        val newCount: Int = textCount.text.toString().toInt() + 1
        textCount.text = newCount.toString()
        totalPrice += changeTotalPrice
        Log.i("totalPrice", "$totalPrice)")
    }
    fun minus(changeTotalPrice:Int, textCount: TextView){
        if (textCount.text.toString().toInt() > 0){
            val newCount: Int = textCount.text.toString().toInt() - 1
            textCount.text = newCount.toString()
            totalPrice -= changeTotalPrice
            Log.i("totalPrice", "$totalPrice)")
        }
    }
    fun goToSnaksActivity(view: View){
        val intent = Intent(this, snaks::class.java)
        startActivity(intent)
    }
}