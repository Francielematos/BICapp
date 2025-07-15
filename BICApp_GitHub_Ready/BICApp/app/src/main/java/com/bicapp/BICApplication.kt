package com.bicapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BICApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
    }
}

