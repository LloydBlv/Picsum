package com.example.picsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.picsum.ui.theme.PicsumTheme
import com.example.screens.PhotosListScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var circuit: Circuit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicsumTheme {
                val backstack = rememberSaveableBackStack(root = PhotosListScreen)
                val navigator = rememberCircuitNavigator(backstack)
                CircuitCompositionLocals(circuit = circuit) {
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backstack,
                    )
                }
            }
        }
    }
}