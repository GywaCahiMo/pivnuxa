package com.example.pivnyxa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class MainActivity : AppCompatActivity() {
    companion object{
        var totalPrice: Int = 0
    }
    private lateinit var AllText: Array<TextView>

    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
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
        //id текстов
        val textIds = intArrayOf(R.id.textLindeman, R.id.textFuller, R.id.textChimay, R.id.textSchineider,
            R.id.textChimayB, R.id.textBernardus, R.id.textFullerLondon, R.id.textAecht, R.id.textDelirium,
            R.id.textDuvel, R.id.textEngel, R.id.textFloris, R.id.textGuldenDraak, R.id.textBrothers,
            R.id.textRaspberry, R.id.textRhubarb, R.id.textStrawberry, R.id.textToffee, R.id.textWild)
        //инициализируем AllText
        AllText = Array(19) { TextView(this) }
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
            putInt("totalPrice", totalPrice)
            for (i in AllText.indices) {
                putString("textView_$i", AllText[i].text.toString())
            }
            apply()
        }
    }
    fun RestoringTheStateOfTheTextView() {
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        totalPrice = sharedPref.getInt("totalPrice", 0)
        for (i in AllText.indices) {
            AllText[i].text = sharedPref.getString("textView_$i", "0")
        }
    }
    fun setOnClickListenerAllButton(){
        val buttonIds = intArrayOf(R.id.button, R.id.button2, R.id.buttonFullerMinus, R.id.buttonFullerPlus,
            R.id.buttonChimayMinus, R.id.buttonChimayPlus,R.id.buttonSchineiderMinus, R.id.buttonSchineiderPlus,
            R.id.buttonChimayBMinus, R.id.buttonChimayBPlus,R.id.buttonBernardusMinus, R.id.buttonBernardusPlus,
            R.id.buttonFullerLondonMinus, R.id.buttonFullerLondonPlus, R.id.buttonAechtMinus, R.id.buttonAechtPlus,
            R.id.buttonDeliriumMinus, R.id.buttonDeliriumPlus, R.id.buttonDuvelMinus, R.id.buttonDuvelPlus,
            R.id.buttonEngelMinus, R.id.buttonEngelPlus, R.id.buttonFlorisMinus, R.id.buttonFlorisPlus,
            R.id.buttonGuldenDraakMinus, R.id.buttonGuldenDraakPlus, R.id.buttonBrothersMinus, R.id.buttonBrothersPlus,
            R.id.buttonRaspberryMinus, R.id.buttonRaspberryPlus, R.id.buttonRhubarbMinus, R.id.buttonRhubarbPlus,
            R.id.buttonStrawberryMinus, R.id.buttonStrawberryPlus, R.id.buttonToffeeMinus, R.id.buttonToffeePlus,
            R.id.buttonWildMinus, R.id.buttonWildPlus,)
        val values = intArrayOf(305, 399, 415, 505, 595, 469, 385, 3879, 525, 465, 379, 335, 339, 429, 415, 415, 415, 415, 415)
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
    fun goToSnaksActivity(view: View) {
        val intent = Intent(this, snaks::class.java)
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