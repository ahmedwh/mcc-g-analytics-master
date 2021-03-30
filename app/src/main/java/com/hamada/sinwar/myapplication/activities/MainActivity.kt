package com.hamada.sinwar.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.hamada.sinwar.myapplication.R
import com.hamada.sinwar.myapplication.app.App
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var i :Intent
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var app:App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app = (application as App)
        mFirebaseAnalytics = app.mFirebaseAnalytics
        i = Intent(this, ProductsActivity::class.java)
        app.trackScreen("Main Screen", "MainActivity")

        btn_food.setOnClickListener {
            i.putExtra("category", "Food")
            openCategory("1", "Food")
            startActivity(i)
        }
        btn_clothes.setOnClickListener {
            i.putExtra("category", "Clothes")
            openCategory("2", "Clothes")
            startActivity(i)
        }
        btn_electronics.setOnClickListener {
            i.putExtra("category", "Electronics")
            openCategory("3", "Electronics")
            startActivity(i)
        }

    }

    private fun openCategory(id:String, name:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "category")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}