package com.whompum.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.layout_activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_main)
        setupNavigationTabs()
    }

    fun setupNavigationTabs() {
        NavigationUI.setupWithNavController(
            nav_tab_view,
            (nav_host_fragment as NavHostFragment).navController
        )
    }

}