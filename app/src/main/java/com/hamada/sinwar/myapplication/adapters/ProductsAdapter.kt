package com.hamada.sinwar.myapplication.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hamada.sinwar.myapplication.R
import com.hamada.sinwar.myapplication.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(private val activity: Activity, private val data:MutableList<Product>, private val onClickItem: OnClickItem)
    : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    inner class ProductsViewHolder(item:View): RecyclerView.ViewHolder(item){
        val name: TextView = item.productName
        val img: ImageView = item.productImg
        val card: LinearLayout = item.card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.product_item, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.name.text = data[position].name
        Picasso.get().load(data[position].img).resize(500, 500).centerCrop().into(holder.img)
        holder.card.setOnClickListener {
            onClickItem.onClick(position)
        }
    }

    interface OnClickItem{
        fun onClick(position: Int)
    }
}
