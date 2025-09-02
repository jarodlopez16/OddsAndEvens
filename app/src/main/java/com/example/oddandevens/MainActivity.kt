package com.example.oddandevens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var tvInstruct: TextView
    lateinit var tvScore: TextView
    lateinit var tvResult: TextView
    lateinit var btnReset: Button
    lateinit var assignment: Spinner

    private lateinit var cmpImage: ImageView
    private lateinit var cmpText: TextView
    private var playerScore = 0
    private var computerScore = 0
    private var computerChoice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cmpText = findViewById(R.id.cmpText)
        cmpImage = findViewById(R.id.cmpImage)
        tvInstruct = findViewById(R.id.tvInstruct)
        tvScore = findViewById(R.id.tvScore)
        tvResult = findViewById(R.id.tvResult)
        btnReset = findViewById(R.id.btnReset)
        assignment = findViewById(R.id.assignment)

        val btnZero: ImageButton = findViewById(R.id.btnZero)
        val btnOne: ImageButton = findViewById(R.id.btnOne)
        val btnTwo: ImageButton = findViewById(R.id.btnTwo)
        val btnThree: ImageButton = findViewById(R.id.btnThree)
        val btnFour: ImageButton = findViewById(R.id.btnFour)
        val btnFive: ImageButton = findViewById(R.id.btnFive)

        btnZero.setOnClickListener { playRound(0) }
        btnOne.setOnClickListener { playRound(1) }
        btnTwo.setOnClickListener { playRound(2) }
        btnThree.setOnClickListener { playRound(3) }
        btnFour.setOnClickListener { playRound(4) }
        btnFive.setOnClickListener { playRound(5) }
        btnReset.setOnClickListener { resetGame() }
    }

    private fun getComputerChoice() {
        cmpText.text = "Computer chose:"
        computerChoice = Random.nextInt(0, 6)
        when (computerChoice) {
            1 -> cmpImage.setImageResource(R.drawable.one)
            2 -> cmpImage.setImageResource(R.drawable.two)
            3 -> cmpImage.setImageResource(R.drawable.three)
            4 -> cmpImage.setImageResource(R.drawable.four)
            5 -> cmpImage.setImageResource(R.drawable.five)
            0 -> cmpImage.setImageResource(R.drawable.zero)
        }
    }

    private fun playRound(playerChoice: Int) {
        getComputerChoice()
        val chosen = assignment.selectedItem
        val sum = playerChoice + computerChoice
        if (sum % 2 == 0) {
            if (chosen == "Evens") {
                tvResult.text = "The sum is " + sum + ", and you had evens.\nYou win! \uD83E\uDD73"
                playerScore++
            } else {
                tvResult.text = "The sum is " + sum + ", and you had odds.\nComputer wins! \uD83D\uDCBB"
                computerScore++
            }
        } else {
            if (chosen == "Odds") {
                tvResult.text = "The sum is " + sum + ", and you had odds.\nYou win! \uD83E\uDD73"
                playerScore++
            } else {
                tvResult.text = "The sum is " + sum + ", and you had evens.\nComputer wins! \uD83D\uDCBB"
                computerScore++
            }
        }
        updateScore()
    }

    private fun updateScore() {
        tvScore.text = "Player: " + playerScore + " | Computer: " + computerScore
    }

    private fun resetGame() {
        computerScore = 0
        playerScore = 0
        tvScore.text = "Player: 0 | Computer: 0"
        tvResult.text = ""
        cmpText.text = ""
        cmpImage.setImageResource(0)
    }
}