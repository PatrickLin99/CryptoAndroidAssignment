package com.example.cryptoandroidtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.search.presentation.DemoActivity

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(onGoToShowCaseClickListener = ::onGoToShowCaseClickListener)
        }
    }

    private fun onGoToShowCaseClickListener() {
        val i = Intent(this, DemoActivity::class.java)
        this.startActivity(i)
    }
}