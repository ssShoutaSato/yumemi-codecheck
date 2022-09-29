/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * This is the main activity for this application.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    companion object {
        lateinit var lastSearchDate: Date
    }
}
