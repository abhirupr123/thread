package com.example.thread

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thread.ui.theme.ThreadTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThreadTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Thread Test",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

                    CircularProgressIndicator(color = Color.Green)
                    Loop()
                }
            }
        }
    }
}

// Thread

@Composable
fun Loop(){
    var flag= remember {
        mutableStateOf(false)
    }
    var run= remember {
        mutableStateOf(0)
    }
    fun loop() {
        run.value=1
        val thread = Thread {
            for (i in 0..100000) {
                println(i)
            }
            flag.value = true
        }
        thread.start()

    }
    Button(onClick = { loop() }, modifier = Modifier.padding(10.dp)) {
        if(flag.value==false && run.value==0)
        Text(text = "Run Process")
        else if(flag.value==false && run.value==1)
            Text(text = "Process Running")
        else Text(text = "Process Completed")
    }
}


// Without thread

//@Composable
//fun Loop(){
//    var flag= remember {
//        mutableStateOf(false)
//    }
//    var run= remember {
//        mutableStateOf(0)
//    }
//    fun loop() {
//        run.value=1
//        for (i in 0..100000) {
//            println(i)
//        }
//        flag.value = true
//    }
//    Button(onClick = { loop() }, modifier = Modifier.padding(10.dp)) {
//        if(flag.value==false && run.value==0)
//        Text(text = "Run Process")
//        else if(flag.value==false && run.value==1)
//            Text(text = "Process Running")
//        else Text(text = "Process Completed")
//    }
//}

// Coroutine

//@Composable
//fun Loop(){
//    var flag= remember {
//        mutableStateOf(false)
//    }
//    var run= remember {
//        mutableStateOf(0)
//    }
//    fun loop()= GlobalScope.launch {
//        run.value=1
//        for (i in 0..100000) {
//            println(i)
//        }
//        flag.value = true
//    }
//    Button(onClick = { loop() }, modifier = Modifier.padding(10.dp)) {
//        if(flag.value==false && run.value==0)
//        Text(text = "Run Process")
//        else if(flag.value==false && run.value==1)
//            Text(text = "Process Running")
//        else Text(text = "Process Completed")
//    }
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ThreadTheme {
//        Greeting("Android")
//    }
//}