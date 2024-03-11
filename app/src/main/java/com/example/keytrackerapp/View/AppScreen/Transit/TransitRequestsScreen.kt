package com.example.keytrackerapp.View.AppScreen.Transit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.keytrackerapp.domain.Entities.Enums.RequestStatusEnum
import com.example.keytrackerapp.domain.Entities.Models.TransferReqsModel
import com.example.keytrackerapp.ui.theme.RequestItemText
import com.example.keytrackerapp.ui.theme.RequestItemTextBlack

@Composable
fun TransitRequestsScreen (navHostController: NavHostController){
    val transitViewModel: TransitViewModel = viewModel()

    var notificationsData by remember { mutableStateOf<List<TransferReqsModel>?>(null) }

    LaunchedEffect(Unit) {
        notificationsData = transitViewModel.getNotifications().await()?: emptyList()
    }
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(1.dp, Color("#949695".toColorInt()))){
            Row(Modifier.padding(12.dp)) {
                Image(painter = painterResource(id = R.drawable.goback_icon),
                    contentDescription = null,
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        navHostController.navigate("AppScreen")
                    })
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
                    text = "TransitRequests",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color("#0072bc".toColorInt())
                    )
                )
            }
        }
        if (notificationsData != null && notificationsData!!.isNotEmpty()) TransitRequestsList(
            notificationsData!!,
            navHostController
        )
    }
}

@Composable
fun TransitRequestsList(nots: List<TransferReqsModel>, navHostController: NavHostController){
    Column(
        Modifier
            .padding(12.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color(("#D8ECFF").toColorInt())),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        nots.forEach { not ->
            NotItem(not, navHostController)
        }
    }
}
@Composable
fun NotItem(not: TransferReqsModel, navHostController: NavHostController){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(5.dp))
        .padding(6.dp)
        .clickable {
            navHostController.navigate("TransitAccept/${not.id}")
        }){
        RequestItemTextBlack(text = "От кого: " + not.userFrom.fullName)
        RequestItemTextBlack(text = "Кому: " + not.userTo.fullName)
        RequestItemTextBlack(text = "Деканат: " + not.keyDTO.officeName)
        RequestItemTextBlack(text = "Номер кабинета: " + not.keyDTO.officeNumber.toString())
    }
}