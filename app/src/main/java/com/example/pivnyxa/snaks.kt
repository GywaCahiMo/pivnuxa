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
import com.example.pivnyxa.MainActivity.Companion.totalPrice

class snaks : AppCompatActivity() {
    private lateinit var textStrays : TextView
    private lateinit var textGov : TextView
    private lateinit var textConina : TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snaks)

        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollViewSnaks)
        val yourLayout = findViewById<LinearLayout>(R.id.yourLayoutSnaks)
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
        textStrays = findViewById(R.id.textStrays)
        textGov = findViewById(R.id.textGov)
        textConina = findViewById(R.id.textConina)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        textStrays.text = sharedPref.getString("textStrays", "0")
        textGov.text = sharedPref.getString("textGov", "0")
        textConina.text = sharedPref.getString("textConina", "0")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние TextView перед уничтожением активити
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("totalPrice", totalPrice)
            putString("textStrays", textStrays.text.toString())
            putString("textGov", textGov.text.toString())
            putString("textConina", textConina.text.toString())
            apply()
        }
    }
    //------------------
    fun plussStrays(view: View){
        plus(219,  textStrays)
    }
    fun minusStrays(view: View){
        minus(219, textStrays)
    }
    //------------------
    fun plussGov(view: View){
        plus(179,  textGov)
    }
    fun minusGov(view: View){
        minus(179, textGov)
    }
    //------------------
    fun plussConina(view: View){
        plus(185,  textConina)
    }
    fun minusConina(view: View){
        minus(185, textConina)
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
    fun goToMainActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun goToSouvenirGlassesActivity(view: View){
        val intent = Intent(this, souvenirGlassesActivity::class.java)
        startActivity(intent)
    }
    fun applicationActivity(view: View){
        val intent = Intent(this, applicationActivity::class.java)
        startActivity(intent)
    }
}