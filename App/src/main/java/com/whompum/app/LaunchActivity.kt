package com.whompum.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        //Redirect to main theme after setting theme from cold start in manifest
        setTheme(R.style.AppThemeMain)
        super.onCreate(savedInstanceState, persistentState)
    }

}