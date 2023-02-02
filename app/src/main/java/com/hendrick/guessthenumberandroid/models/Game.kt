package com.hendrick.guessthenumberandroid.models

import kotlin.math.absoluteValue
import kotlin.random.Random

class Game private constructor(randomGuess: Int, roundScore: Int = 0, totalScore: Int = 0) {
    var guess: Int = randomGuess
        private set
    var totalScore: Int = totalScore
        private set
    var roundScore: Int = roundScore
        private set

    companion object {
        operator fun invoke(): Game {
            val game =  Game(0, 0, 0)
            game.newGame()
            return game
        }

        fun initTest(randomGuess: Int): Game {
            return Game(randomGuess, 0, 0)
        }
    }


    fun newGame(testValue: Int? = null) {
        roundScore = 0
        guess = testValue ?: Random.nextInt(101)
    }

    fun reset(testValue: Int? = null) {
        newGame(testValue)
        totalScore = 0
    }

    fun submitGuess(guess: Int) {
        val difference = guess - this.guess

        roundScore = if (difference == 0) {
            200
        } else {
            100 - difference.absoluteValue
        }

        totalScore += roundScore
    }

}