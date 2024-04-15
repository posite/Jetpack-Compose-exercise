package com.posite.compose1.presentation.layout

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.posite.compose1.R
import com.posite.compose1.data.dto.test.response.UserInfoModelItem
import com.posite.compose1.presentation.layout.vm.MainViewModel
import com.posite.compose1.presentation.layout.vm.MainViewModelImpl
import com.posite.compose1.ui.theme.Compose1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
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
                //SurfaceEx()
                //ScaffoldEx()
                //LazyColumnRowEx()
                //ProgressIndicatorEx()
                //Buttons()
                //NavEx()
                //RetrofitEx()
                GetPostByRandomId()
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
    fun ButtonEx(viewModel: MainViewModel) {
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldEx() {
        Scaffold(
            modifier = Modifier.padding(bottom = 4.dp),
            topBar = {
                TopAppBar(title = { Text(text = "TopAppBar") }, navigationIcon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = "ArrowBack")
                }, actions = {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.onOneClick() },
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            bottomBar = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shadowElevation = 0.dp,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Place, contentDescription = "Place")
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.AccountCircle, contentDescription = "Account")
                        }
                    }
                }
            }
        ) { paddingValue ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = paddingValue.calculateTopPadding() + 8.dp,
                        horizontal = 16.dp
                    ),
            ) {
                Text(text = "counted : ${viewModel.count1.value}")
            }
        }
    }

    @Composable
    fun LazyColumnRowEx() {
        val list = listOf<List<String>>(listOf("가", "나", "다", "라"), listOf("마", "바", "사", "아"))
        Column {
            LazyRow() {
                items(list) { item ->
                    Column {
                        item.forEach {
                            KoreanItem(it)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(12.dp))
            LazyColumn(
                modifier = Modifier,
            ) {
                items(list) { item ->
                    Row {
                        item.forEach {
                            KoreanItem(it)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun KoreanItem(item: String) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable { Log.d("lazy", item) },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Text(
                text = item,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    @Composable
    fun ProgressIndicatorEx() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            LinearProgressIndicator(
                progress = viewModel.progressAmount.value,
                modifier = Modifier.height(12.dp),
                color = Color.Yellow,
                trackColor = Color.LightGray
            )
            Spacer(modifier = Modifier.padding(12.dp))
            CircularProgressIndicator(
                progress = viewModel.progressAmount.value,
                modifier = Modifier.height(12.dp),
                color = Color.Blue,
                trackColor = Color.LightGray
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Row {
                Button(
                    onClick = { viewModel.upProgress() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Cyan,
                        containerColor = Color.Gray
                    ),
                ) {
                    Text(text = "Up")
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Button(
                    onClick = { viewModel.downProgress() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Magenta,
                        containerColor = Color.Green
                    )
                ) {
                    Text(text = "Down")
                }
            }
        }

    }

    @Composable
    fun Buttons() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonFormatEx(CutCornerShape(20.dp), "CutCornerShape")
            Spacer(modifier = Modifier.padding(8.dp))
            ButtonFormatEx(RoundedCornerShape(20.dp), "RoundedCornerShape")
        }
    }

    @Composable
    fun ButtonFormatEx(shape: Shape, text: String) {
        Button(
            onClick = { Toast.makeText(this, "$text button 눌림!", Toast.LENGTH_SHORT).show() },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Cyan,
                containerColor = Color.Gray
            ),
            border = BorderStroke(2.dp, Color.Magenta),
            shape = shape,
        ) {
            Text(text)
        }
    }

    @Composable
    fun NavEx() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeEx(navController)
            }
            composable("detail/{number}") {
                DetailEx(number = it.arguments?.getString("number").toString(), nav = navController)
            }
        }
    }

    @Composable
    fun HomeEx(nav: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home", fontSize = 40.sp)
            Spacer(modifier = Modifier.padding(12.dp))
            Button(onClick = { nav.navigate("detail/1") }) {
                Text("Go Detail1")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { nav.navigate("detail/2") }) {
                Text("Go Detail2")
            }
        }

    }

    @Composable
    fun DetailEx(number: String, nav: NavHostController) {

        val goDetailNumber = if (number == "1") {
            2
        } else {
            1
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = number, fontSize = 40.sp)
            Spacer(modifier = Modifier.padding(12.dp))
            Button(onClick = { nav.popBackStack(route = "home", inclusive = false) }) {
                Text("return home")
            }

            Spacer(modifier = Modifier.padding(12.dp))
            Button(onClick = { nav.navigate("detail/$goDetailNumber") }) {
                Text("Go Detail$goDetailNumber")
            }

        }
    }

    @Composable
    fun RetrofitEx() {
        viewModel.fetchAllUserInfo()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.allUserInfo.value) { item ->
                Column {
                    UserItem(item)

                }
            }
        }

    }

    @Composable
    fun UserItem(item: UserInfoModelItem) {
        Card(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = "id: ${item.id}")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = "name: ${item.name}")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = "email: ${item.email}")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "address: ${item.address.street} ${item.address.suite} ${item.address.city}"
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = "phone: ${item.phone}")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "website: ${item.website}"
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "company: ${item.company.name} ${item.company.catchPhrase} ${item.company.bs}"
                )
                Spacer(modifier = Modifier.padding(4.dp))

            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun GetPostByRandomId() {
        viewModel.fetchPost((1..100).random())
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.fetchPost((1..100).random()) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }) {
            if (viewModel.post.value.id != 0) {
                Card(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Posit Id: ${viewModel.post.value.id}"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "User Id: ${viewModel.post.value.userId}"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Title: ${viewModel.post.value.title}"
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Body: ${viewModel.post.value.body}"
                        )
                        Spacer(modifier = Modifier.padding(4.dp))

                    }
                }
            }
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
            //SurfaceEx()
            //ScaffoldEx()
            //LazyColumnRowEx()
            //ProgressIndicatorEx()
            //Buttons()
            //NavEx()
            //RetrofitEx()
            GetPostByRandomId()
        }
    }

}




