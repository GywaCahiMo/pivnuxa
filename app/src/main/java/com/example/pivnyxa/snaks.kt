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
    private lateinit var textBaranina : TextView
    private lateinit var textIndeika : TextView
    private lateinit var textYtka : TextView
    private lateinit var textChicken : TextView
    private lateinit var textPork : TextView

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
        textBaranina = findViewById(R.id.textBaranina)
        textIndeika = findViewById(R.id.textIndeika)
        textYtka = findViewById(R.id.textYtka)
        textChicken = findViewById(R.id.textChicken)
        textPork = findViewById(R.id.textPork)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        textStrays.text = sharedPref.getString("textStrays", "0")
        textGov.text = sharedPref.getString("textGov", "0")
        textConina.text = sharedPref.getString("textConina", "0")
        textBaranina.text = sharedPref.getString("textBaranina", "0")
        textIndeika.text = sharedPref.getString("textIndeika", "0")
        textYtka.text = sharedPref.getString("textYtka", "0")
        textChicken.text = sharedPref.getString("textChicken", "0")
        textPork.text = sharedPref.getString("textPork", "0")
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
            putString("textBaranina", textBaranina.text.toString())
            putString("textIndeika", textIndeika.text.toString())
            putString("textYtka", textYtka.text.toString())
            putString("textChicken", textChicken.text.toString())
            putString("textPork", textPork.text.toString())
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
    fun plussBaranina(view: View){
        plus(195,  textBaranina)
    }
    fun minusBaranina(view: View){
        minus(195, textBaranina)
    }
    //------------------
    fun plussIndeika(view: View){
        plus(175,  textIndeika)
    }
    fun minusIndeika(view: View){
        minus(175, textIndeika)
    }
    //------------------
    fun plussYtka(view: View){
        plus(195,  textYtka)
    }
    fun minusYtka(view: View){
        minus(195, textYtka)
    }
    //------------------
    fun plussChicken(view: View){
        plus(149,  textChicken)
    }
    fun minusChicken(view: View){
        minus(149, textChicken)
    }
    //------------------
    fun plussPork(view: View){
        plus(175,  textPork)
    }
    fun minusPork(view: View){
        minus(175, textPork)
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