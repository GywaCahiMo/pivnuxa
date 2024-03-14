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
import com.example.pivnyxa.MainActivity.Companion.totalPrice

class snaks : AppCompatActivity() {
    private lateinit var AllText: Array<TextView>
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
        //id текстов
        val textIds = intArrayOf(R.id.textPork, R.id.textChicken, R.id.textYtka, R.id.textIndeika,
            R.id.textBaranina, R.id.textConina, R.id.textGov, R.id.textStrays)
        //инициализируем AllText
        AllText = Array(8) { TextView(this) }
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
                putString("textViewSnaks_$i", AllText[i].text.toString())
            }
            apply()
        }
    }
    fun RestoringTheStateOfTheTextView() {
        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        for (i in AllText.indices) {
            AllText[i].text = sharedPref.getString("textViewSnaks_$i", "0")
        }
    }
    fun setOnClickListenerAllButton(){
        val buttonIds = intArrayOf(R.id.buttonPorkMinus, R.id.buttonPorkPlus, R.id.buttonChickenMinus, R.id.buttonChickenPlus,
            R.id.buttonYtkaMinus, R.id.buttonYtkaPlus, R.id.buttonIndeikaMinus, R.id.buttonIndeikaPlus,
            R.id.buttonBaraninaMinus, R.id.buttonBaraninaPlus, R.id.buttonConinaMinus, R.id.buttonConinaPlus,
            R.id.buttonGovMinus, R.id.buttonGovPlus, R.id.buttonStraysMinus, R.id.buttonStraysPlus)
        val values = intArrayOf(175, 149, 195, 175, 195, 185, 179, 219)
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