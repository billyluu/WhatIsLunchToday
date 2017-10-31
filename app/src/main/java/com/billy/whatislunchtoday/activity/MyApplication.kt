package com.billy.whatislunchtoday.activity

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Created by billylu on 2017/10/31.
 */
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)

    }
}