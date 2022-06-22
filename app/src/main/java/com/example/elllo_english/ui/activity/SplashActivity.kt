package com.example.elllo_english.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.elllo_english.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        forwardActivity()
    }

    private fun forwardActivity() {
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivityCover::class.java))
            finish()
        }, 2000)
    }
}