package com.example.relook

import android.app.Application
import com.beardedhen.androidbootstrap.TypefaceProvider

class TestBootstrap : Application() {
    override fun onCreate() {
        super.onCreate()
        TypefaceProvider.registerDefaultIconSets()
    }
}