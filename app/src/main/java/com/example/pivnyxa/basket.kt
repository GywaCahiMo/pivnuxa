package com.example.pivnyxa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Product(val name: String, val price: Double)


class CartAdapter(private val productList: List<Product>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.textViewItemName.text = product.name
        holder.textViewItemPrice.text = product.price.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItemName: TextView = itemView.findViewById(R.id.text_view_item_name)
        val textViewItemPrice: TextView = itemView.findViewById(R.id.text_view_item_price)
    }
}