package com.example.elllo_english.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.elllo_english.R
import com.example.elllo_english.utils.AppLogger

class MainActivityCover : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppLogger.info("Create toolBar")
        setSupportActionBar(findViewById(R.id.tool_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Level"
        val navController = findNavController(R.id.nav_host_fragment)
        val config = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        toolbar.setupWithNavController(navController, config)
    }
}