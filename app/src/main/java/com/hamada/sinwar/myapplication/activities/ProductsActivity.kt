package com.hamada.sinwar.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamada.sinwar.myapplication.R
import com.hamada.sinwar.myapplication.adapters.ProductsAdapter
import com.hamada.sinwar.myapplication.app.App
import com.hamada.sinwar.myapplication.models.Product
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity(), ProductsAdapter.OnClickItem {

    private lateinit var db:FirebaseFirestore
    private lateinit var data:MutableList<Product>
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var app:App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        app = (application as App)
        mFirebaseAnalytics = app.mFirebaseAnalytics
        db = Firebase.firestore
        data = mutableListOf()
        val category = "${intent.getStringExtra("category")}"

        for (p in (this.application as App).products){
            if (p.category == category){
                data.add(p)
            }
        }
        app.trackScreen("Products Screen", "ProductsActivity")

        val adapter = ProductsAdapter(this, data, this)
        rvProducts.layoutManager = GridLayoutManager(this, 2)
        rvProducts.adapter = adapter
    }

    override fun onClick(position: Int) {
        val i = Intent(this, DetailsActivity::class.java)
        i.putExtra("product", data[position])
        selectProduct(data[position].id, data[position].name)
        startActivity(i)
    }

    private fun selectProduct(id:String, name:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "product")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
    }
}