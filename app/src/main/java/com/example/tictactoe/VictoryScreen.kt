package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class VictoryScreen : AppCompatActivity() {

    private lateinit var tvVictoryPlayer: TextView
    private lateinit var tvVictoryText: TextView
    private lateinit var btPlayAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_victory_screen)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        tvVictoryPlayer = findViewById(R.id.tvVictoryPlayer)
        tvVictoryText = findViewById(R.id.tvVictoryText)
        btPlayAgain = findViewById(R.id.btPlayAgain)

        val winner = intent.getStringExtra("winner")
        val winnerName = intent.getStringExtra("winnerName")

        if (winner == "p1") {
            tvVictoryPlayer.text = winnerName
            tvVictoryPlayer.setTextColor(Color.CYAN)
            tvVictoryText.setTextColor(Color.CYAN)
        }
        else {
            tvVictoryPlayer.text = winnerName
            tvVictoryPlayer.setTextColor(Color.RED)
            tvVictoryText.setTextColor(Color.RED)
        }

        btPlayAgain.setOnClickListener {
            finish()
        }

    }
}