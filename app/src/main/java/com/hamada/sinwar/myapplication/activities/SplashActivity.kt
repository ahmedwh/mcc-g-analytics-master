package com.hamada.sinwar.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamada.sinwar.myapplication.R
import com.hamada.sinwar.myapplication.app.App
import com.hamada.sinwar.myapplication.models.Product

class SplashActivity : AppCompatActivity() {

    private lateinit var db:FirebaseFirestore
    lateinit var app:App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        app = application as App
        db = Firebase.firestore

        app.trackScreen("Splash Screen", "SplashActivity")

        db.collection("products").get().addOnSuccessListener {
            for (doc in it.documents){
                val p = Product(doc.id, "${doc.getString("name")}", "${doc.getString("img")}", "${doc.getString("category")}")
                app.products.add(p)
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}