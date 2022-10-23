package com.saket.androiddrawabletypes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val levelImage = findViewById<ImageView>(R.id.level_image)
        levelImage.setImageLevel(1)
    }
}