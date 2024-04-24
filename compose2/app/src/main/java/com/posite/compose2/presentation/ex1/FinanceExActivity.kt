package com.posite.compose2.presentation.ex1

import android.icu.text.NumberFormat
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.posite.compose2.presentation.base.BaseActivity
import com.posite.compose2.ui.theme.SkyBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FinanceExActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        FinanceMain()
    }
}

@Composable
fun FinanceMain() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        FinanceHeader()
        FinanceTopMenu()
    }
}

@Composable
fun FinanceHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FaIcon(faIcon = FaIcons.ChevronLeft)
        FaIcon(faIcon = FaIcons.Plus)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinanceTopMenu() {
    val menus = listOf("자산", "소비·수입", "연말정산")
    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
    val coroutineScope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage, indicator = { positions ->
        TabRowDefaults.Indicator(
            modifier = Modifier
                .tabIndicatorOffset(positions[pagerState.currentPage])
                .padding(horizontal = 24.dp),
            color = SkyBlue
        )
    }, divider = {}, containerColor = Color.White, contentColor = Color.Black) {
        menus.forEachIndexed { index, text ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }, text = {
                Text(text = text)
            })
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            when (pagerState.currentPage) {
                0 -> MyAsset()
                1 -> ConsumptionIncomeThisMonth()
                2 -> YearEndSettlement()
            }
        }
    }
}

@Composable
fun MyAsset() {
    val (resultMoney, setMoney) = remember { mutableStateOf(0) }
    val moneyAnimation by animateIntAsState(
        targetValue = resultMoney,
        label = "",
        animationSpec = tween(1000)
    )
    val formattedMoney = NUMBER_FORMAT.format(moneyAnimation)
    Row(verticalAlignment = Alignment.CenterVertically) {
        FaIcon(faIcon = FaIcons.CaretLeft)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "나의 자산", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        FaIcon(faIcon = FaIcons.CaretRight)
    }
    Spacer(modifier = Modifier.height(12.dp))

    LaunchedEffect(key1 = true) {
        setMoney(10000)
    }
    Text("${formattedMoney}원", fontSize = 24.sp)
    Spacer(modifier = Modifier.height(4.dp))

}

@Composable
fun ConsumptionIncomeThisMonth() {
    val (resultMoney, setMoney) = remember { mutableStateOf(0) }
    val moneyAnimation by animateIntAsState(
        targetValue = resultMoney,
        label = "",
        animationSpec = tween(1000)
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                val formattedMoney = NUMBER_FORMAT.format(moneyAnimation)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FaIcon(faIcon = FaIcons.CaretLeft)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "나의 소비", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    FaIcon(faIcon = FaIcons.CaretRight)
                }

                Spacer(modifier = Modifier.height(12.dp))

                LaunchedEffect(key1 = true) {
                    setMoney(1000000)
                }
                Text("${formattedMoney}원", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "계좌에서 쓴 금액 포함", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))
                ConsumptionIncomeThisMonthProgress()
            }
        }
        item{ConsumeBody()}
    }
}

