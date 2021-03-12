package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        Greeting("Android")
    }
}

//@Preview(showBackground = true)
@Composable
fun seperatePreview(){
    val list = List(100) {"This is item $it"}
    ComposeAppTheme{
        Column{
            Text("Sample App")
            LazyColumn{
                items(list){ item ->
                    Text("$item")
                }
            }
        }

    }
}

@ExperimentalAnimationApi
@Composable
fun JetPackCard(text: String , modifier: Modifier = Modifier){
    Card(modifier, backgroundColor = Color.LightGray){
        var expanded by remember { mutableStateOf(false) }
        Column(horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier.clickable { expanded =!expanded }) {
            Text(
                text = "This is a Test",
                style = MaterialTheme.typography.body1
            )
            Image(painterResource(R.drawable.jetpackcompose_logo3),
                contentDescription ="jetpack logo",
                modifier = Modifier.height(200.dp).width(200.dp) )
            AnimatedVisibility(expanded) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2
                )

            }
            
        }
    }
}

@Preview
@ExperimentalAnimationApi
@Composable
fun JetPackPreview() {
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = {
            AnimatedVisibility(visible = !scrollState.isScrollInProgress,
                enter = slideInVertically(),
                exit = slideOutVertically(),
            ) {
            TopAppBar(title = {Text("TopAppBar")},backgroundColor = MaterialTheme.colors.primary, elevation = 4.dp) }},
        content = {
            MaterialTheme {
                Column(
                    Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    CardList(scrollState)

                }
            }
        },
        bottomBar = { BottomAppBar(backgroundColor = MaterialTheme.colors.primary) {
            Text(text = "This be a bar")
        }}
    )
}




@ExperimentalAnimationApi
@Composable
fun CardList(scrollState : LazyListState ){
    val list = List(50) { "Item number #$it" }
    LazyColumn (state = scrollState){
        val cardModifier = Modifier
            .clip(RoundedCornerShape(8))
            .fillMaxWidth()
            .padding(10.dp)

        items(list) { item ->
            JetPackCard(item, cardModifier)

        }
    }
}

