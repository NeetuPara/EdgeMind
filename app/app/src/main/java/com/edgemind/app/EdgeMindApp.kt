package com.edgemind.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EdgeMindApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
