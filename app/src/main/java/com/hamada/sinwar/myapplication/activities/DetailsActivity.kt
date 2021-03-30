package com.hamada.sinwar.myapplication.activities

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import com.hamada.sinwar.myapplication.R
import com.hamada.sinwar.myapplication.app.App
import com.hamada.sinwar.myapplication.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    lateinit var app:App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.toolbar))

        app = (application as App)
        app.trackScreen("Details Screen", "DetailsActivity")

        val product = intent.getParcelableExtra<Product>("product")!!
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = product.name
        Picasso.get().load(product.img).into(imageDetails)
    }
}