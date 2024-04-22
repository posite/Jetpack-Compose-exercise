package com.posite.compose1.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.posite.compose1.ui.theme.Compose1Theme

abstract class BaseActivity : ComponentActivity() {
    @Composable
    abstract fun InitView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1Theme {
                InitView()
            }
        }
    }

}