package com.posite.compose2.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.posite.compose2.ui.theme.Compose2Theme

abstract class BaseActivity : ComponentActivity() {
    @Composable
    abstract fun InitView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose2Theme {
                InitView()
            }
        }
    }

}