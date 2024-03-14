package com.example.pivnyxa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView

class souvenirGlassesActivity : AppCompatActivity() {
    private lateinit var AllText: Array<TextView>

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
        //id текстов
        val textIds = intArrayOf(R.id.textGlassFuller, R.id.textGlassFourchette,
            R.id.textGlassGoliath, R.id.textGlassKozel, R.id.textGlassBrugse, R.id.textGlassPICHET)
        //инициализируем AllText
        AllText = Array(6) { TextView(this) }
        for(i in AllText.indices){
            AllText[i] = findViewById(textIds[i])
        }
        RestoringTheStateOfTheTextView()
        setOnClickListenerAllButton()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            for (i in AllText.indices) {
                putString("textViewGlasses_$i", AllText[i].text.toString())
            }
            apply()
        }
    }
    fun RestoringTheStateOfTheTextView() {
        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        for (i in AllText.indices) {
            AllText[i].text = sharedPref.getString("textViewGlasses_$i", "0")
        }
    }
    fun setOnClickListenerAllButton(){
        val buttonIds = intArrayOf(R.id.buttonGlassFullerMinus, R.id.buttonGlassFullerPlus,
            R.id.buttonGlassFourchetteMinus, R.id.buttonGlassFourchettePlus, R.id.buttonGlassGoliathMinus, R.id.buttonGlassGoliathPlus,
            R.id.buttonGlassKozelMinus, R.id.buttonGlassKozelPlus, R.id.buttonGlassBrugseMinus, R.id.buttonGlassBrugsePlus,
            R.id.buttonGlassPICHETMinus, R.id.buttonGlassPICHETPlus,)
        val values = intArrayOf(449, 675, 749, 389, 579, 1379)
        for ((j, i) in (buttonIds.indices step 2).withIndex()) {
            val buttonMinus: Button = findViewById(buttonIds[i])
            val buttonPlus: Button = findViewById(buttonIds[i + 1])
            buttonMinus.setOnClickListener { minus(values[j], AllText[j]) }
            buttonPlus.setOnClickListener { plus(values[j], AllText[j]) }
        }
    }
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