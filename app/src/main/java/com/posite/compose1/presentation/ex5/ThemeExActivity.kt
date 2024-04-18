package com.posite.compose1.presentation.ex5

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeExActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeExActivityView()
        }
    }
}

@Composable
fun ThemeExActivityView() {
    Compose1Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("제목", style = MaterialTheme.typography.titleLarge)
                Text("본문", style = MaterialTheme.typography.bodyLarge)
                Text("라벨", style = MaterialTheme.typography.labelLarge)

                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(Color.Cyan)
                ) {

                }
                Spacer(modifier = Modifier.padding(12.dp))
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(Color.Cyan)
                ) {

                }
                Spacer(modifier = Modifier.padding(12.dp))
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.Cyan)
                ) {

                }
                Spacer(modifier = Modifier.padding(12.dp))
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color.Cyan)
                ) {

                }
            }

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Light Mode")
@Composable
fun ThemeExActivityPreView() {
    ThemeExActivityView()
}