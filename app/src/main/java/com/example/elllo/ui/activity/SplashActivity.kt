package com.example.elllo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.elllo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        forwardActivity()
    }

    private fun forwardActivity() {
        lifecycleScope.launch {
            delay(2000)
            MainActivityCover.starter(this@SplashActivity)
            finish()
        }
    }
}