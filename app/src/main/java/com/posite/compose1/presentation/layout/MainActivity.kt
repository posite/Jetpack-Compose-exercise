package com.posite.compose1.presentation.layout

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.posite.compose1.R
import com.posite.compose1.presentation.layout.vm.MainViewModelImpl
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModelImpl>()
    private val passwordResource: (Boolean) -> Int = {
        if (it) { // true
            R.drawable.baseline_visibility_24
        } else {
            R.drawable.baseline_visibility_off_24
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1Theme {
                //ButtonEx()
                //olumnEx()
                //FieldsEx()
                //ImageEx()
                //BoxEx()
                //CardEx()
                //WebViewEx()
                SurfaceEx()
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
                viewModel.onOneClick()
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
                text = "Clicked Count: ${viewModel.count1.value}",
            )
        }
    }

    @Composable
    fun ColumnEx() {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
            ClickableText(
                onClick = { viewModel.onOneClick() },
                style = TextStyle(
                    background = Color.LightGray,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                ),
                text = AnnotatedString("Clicked Count: ${viewModel.count1.value}")
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Divider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.padding(8.dp))

            ClickableText(
                onClick = { viewModel.onTwoClick() },
                style = TextStyle(
                    background = Color.Yellow,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                ),
                text = AnnotatedString("Clicked Count: ${viewModel.count2.value}")
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Divider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.padding(8.dp))

            ClickableText(
                onClick = { viewModel.onThreeClick() },
                style = TextStyle(
                    background = Color.Cyan,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                ),
                text = AnnotatedString("Clicked Count: ${viewModel.count3.value}")
            )

        }
    }

    @Composable
    fun FieldsEx() {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
            TextField(
                value = viewModel.userInput1.value,
                onValueChange = viewModel::onUser1Inputted,
                maxLines = 1,
                placeholder = {
                    Text("아무거나 입력하세요")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onUser1InputVisibleClick()
                    }) {
                        Icon(
                            painter = painterResource(
                                id = passwordResource(viewModel.userInput1Visible.value)
                            ), contentDescription = null
                        )
                    }
                },
                visualTransformation = if (viewModel.userInput1Visible.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.padding(24.dp))

            OutlinedTextField(
                value = viewModel.userInput2.value,
                onValueChange = viewModel::onUser2Inputted,
                maxLines = 1,
                placeholder = {
                    Text("아무거나 입력하세요")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onUser2InputVisibleClick()
                    }) {
                        Icon(
                            painter = painterResource(
                                id = passwordResource(viewModel.userInput2Visible.value)
                            ), contentDescription = null
                        )
                    }
                },
                visualTransformation = if (viewModel.userInput2Visible.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Text(text = "입력1: ${viewModel.userInput1.value}", maxLines = 1)

            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "입력2: ${viewModel.userInput2.value}", maxLines = 1)
        }
    }

    @Composable
    fun ImageEx() {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.testing),
                contentDescription = "local image"
            )

            Spacer(modifier = Modifier.padding(12.dp))

            AsyncImage(
                model = "",
                contentDescription = "외부 image"
            )
        }

    }

    @Composable
    fun BoxEx() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "정 가운데", modifier = Modifier.align(Alignment.Center))
            Text(text = "왼쪽 위", modifier = Modifier.align(Alignment.TopStart))
            Text(text = "오른쪽 아래", modifier = Modifier.align(Alignment.BottomEnd))
        }
    }

    @Composable
    fun CardEx() {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            border = BorderStroke(5.dp, Color.Cyan),
            shape = CutCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {

        }
    }

    @Composable
    fun WebViewEx() {
        AndroidView(factory = {
            WebView(it).apply {
                loadUrl("https://github.com/posite")
            }
        })
    }

    @Composable
    fun SurfaceEx() {
        Surface(
            shape = RoundedCornerShape(30.dp),
            contentColor = Color.Gray,
            shadowElevation = 8.dp,
            tonalElevation = if (viewModel.surfaceSelect.value) {
                999.dp
            } else {
                0.dp
            },
            selected = viewModel.surfaceSelect.value,
            onClick = {
                viewModel.onSurfaceClick()
            }
        ) {
            Text(modifier = Modifier.padding(4.dp), text = "compose surface")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Compose1Theme {
            //ImageEx()
            //ButtonEx()
            //ColumnEx()
            // FieldsEx()
            //BoxEx()
            //CardEx()
            //WebViewEx()
            SurfaceEx()
        }
    }
}