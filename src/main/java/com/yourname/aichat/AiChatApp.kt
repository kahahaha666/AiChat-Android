package com.yourname.aichat

import android.app.Application
import com.yourname.aichat.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AiChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AiChatApp)
            modules(appModule)
        }
    }
}