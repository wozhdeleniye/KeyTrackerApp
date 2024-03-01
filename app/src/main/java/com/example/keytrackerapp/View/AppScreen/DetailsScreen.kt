package com.example.keytrackerapp.View.AppScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.ui.theme.AddRequestContent
import com.example.keytrackerapp.ui.theme.AddRequestHeader
import com.example.keytrackerapp.ui.theme.TextButtonLabel

@Composable
fun DetailsScreen(navController: NavHostController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(1.dp, Color("#949695".toColorInt()))){
                Row(
                    Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Image(painter = painterResource(id = R.drawable.goback_icon),
                        contentDescription = null,
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("AppScreen")
                        })
                    Text(
                        text = "Детали заявки",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(500),
                            color = Color("#188DFE".toColorInt())
                        ),
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )
                }
            }
            Card(
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color("#D8ECFF".toColorInt())
                )
            ){
                Column(
                    Modifier
                        .fillMaxWidth(0.95f)
                        .padding(12.dp)) {
                    AddRequestHeader(text = "Бронь на имя:")
                    AddRequestContent(text = "Гальперина Екатерина Асимовна")
                    AddRequestHeader(text = "Дата:")
                    AddRequestContent(text = "29.09.2024 08:45")
                    AddRequestHeader(text = "Номер пары:")
                    AddRequestContent(text = "29.09.2024 08:45")
                    AddRequestHeader(text = "Номер кабинета:")
                    AddRequestContent(text = "215")
                }
            }
            TextButton(modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = Color("#188DFE".toColorInt()),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(0.dp),
                onClick = {
                    //navController.navigate("AppScreen")
                    /*if (fillCheckerLog(username, password)) {
                        val job = loginViewModel.login(LoginRequestBody(username, password))
                        job.invokeOnCompletion {
                            if (!job.isCancelled) navController.navigate("AppScreen")
                        }
                    }*/
                }
            ){
                TextButtonLabel(text = "Вернуть ключ в деканат")
            }
            TextButton(modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = Color("#6B737A".toColorInt()),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(0.dp),
                onClick = {
                    //navController.navigate("AppScreen")
                    /*if (fillCheckerLog(username, password)) {
                        val job = loginViewModel.login(LoginRequestBody(username, password))
                        job.invokeOnCompletion {
                            if (!job.isCancelled) navController.navigate("AppScreen")
                        }
                    }*/
                }
            ){
                TextButtonLabel(text = "Передать ключ")
            }
        }
        Text("еонец")
    }
}