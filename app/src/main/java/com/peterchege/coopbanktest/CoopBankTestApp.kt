package com.peterchege.coopbanktest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoopBankTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}