package com.example.piggybank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.button_click)
        buttonClick.setOnClickListener {
            val intent = Intent(this, addSave::class.java)
            startActivity(intent)
        }

        val buttonSec = findViewById<Button>(R.id.button_open)
        buttonSec.setOnClickListener {
            val intentSec = Intent(this, OpenPig::class.java)
            startActivity(intentSec)
        }


    }
}