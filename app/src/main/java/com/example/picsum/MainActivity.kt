package com.example.picsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.picsum.ui.theme.PicsumTheme
import com.example.screens.PhotosListScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicsumTheme {
                val backStack = rememberSaveableBackStack(root = PhotosListScreen)
                val navigator = rememberCircuitNavigator(backStack)
                NavigableCircuitContent(navigator, backStack)
            }
        }
    }
}