@Composable
private fun ConsumeBody() {
    ContentDivider()
    ConsumeGraph()
    Spacer(modifier = Modifier.height(16.dp))
    ContentDivider()
    Spacer(modifier = Modifier.height(16.dp))
    ConsumeInsuranceGraph()
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ConsumptionIncomeThisMonthProgress() {
    val list = listOf(
        ConsumeItem(FaIcons.MoneyBill, SkyBlue, "이체", 600000, 0.6f),
        ConsumeItem(FaIcons.DollarSign, Color.Blue, "저금", 200000, 0.2f),
        ConsumeItem(FaIcons.MoneyBillWave, Color.Gray, "송금", 100000, 0.1f),
        ConsumeItem(FaIcons.HouseUser, Color.Green, "월세", 100000, 0.1f)
    )
    val (percent, setPercent) = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        setPercent(true)
    }
    val animatedWeight = list.map {
        animateFloatAsState(
            targetValue = if (percent) it.fraction else 0.01f,
            animationSpec = tween(3000)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        list.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .weight(animatedWeight[index].value)
                    .fillMaxHeight()
                    .background(
                        item.color,
                        shape = when (index) {
                            0 -> RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                            list.lastIndex -> RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                            else -> RoundedCornerShape(0.dp)
                        }
                    )
            ) {

            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    ConsumptionIncomeThisMonthList(list = list)

}

@Composable
fun ConsumptionIncomeThisMonthList(list: List<ConsumeItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        list.forEach { consume ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(consume.color, CircleShape), contentAlignment = Alignment.Center
                ) {
                    FaIcon(faIcon = consume.icon, tint = Color.White, size = 24.dp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = consume.content, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${(consume.fraction * 100).toInt()}%",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 1.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${NUMBER_FORMAT.format(consume.amount)}원", fontSize = 20.sp)
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun ConsumeGraph() {
    val consume1 = listOf(650f, 900f, 500f, 950f)
    val consume2 = listOf(200f, 100f, 400f, 300f)
    val consume3 = listOf(200f, 50f, 100f, 150f)
    val consume4 = listOf(150f, 100f, 200f, 50f)

    val pointState = rememberInfiniteTransition()
    val twinkleAnimation = pointState.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = "이번달", fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(12.dp))
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val width = size.width
            val height = size.height
            val max = maxOf(consume1.max(), consume2.max())
            val min = minOf(consume1.min(), consume2.min())

            val consumeMap1 = consume1.mapIndexed { index, point ->
                val x = (width / (consume1.size - 1)) * index
                val y = height - (height * (point - min) / (max - min))
                x to y
            }

            val consumeMap2 = consume2.mapIndexed { index, point ->
                val x = (width / (consume1.size - 1)) * index
                val y = height - (height * (point - min) / (max - min))
                x to y
            }

            val consumeMap3 = consume3.mapIndexed { index, point ->
                val x = (width / (consume1.size - 1)) * index
                val y = height - (height * (point - min) / (max - min))
                x to y
            }

            val consumeMap4 = consume4.mapIndexed { index, point ->
                val x = (width / (consume1.size - 1)) * index
                val y = height - (height * (point - min) / (max - min))
                x to y
            }

            consumeMap1.zipWithNext { a, b ->
                drawLine(
                    color = SkyBlue,
                    start = Offset(a.first, a.second),
                    end = Offset(b.first, b.second),
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = SkyBlue,
                    center = Offset(a.first, a.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
                drawCircle(
                    color = SkyBlue,
                    center = Offset(b.first, b.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
            }
            consumeMap2.zipWithNext { a, b ->
                drawLine(
                    color = Color.Blue,
                    start = Offset(a.first, a.second),
                    end = Offset(b.first, b.second),
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = Color.Blue,
                    center = Offset(a.first, a.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
                drawCircle(
                    color = Color.Blue,
                    center = Offset(b.first, b.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
            }
            consumeMap3.zipWithNext { a, b ->
                drawLine(
                    color = Color.Gray,
                    start = Offset(a.first, a.second),
                    end = Offset(b.first, b.second),
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = Color.Gray,
                    center = Offset(a.first, a.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
                drawCircle(
                    color = Color.Gray,
                    center = Offset(b.first, b.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
            }
            consumeMap4.zipWithNext { a, b ->
                drawLine(
                    color = Color.Green,
                    start = Offset(a.first, a.second),
                    end = Offset(b.first, b.second),
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = Color.Green,
                    center = Offset(a.first, a.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
                drawCircle(
                    color = Color.Green,
                    center = Offset(b.first, b.second),
                    radius = 10f,
                    alpha = twinkleAnimation.value
                )
            }
        }
    }

}

@Composable
fun ConsumeInsuranceGraph() {
    val (leftGraphHeight, setLeftGraphHeight) = remember { mutableStateOf(200.dp) }
    val (rightGraphHeight, setRightGraphHeight) = remember { mutableStateOf(200.dp) }
    val leftGraphState =
        animateDpAsState(targetValue = leftGraphHeight, animationSpec = tween(2000))
    val rightGraphState =
        animateDpAsState(targetValue = rightGraphHeight, animationSpec = tween(2000))

    LaunchedEffect(Unit) {
        while (true) {
            setLeftGraphHeight(200.dp)
            setRightGraphHeight(40.dp)

            delay(2000)

            setLeftGraphHeight(40.dp)
            setRightGraphHeight(200.dp)
            delay(2000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "매달 내는 보험료 적절할까요?", fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(modifier = Modifier.width(70.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("과잉")
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(leftGraphState.value)
                        .background(
                            Color.Red,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                )
            }
        }

        Box(
            modifier = Modifier
                .width(70.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("평균")
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(rightGraphState.value)
                        .background(SkyBlue, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
            }
        }
    }

}


@Composable
fun YearEndSettlement() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        FaIcon(faIcon = FaIcons.CaretLeft)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "연말정산", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        FaIcon(faIcon = FaIcons.CaretRight)
    }
}

@Composable
fun ContentDivider() = Divider(
    modifier = Modifier
        .fillMaxWidth()
        .height(10.dp),
    color = Color.LightGray
)

private val NUMBER_FORMAT = NumberFormat.getNumberInstance()

@Composable
@Preview(showBackground = true)
fun ChartExPreview() {
    FinanceMain()
}
