package com.example.keytrackerapp.View.AppScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.ui.theme.RequestItemText


@Composable
fun RequestListScreen(navController: NavHostController){
    val reqs = ArrayList<String>()
    reqs.add("aa")
    reqs.add("bb")
    reqs.add("cc")
    reqs.add("dd")
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(1.dp, Color("#949695".toColorInt()))){
            Row(Modifier.padding(12.dp)) {
                Image(painter = painterResource(id = R.drawable.tsu_logo),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop)

                Text(
                    text = ".Keys",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color("#0072bc".toColorInt())
                    )
                )
            }
            Row(Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.End) {
                Box(Modifier.padding(horizontal = 6.dp)){
                    Image(painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("AddRequestScreen")
                        })
                }
                Box(Modifier.padding(horizontal = 6.dp)) {
                    Image(painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("ProfileScreen")
                        })
                }
            }
        }
        RequestList(reqs = reqs, navController)
    }
}

@Composable
fun RequestList(reqs: List<String>, navController: NavHostController) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        reqs.forEach { req ->
            RequestItem(req, navController)
        }
    }
}

@Composable
fun RequestItem(req: String, navController: NavHostController){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(5.dp))
        .background(color = Color.Gray)
        .fillMaxWidth()
        .padding(6.dp)
        .clickable {
            navController.navigate("DetailsScreen")
        }){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            RequestItemText(text = "Аудитория")
            RequestItemText(text = "Время")
        }
        RequestItemText(text = "Фамилия Имя Отчество")
        RequestItemText(text = "Статус")
    }
}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)