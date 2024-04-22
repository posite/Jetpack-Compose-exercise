package com.posite.compose1.presentation.ex3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.posite.compose1.R
import com.posite.compose1.presentation.base.BaseActivity
import com.posite.compose1.presentation.ex3.graph.BarChart
import com.posite.compose1.presentation.ex3.graph.PieChart
import com.posite.compose1.presentation.ex3.graph.ProgressGraph
import com.posite.compose1.presentation.ex3.model.BottomItem
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Ex3Activity : BaseActivity() {
    @Composable
    override fun InitView() {
        val navController = rememberNavController()
        DrawBackground(navController)
    }

    @Composable
    fun DrawBackground(navController: NavHostController) {
        val navBottomItems = listOf(
            BottomItem(
                icon = R.drawable.circle_progress_64,
                content = "그래프1",
                route = "graph1"
            ),
            BottomItem(
                icon = R.drawable.pie_chart_64,
                content = "그래프2",
                route = "graph2"
            ),
            BottomItem(
                icon = R.drawable.bar_chart_64,
                content = "그래프3",
                route = "graph3"
            )
        )
        Scaffold(bottomBar = {
            BottomAppBar(containerColor = Color.Cyan) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 7.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    navBottomItems.forEach { item ->
                        val currentItem =
                            navController.currentBackStackEntryAsState().value?.destination?.route
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                navController.navigate(item.route)
                            }
                        ) {
                            Image(
                                painter = painterResource(item.icon),
                                contentDescription = item.content
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = item.content,
                                color = if (currentItem == item.route) Color.Magenta else Color.Black
                            )
                        }

                    }
                }
            }
        }) { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValue)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = navBottomItems.first().route,
                    modifier = Modifier.padding(paddingValues = paddingValue)
                ) {
                    composable("graph1") {
                        ProgressGraph()
                    }
                    composable("graph2") {
                        PieChart()
                    }
                    composable("graph3") {
                        BarChart()
                    }
                }
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Compose1Theme {
            InitView()
        }
    }
}