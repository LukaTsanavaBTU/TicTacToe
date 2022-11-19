package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvP1name: TextView
    private lateinit var tvP1score: TextView
    private lateinit var tvP2name: TextView
    private lateinit var tvP2score: TextView
    private var xTurn = true
    private var gamePaused = false
    private var usedIds = mutableListOf("")
    private var combination = mutableListOf("","","","","","","","","")
    private lateinit var bReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        bReset = findViewById(R.id.bReset)
        tvP1name = findViewById(R.id.tvP1name)
        tvP1score = findViewById(R.id.tvP1score)
        tvP2name = findViewById(R.id.tvP2name)
        tvP2score = findViewById(R.id.tvP2score)

        val p1Name = intent.getStringExtra("p1Name")
        val p2Name = intent.getStringExtra("p2Name")

        tvP1name.text = "$p1Name:"
        tvP2name.text = "$p2Name:"

        bReset.setOnClickListener {
            findViewById<Button>(R.id.b1).text = ""
            findViewById<Button>(R.id.b2).text = ""
            findViewById<Button>(R.id.b3).text = ""
            findViewById<Button>(R.id.b4).text = ""
            findViewById<Button>(R.id.b5).text = ""
            findViewById<Button>(R.id.b6).text = ""
            findViewById<Button>(R.id.b7).text = ""
            findViewById<Button>(R.id.b8).text = ""
            findViewById<Button>(R.id.b9).text = ""
            xTurn = true
            usedIds = mutableListOf("")
            combination = mutableListOf("","","","","","","","","")
            gamePaused = false
        }
    }

    fun clickButton (clickedView: View) {
        if (clickedView is Button) {
            if (!gamePaused) {
                val location = resources.getResourceEntryName(clickedView.id).takeLast(1)
                if (location !in usedIds) {
                    if (xTurn) {
                        clickedView.text = "X"
                        clickedView.setTextColor(application.resources.getColor(R.color.blue))
                        usedIds.add(resources.getResourceEntryName(clickedView.id).takeLast(1))
                        combination[location.toInt() - 1] = "X"
                        xTurn = !xTurn

                    } else {
                        clickedView.text = "O"
                        usedIds.add(resources.getResourceEntryName(clickedView.id).takeLast(1))
                        clickedView.setTextColor(application.resources.getColor(R.color.red))
                        combination[location.toInt() - 1] = "O"
                        xTurn = !xTurn
                    }

                    if ( combination[0] + combination[3] + combination[6]  == "XXX" || combination[1] + combination[4] + combination[7]  == "XXX"
                        || combination[2] + combination[5] + combination[8]  == "XXX" || combination[0] + combination[1] + combination[2]  == "XXX"
                        || combination[3] + combination[4] + combination[5]  == "XXX" || combination[6] + combination[7] + combination[8]  == "XXX"
                        || combination[0] + combination[4] + combination[8]  == "XXX" || combination[2] + combination[4] + combination[6]  == "XXX") {
                        showToast("blue")
                        tvP1score.text = (tvP1score.text.toString().toInt() + 1).toString()
                        gamePaused = true
                    }
                    else if ( combination[0] + combination[3] + combination[6]  == "OOO" || combination[1] + combination[4] + combination[7]  == "OOO"
                        || combination[2] + combination[5] + combination[8]  == "OOO" || combination[0] + combination[1] + combination[2]  == "OOO"
                        || combination[3] + combination[4] + combination[5]  == "OOO" || combination[6] + combination[7] + combination[8]  == "OOO"
                        || combination[0] + combination[4] + combination[8]  == "OOO" || combination[2] + combination[4] + combination[6]  == "OOO" ) {
                        showToast("red")
                        tvP2score.text = (tvP2score.text.toString().toInt() + 1).toString()
                        gamePaused = true
                    }
                    else if (combination.all { it != "" }) {
                        showToast("tie")
                        gamePaused = true
                    }
                }
            }
        }
    }

    private fun showToast (state: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toastLayout))
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, 0,0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        if (state == "red") {

            layout.findViewById<TextView>(R.id.toastText).text =
                "${tvP2name.text.toString().dropLast(1)} Wins!"
            layout.findViewById<TextView>(R.id.toastText).setTextColor(Color.RED)
        }

        if (state == "blue") {
            layout.findViewById<TextView>(R.id.toastText).text =
                "${tvP1name.text.toString().dropLast(1)} Wins!"
            layout.findViewById<TextView>(R.id.toastText).setTextColor(Color.CYAN)
        }


        toast.show()
    }
}