package com.example.pivnyxa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class MainActivity : AppCompatActivity() {
    companion object{
        var totalPrice: Int = 0
    }

    private lateinit var textLindeman : TextView
    private lateinit var textFuller : TextView
    private lateinit var textChimay : TextView
    private lateinit var textSchineider : TextView
    private lateinit var textChimayB : TextView
    private lateinit var textBernardus : TextView
    private lateinit var textFullerLondon : TextView
    private lateinit var textAecht : TextView
    private lateinit var textDelirium : TextView
    private lateinit var textDuvel : TextView
    private lateinit var textEngel : TextView
    private lateinit var textFloris : TextView
    private lateinit var textGuldenDraak : TextView

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
        textBernardus = findViewById(R.id.textBernardus)
        textFullerLondon = findViewById(R.id.textFullerLondon)
        textAecht = findViewById(R.id.textAecht)
        textDelirium = findViewById(R.id.textDelirium)
        textDuvel = findViewById(R.id.textDuvel)
        textEngel = findViewById(R.id.textEngel)
        textFloris = findViewById(R.id.textFloris)
        textGuldenDraak = findViewById(R.id.textGuldenDraak)

        // Восстанавливаем состояние TextView, если оно было сохранено ранее
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        totalPrice = sharedPref.getInt("totalPrice",0)
        textLindeman.text = sharedPref.getString("textLindeman", "0")
        textFuller.text = sharedPref.getString("textFuller", "0")
        textChimay.text = sharedPref.getString("textChimay", "0")
        textSchineider.text = sharedPref.getString("textSchineider", "0")
        textChimayB.text = sharedPref.getString("textChimayB", "0")
        textBernardus.text = sharedPref.getString("textBernardus", "0")
        textFullerLondon.text = sharedPref.getString("textFullerLondon", "0")
        textAecht.text = sharedPref.getString("textAecht", "0")
        textDelirium.text = sharedPref.getString("textDelirium", "0")
        textDuvel.text = sharedPref.getString("textDuvel", "0")
        textEngel.text = sharedPref.getString("textEngel", "0")
        textFloris.text = sharedPref.getString("textFloris", "0")
        textGuldenDraak.text = sharedPref.getString("textGuldenDraak", "0")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем состояние TextView перед уничтожением активити
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("totalPrice", totalPrice)
            putString("textLindeman", textLindeman.text.toString())
            putString("textFuller", textFuller.text.toString())
            putString("textChimay", textChimay.text.toString())
            putString("textSchineider", textSchineider.text.toString())
            putString("textChimayB", textChimayB.text.toString())
            putString("textBernardus", textBernardus.text.toString())
            putString("textFullerLondon", textFullerLondon.text.toString())
            putString("textAecht", textAecht.text.toString())
            putString("textDelirium", textDelirium.text.toString())
            putString("textDuvel", textDuvel.text.toString())
            putString("textEngel", textEngel.text.toString())
            putString("textFloris", textFloris.text.toString())
            putString("textGuldenDraak", textGuldenDraak.text.toString())
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
    fun plussBernardus(view: View){
        plus(469,  textBernardus)
    }
    fun minusBernardus(view: View){
        minus(469, textBernardus)
    }
    //------------------
    fun plussFullerLondon(view: View){
        plus(385,  textFullerLondon)
    }
    fun minusFullerLondon(view: View){
        minus(385, textFullerLondon)
    }
    //------------------
    fun plussAecht(view: View){
        plus(3879,  textAecht)
    }
    fun minusAecht(view: View){
        minus(3879, textAecht)
    }
    //------------------
    fun plussDelirium(view: View){
        plus(525,  textDelirium)
    }
    fun minusDelirium(view: View){
        minus(525, textDelirium)
    }
    //------------------
    fun plussDuvel(view: View){
        plus(465,  textDuvel)
    }
    fun minusDuvel(view: View){
        minus(465, textDuvel)
    }
    //------------------
    fun plussEngel(view: View){
        plus(379,  textEngel)
    }
    fun minusEngel(view: View){
        minus(379, textEngel)
    }
    //------------------
    fun plussFloris(view: View){
        plus(335,  textFloris)
    }
    fun minusFloris(view: View){
        minus(335, textFloris)
    }
    //------------------
    fun plussGuldenDraak(view: View){
        plus(339,  textGuldenDraak)
    }
    fun minusGuldenDraak(view: View){
        minus(339, textGuldenDraak)
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
    fun goToSouvenirGlassesActivity(view: View){
        val intent = Intent(this, souvenirGlassesActivity::class.java)
        startActivity(intent)
    }
    fun applicationActivity(view: View){
        val intent = Intent(this, applicationActivity::class.java)
        startActivity(intent)
    }
}