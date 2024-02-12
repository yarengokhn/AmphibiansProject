package com.example.amphibiansproject

import android.app.Application
import com.example.amphibiansproject.data.AppContainer
import com.example.amphibiansproject.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
         container= DefaultAppContainer()
    }
}