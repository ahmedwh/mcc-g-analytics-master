package com.hamada.sinwar.myapplication.app

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.hamada.sinwar.myapplication.models.Product

class App:Application() {
    lateinit var products :MutableList<Product>
    lateinit var mFirebaseAnalytics:FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        products = mutableListOf()
        mFirebaseAnalytics = Firebase.analytics
    }

    fun trackScreen(screenName:String, className:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, className)
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}