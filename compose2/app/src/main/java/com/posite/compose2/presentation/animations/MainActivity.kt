package com.posite.compose2.presentation.animations

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.posite.compose2.presentation.animations.compose.SliderPieChart
import com.posite.compose2.presentation.base.BaseActivity
import com.posite.compose2.ui.theme.Compose2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        SliderPieChart()
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose2Theme {
        SliderPieChart()
    }
}