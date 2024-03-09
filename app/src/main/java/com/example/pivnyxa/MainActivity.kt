package com.example.pivnyxa

import android.annotation.SuppressLint
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
    var totalPrice: Int = 0

    var countLindemans: Int = 0
    var countFuller: Int = 0
    var countChimay: Int = 0

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
    }
    //------------------
    fun plussLindeman(view: View){
        val textLindeman : TextView = findViewById(R.id.textLindeman)
        plus(countLindemans,305,  textLindeman)
        countLindemans = textLindeman.text.toString().toInt()
    }
    fun minusLindeman(view: View){
        val textLindeman : TextView = findViewById(R.id.textLindeman)
        minus(countLindemans,305, textLindeman)
        countLindemans = textLindeman.text.toString().toInt()
    }
    //------------------
    fun plussFuller(view: View){
        val textLindeman : TextView = findViewById(R.id.textFuller)
        plus(countFuller,399,  textLindeman)
        countFuller = textLindeman.text.toString().toInt()
    }
    fun minusFuller(view: View){
        val textLindeman : TextView = findViewById(R.id.textFuller)
        minus(countFuller,399, textLindeman)
        countFuller = textLindeman.text.toString().toInt()
    }
    //------------------
    fun plussChimay(view: View){
        val textLindeman : TextView = findViewById(R.id.textChimay)
        plus(countChimay,399,  textLindeman)
        countChimay = textLindeman.text.toString().toInt()
    }
    fun minusChimay(view: View){
        val textLindeman : TextView = findViewById(R.id.textChimay)
        minus(countChimay,399, textLindeman)
        countChimay = textLindeman.text.toString().toInt()
    }
    //------------------
    fun plus(count: Int, changeTotalPrice:Int, textCount: TextView){
        val newCount = count + 1
        textCount.text = newCount.toString()
        totalPrice += changeTotalPrice
        Log.i("totalPrice", "$totalPrice)")
    }
    fun minus(count: Int, changeTotalPrice:Int, textCount: TextView){
        if (count > 0){
            val newCount = count - 1
            textCount.text = newCount.toString()
            totalPrice -= changeTotalPrice
            Log.i("totalPrice", "$totalPrice)")
        }
    }
    fun goToReactionGameActivity(view: View){
        //val intent = Intent(this, CartActivity::class.java)
        //startActivity(intent)
    }
}