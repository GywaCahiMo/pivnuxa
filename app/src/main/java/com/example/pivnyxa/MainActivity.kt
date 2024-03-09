package com.example.pivnyxa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import com.example.pivnyxa.CartActivity.Companion.productList

class MainActivity : AppCompatActivity() {

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

        productList = mutableListOf(Product("Product 1", 10.0), Product("Product 2", 20.0))
        //(productList as MutableList<Product>).add(Product("Product 3", 10.0))
        //(productList as MutableList<Product>).remove(Product("Product 3", 10.0))

        addBacket("aaa", 439.0)
        removeBacket("ff", 45.0)
    }
    fun addBacket(name: String, price: Double){
        if(productList.contains(Product(name, price))){
            Log.i("meLog","true")

        }
        else{
            (productList as MutableList<Product>).add(Product(name, price))
        }
    }
    fun removeBacket(name: String, price: Double){
        if(!productList.contains(Product(name, price))){
            Log.i("meLog","false")
        }
        (productList as MutableList<Product>).remove(Product("Product 3", 10.0))
    }


    fun goToReactionGameActivity(view: View){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
}