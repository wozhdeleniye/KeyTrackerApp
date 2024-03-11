package com.example.keytrackerapp.View.AppScreen.Transit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.View.AppScreen.RequestList.bottomBorder
import com.example.keytrackerapp.View.AppScreen.UserProfile.ProfileViewModel
import com.example.keytrackerapp.View.AppScreen.convertDateFormat
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody
import com.example.keytrackerapp.ui.theme.TextButtonLabel

@Composable
fun TransitAcception(navHostController: NavHostController, id: String) {
    val transitViewModel: TransitViewModel = viewModel()
    Column(verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()) {
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
            Row(
                Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Accept?",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color("#0072bc".toColorInt())
                    )
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp ),
            modifier = Modifier.fillMaxWidth()){
            TextButton(modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = Color("#188DFE".toColorInt()),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(0.dp),
                onClick = {
                    val job = transitViewModel.accept(id, "true")
                    job.invokeOnCompletion {
                        if (!job.isCancelled) navHostController.navigate("TransitRequests")
                    }
                }
            ){
                TextButtonLabel(text = "Принять")
            }
            TextButton(modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = Color("#188DFE".toColorInt()),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(0.dp),
                onClick = {
                    val job = transitViewModel.accept(id, "false")
                    job.invokeOnCompletion {
                        if (!job.isCancelled) navHostController.navigate("TransitRequests")
                    }
                }
            ){
                TextButtonLabel(text = "Отказать")
            }
        }
    }
}