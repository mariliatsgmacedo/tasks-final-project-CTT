package com.macedos.mytasksfinalproject

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object{
        private lateinit var appContext:Context
        fun getContext(): Context{
            return appContext
        }
    }
}