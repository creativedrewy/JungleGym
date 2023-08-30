package com.solanamobile.krate

import android.app.Application
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.android.application
import com.moriatsushi.koject.start

class KrateApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Koject.start {
            application(this@KrateApplication)
        }
    }

}