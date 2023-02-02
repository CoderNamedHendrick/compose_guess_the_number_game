package com.hendrick.guessthenumberandroid.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hendrick.guessthenumberandroid.models.Game
import com.hendrick.guessthenumberandroid.ui.theme.GuessTheNumberAndroidTheme
import com.hendrick.guessthenumberandroid.ui.widgets.AppOutlinedTextField
import com.hendrick.guessthenumberandroid.ui.widgets.SuccessDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessTheNumberScreen(modifier: Modifier = Modifier) {

    val game by remember {
        mutableStateOf(Game())
    }

    var guessValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var validationError by remember {
        mutableStateOf<String?>(null)
    }


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Android Guess the number game") })
        }) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                TextButton(
                    modifier = modifier.align(alignment = Alignment.End),
                    onClick = {
                        game.reset()
                        guessValue = TextFieldValue("")
                    },
                ) {
                    Text(text = "Reset Game")
                }

                Spacer(modifier = modifier.height(22.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("🎯🎯Total Score:", style = TextStyle(fontSize = 22.sp))

                    Text(
                        "${game.totalScore}",
                        style = TextStyle(
                            fontSize = 28.sp,
                            color = Color(0xFFFFC107)
                        )
                    )
                }

                Spacer(modifier = modifier.height(18.dp))

                Text(
                    "I'm guessing a number between 1 and 100.\nTake a guess👀",
                    style = TextStyle(fontSize = 28.sp),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = modifier.height(12.dp))

                AppOutlinedTextField(
                    value = guessValue,
                    onValueChanged = { guessValue = it },
                    placeholder = { Text("Guess") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                    error = validationError,
                    ignoredRegex = Regex("[\\D]")
                )

                Spacer(modifier = modifier.height(12.dp))

                ElevatedButton(onClick = {
                    validationError = validateStringValue(guessValue.text)

                    if (validationError == null) {
                        game.submitGuess(guessValue.text.toInt())
                        showDialog = true
                    }
                }) {
                    Text("Submit Guess")
                }
            }

        }
    }

    if (showDialog) {
        SuccessDialog(game = game, optionPress = { resetGame ->
            showDialog = if (resetGame) {
                game.reset()
                guessValue = TextFieldValue("")
                false
            } else {
                game.newGame()
                guessValue = TextFieldValue("")
                false
            }
        }, onDismiss = game::newGame)
    }
}

fun validateStringValue(value: String): String? {
    if (value.isEmpty() || value.toIntOrNull() == null) return "Please input a number"

    if (value.toInt() > 100) {
        return "Guess out of bounds, please try again"
    }

    return null
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GuessTheNumberScreenPreview() {
    GuessTheNumberAndroidTheme {
        GuessTheNumberScreen()
    }
}