package com.posite.compose1.presentation.layout

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.presentation.layout.vm.MainViewModelImpl
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1Theme {
                // A surface container using the 'background' color from the theme

                ButtonEx()
                //ColumnEx()
            }
        }
    }

    @Composable
    fun TextEx() {
        Text(
            style = TextStyle(background = Color.Gray),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            text = "안녕!",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }

    @Composable
    fun ButtonEx() {
        // remember 1 var 변수처럼 사용
        //var clickedCount by remember {
        //    mutableIntStateOf(0)
        //}

        // setValue 함수를 사용하여 값 변경
        //val mutableState = remember { mutableIntStateOf(0) }


        // remember 3  get, set으로 분리
        //val (getCount, setCount) = remember {
        //    mutableIntStateOf(0)
        //}

        val context = LocalContext.current
        Button(
            onClick = {
                Log.d("Main", "Button Clicked")
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                //clickedCount++
                //setCount(getCount + 1)
                //mutableState.intValue = mutableState.intValue.inc()
                viewModel.onClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Blue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                style = TextStyle(background = Color.Gray),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                text = "Clicked Count: ${viewModel.count.value}",
            )
        }
    }

    @Composable
    fun ColumnEx() {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Compose1Theme {

            ButtonEx()
//            ColumnEx()
        }
    }
}