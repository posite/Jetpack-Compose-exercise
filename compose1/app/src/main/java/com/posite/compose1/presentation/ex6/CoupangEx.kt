package com.posite.compose1.presentation.ex6

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getColor
import com.posite.compose1.R
import com.posite.compose1.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoupangEx : BaseActivity() {
    @Composable
    override fun InitView() {
        CoupangExView()
    }
}

@Composable
fun CoupangExView() {
    Column(modifier = Modifier.fillMaxSize()) {
        CoupangHeader()
        Spacer(modifier = Modifier.size(12.dp))
        CoupangSearchBar()
        Spacer(modifier = Modifier.size(8.dp))
        CoupangTopBanner()
        Spacer(modifier = Modifier.size(4.dp))
        CoupangCategories()
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun CoupangHeader() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "c",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupange_brown))
            )
            Text(
                text = "o",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupange_brown))
            )
            Text(
                text = "u",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupange_brown))
            )
            Text(
                text = "p",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupang_red))
            )
            Text(
                text = "a",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupang_orange))
            )
            Text(
                text = "n",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupang_green))
            )
            Text(
                text = "g",
                style = MaterialTheme.typography.titleSmall,
                color = Color(getColor(context, R.color.coupang_blue))
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column(modifier = Modifier.padding(bottom = 6.dp)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "coupang dropdown",
                    modifier = Modifier
                        .background(
                            color = Color(getColor(context, R.color.gray_eeeeee)),
                            shape = CircleShape
                        )
                        .size(16.dp)

                )
            }

        }
        Image(
            painter = painterResource(id = R.drawable.notification_128),
            contentDescription = "coupang dropdown",
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun CoupangSearchBar() {
    val (text, setText) = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(48.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.small
            )
    ) {
        TextField(
            value = text,
            onValueChange = { setText(it) },
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = "지슈라 2",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                )
            },
            shape = MaterialTheme.shapes.small
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoupangTopBanner() {
    val context = LocalContext.current
    val pageState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        4
    }

    val adTextList = listOf("ad1", "ad2", "ad3", "ad4")

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(state = pageState, modifier = Modifier.fillMaxWidth()) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 16.dp)
                    .background(Color.Gray)
            ) {
                Text(text = adTextList[page])
            }

        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageState.pageCount) { iteration ->
                val color = if (pageState.currentPage == iteration) Color.White else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }


    }
}

@Composable
fun CoupangCategories() {
    val scrollState = rememberScrollState()
    val categoryList =
        listOf("category1", "category2", "category3", "category4", "category5", "category6")
    val iconList = listOf(
        Icons.Default.AccountBox,
        Icons.Default.Search,
        Icons.Default.List,
        Icons.Default.Add,
        Icons.Default.Email,
        Icons.Default.Home
    )
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .horizontalScroll(scrollState)
        ) {
            categoryList.forEachIndexed { index, category ->
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = iconList[index],
                        contentDescription = category,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = category, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        imageVector = iconList[index],
                        contentDescription = category,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = category, style = MaterialTheme.typography.bodyMedium)
                }

                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = iconList[index],
                        contentDescription = category,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = category, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        imageVector = iconList[index],
                        contentDescription = category,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = category, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        Canvas(
            modifier = Modifier
                .width(60.dp)
                .height(3.dp)
        ) {
            drawRoundRect(
                color = Color.LightGray,
                cornerRadius = CornerRadius(20f),
                alpha = 0.5f
            )
            drawRoundRect(
                topLeft = Offset(
                    (scrollState.value.toFloat() / scrollState.maxValue.toFloat()) * 149f,
                    0f
                ),
                color = Color.Black,
                cornerRadius = CornerRadius(20f),
                size = Size(
                    if ((scrollState.value.toFloat() / scrollState.maxValue.toFloat()) * 100f <= 95f) 40f else 30f,
                    8f
                )
            )
        }
    }

}


@Composable
@Preview(showBackground = true)
fun CoupangeExPreview() {
    CoupangExView()
}