package com.example.pivnyxa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    private lateinit var recyclerViewCart: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    companion object {
        lateinit var productList: List<Product>
    }
    //lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        recyclerViewCart = findViewById(R.id.recycler_view_cart)
        recyclerViewCart.layoutManager = LinearLayoutManager(this)

        // Инициализация списка товаров в корзине (productList)
        //productList = listOf(Product("Product 1", 10.0), Product("Product 2", 20.0))
        cartAdapter = CartAdapter(productList)
        recyclerViewCart.adapter = cartAdapter
    }
}