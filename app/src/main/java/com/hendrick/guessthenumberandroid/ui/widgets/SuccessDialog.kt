package com.hendrick.guessthenumberandroid.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.hendrick.guessthenumberandroid.models.Game
import com.hendrick.guessthenumberandroid.ui.theme.GuessTheNumberAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessDialog(
    modifier: Modifier = Modifier,
    game: Game,
    optionPress: (Boolean) -> Unit,
    onDismiss: () -> Unit,
) {

    AlertDialog(
        modifier = modifier
            .background(color = Color.White)
            .padding(18.dp),
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {

        Column {
            Text(
                "Congratulations🎉🎉🎉, I guessed ${game.guess}.\nYou scored ${game.roundScore} in this round and your total score is ${game.totalScore}",
                style = TextStyle(
                    fontSize = 22.sp, color = Color.Blue
                ),
                textAlign = TextAlign.Center,
            )

            Row {
                TextButton(onClick = { optionPress(false) }) {
                    Text("Continue to new round")
                }

                TextButton(onClick = { optionPress(true) }) {
                    Text("Reset game")
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun SuccessDialogPreview() {
    GuessTheNumberAndroidTheme {
        SuccessDialog(game = Game.initTest(10), optionPress = {}) {}
    }
}