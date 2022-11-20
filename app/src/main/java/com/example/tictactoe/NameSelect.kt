package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf

class NameSelect : AppCompatActivity() {

    private lateinit var etP1: EditText
    private lateinit var etP2: EditText
    private lateinit var etWinningScore: EditText
    private lateinit var btStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_select)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        etP1 = findViewById(R.id.etP1)
        etP2 = findViewById(R.id.etP2)
        etWinningScore = findViewById(R.id.etWinningScore)
        btStart = findViewById(R.id.btStart)

        btStart.setOnClickListener {
            var p1Name = etP1.text.toString().trim()
            var p2Name = etP2.text.toString().trim()
            var winningScore = etWinningScore.text.toString().trim()
            if (winningScore == "" || winningScore == "0" || winningScore == "00") { winningScore = "5" }
            if (winningScore[0].toString() == "0") { winningScore = winningScore.takeLast(1) }
            if (p1Name == "") { p1Name = "Player 1" }
            if (p2Name == "") { p2Name = "Player 2" }

            intent = Intent(applicationContext, MainActivity::class.java)
            val bundle = bundleOf(
                Pair("p1Name", p1Name),
                Pair("p2Name", p2Name),
                Pair("winningScore", winningScore))
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }
}