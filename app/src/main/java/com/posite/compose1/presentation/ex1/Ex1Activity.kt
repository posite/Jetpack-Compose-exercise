package com.posite.compose1.presentation.ex1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posite.compose1.R
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Ex1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1Theme {
                DevResume()
            }
        }
    }

    @Composable
    fun DevResume() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Android Dev Resume",
                fontSize = 26.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(getColor(R.color.light_blue)))
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.photo),
                        contentDescription = "이력서 사진",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally), text = "자기소개",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(
                        fontSize = 16.sp,
                        text = "안녕하세요! 안드로이드 개발자 심영수입니다. 항상 더 좋은 UI/UX를 제공하기 위해 노력하고 있습니다!"
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    Divider()
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-xxxx-xxxx"))
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.Call, contentDescription = "전화 아이콘")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(text = "Tel: 010-xxxx-xxxx ")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(modifier = Modifier.clickable {
                        val intent =
                            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:xxxx@gmail.com"))
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.Email, contentDescription = "이메일 아이콘")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(text = "xxxx@gmail.com ")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(modifier = Modifier.clickable {
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/posite"))
                        context.startActivity(intent)
                    }) {
                        Icon(
                            painterResource(id = R.drawable.github_64),
                            contentDescription = "github 아이콘",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(text = "posite")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Compose1Theme {
            DevResume()
        }
    }
}