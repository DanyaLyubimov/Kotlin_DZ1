package com.example.dz1


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateSquares()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CreateSquares() {
    var remN by rememberSaveable {
        mutableStateOf(0)
    }
    val inLineN: Int
    val configuration = LocalConfiguration.current
    inLineN = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            integerResource(id = R.integer.landscape_length)
        }

        else -> {
            integerResource(id = R.integer.portrait_length)
        }
    }

    Surface(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(10.dp)
        ) {


            Column(
                modifier = Modifier
                    .weight(4f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (i in 0 until remN / inLineN) {
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        for (j in 1..inLineN) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(if ((i * inLineN + j) % 2 == 0) Color.Red else Color.Blue)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "${i * inLineN + j}",
                                    fontSize = 40.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    for (j in 1..remN % inLineN) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(if ((remN - remN % inLineN + j) % 2 == 0) Color.Red else Color.Blue)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "${remN - remN % inLineN + j}",
                                fontSize = 40.sp,
                                color = Color.White
                            )
                        }
                    }
                    repeat(inLineN - remN % inLineN) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
                Text(text = "$remN", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Box(contentAlignment = Alignment.Center, modifier = Modifier
                    .clickable {
                        remN++
                    }
                    .size(60.dp)
                    .border(2.dp, Color.Black)
                    .background(Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Добавить квадрат",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}
