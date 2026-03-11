package com.edgemind.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.edgemind.app.ui.navigation.EdgeMindNavHost
import com.edgemind.app.ui.theme.EdgeMindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EdgeMindTheme {
                EdgeMindNavHost()
            }
        }
    }
}
