package com.hendrick.guessthenumberandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hendrick.guessthenumberandroid.screens.GuessTheNumberScreen
import com.hendrick.guessthenumberandroid.ui.theme.GuessTheNumberAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheNumberAndroidTheme { GuessTheNumberScreen() }
        }
    }
}