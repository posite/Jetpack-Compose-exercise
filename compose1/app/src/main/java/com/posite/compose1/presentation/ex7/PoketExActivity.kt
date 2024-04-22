package com.posite.compose1.presentation.ex7

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getColor
import com.posite.compose1.R
import com.posite.compose1.presentation.base.BaseActivity
import com.posite.compose1.presentation.ex7.vm.PoketViewModel
import com.posite.compose1.presentation.ex7.vm.PoketViewModelImpl
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoketExActivity : BaseActivity() {
    private val viewModel: PoketViewModel by viewModels<PoketViewModelImpl>()

    @Composable
    override fun InitView() {
        PoketCard(viewModel)
    }


}

@Composable
fun PoketCard(viewModel: PoketViewModel) {
    val animationDegree by animateFloatAsState(
        targetValue = if (viewModel.poketState.value) 0f else 180f,
        label = ""
    )
    Log.d("state", "PoketCard: ${viewModel.poketState.value}")
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 50.dp)
        .clickable { viewModel.onPoketClick() }
        .graphicsLayer {
            Log.d("animation", animationDegree.toString())
            rotationY = animationDegree
        }
    ) {
        if (animationDegree <= 90f) {
            PoketFront()
        } else {
            PoketBack()
        }
    }

}

@Composable
fun PoketFront() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.large)
            .background(Color(getColor(context, R.color.poket_backgorund)))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(shape = MaterialTheme.shapes.large)
                .background(Color.Black), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.poketball_512),
                contentDescription = "몬스터볼"
            )
        }
    }
}

@Composable
fun PoketBack() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.large)
            .background(Color(getColor(context, R.color.poket_backgorund)))
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .graphicsLayer {
                scaleX = -1f
            }
            .clip(shape = MaterialTheme.shapes.large)
            .background(Color(getColor(context, R.color.arceus_background))),
            contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.arceus),
                    contentDescription = "아르세우스"
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "아르세우스", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PoketPreview() {
    val viewModel = PoketViewModelImpl()
    Compose1Theme {
        PoketCard(viewModel = viewModel)
    }
}
