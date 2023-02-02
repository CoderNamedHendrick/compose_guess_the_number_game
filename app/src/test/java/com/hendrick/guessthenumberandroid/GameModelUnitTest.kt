package com.hendrick.guessthenumberandroid

import com.hendrick.guessthenumberandroid.models.Game
import org.junit.Test
import org.junit.Assert.*

class GameModelUnitTest {
    private var game: Game = Game.initTest(30)

    @Test
    fun gameInstance_isCorrect() {
        assertEquals(0, game.totalScore)
        assertEquals(0, game.roundScore)
        assertEquals(30, game.guess)
    }

    @Test
    fun gameInstance_scoreUpdateIsCorrect() {
        game.submitGuess(50)
        assertEquals(80, game.totalScore)

        game.submitGuess(29)
        assertEquals(179, game.totalScore)

        game.submitGuess(30)
        assertEquals(379, game.totalScore)
    }

    @Test
    fun gameInstance_roundScoreUpdateIsCorrect() {
        game.submitGuess(50)
        assertEquals(80, game.totalScore)

        game.submitGuess(29)
        assertEquals(179, game.totalScore)

        game.newGame(10)

        assertEquals(0, game.roundScore)
    }

    @Test
    fun gameInstance_playMultipleGamesSuccess() {
        game.submitGuess(20)
        assertEquals(90, game.totalScore)

        game.newGame(20)

        game.submitGuess(40)
        assertEquals(170, game.totalScore)
    }

    @Test
    fun gameInstance_playMultipleGamesResetAndStartAgain_isCorrect() {
        game.submitGuess(50)
        game.submitGuess(29)
        assertEquals(179, game.totalScore)

        game.reset(80)

        assertEquals(0, game.roundScore)
        assertEquals(0, game.totalScore)

        game.submitGuess(40)
        game.submitGuess(80)

        assertEquals(200, game.roundScore)
        assertEquals(260, game.totalScore)
    }
}