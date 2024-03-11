package com.example.keytrackerapp.View.AppScreen.Transit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.filmushits.Etities.RequestBodies.LoginRequestBody
import com.example.keytrackerapp.R
import com.example.keytrackerapp.View.AppScreen.RequestList.RequestList
import com.example.keytrackerapp.View.AppScreen.RequestList.bottomBorder
import com.example.keytrackerapp.domain.Entities.Models.UserModel

@Composable
fun TransitOfferScreen(navController: NavHostController, id: String) {
    val transitViewModel: TransitViewModel = viewModel()
    var users by remember { mutableStateOf<List<UserModel>?>(null) }

    LaunchedEffect(Unit){
        users = transitViewModel.getUsers().await()?.users ?: emptyList()
    }
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
            Row(
                Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.End) {
                Text(
                    text = "TransitOffer to:",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color("#0072bc".toColorInt())
                    )
                )
            }
        }
        if (users != null && users!!.isNotEmpty()){
            UsersList(users!!, id, navController)
        }
    }
}
@Composable
fun UsersList (users: List<UserModel>, id: String, navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.Start,
    ) {
        users.forEach { user ->
            UserItem(user = user, id, navController)
        }
    }
}

@Composable
fun UserItem (user: UserModel, id: String, navController: NavHostController){

    val transitViewModel: TransitViewModel = viewModel()

    Card(
        border = BorderStroke(1.dp,Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color("#D8ECFF".toColorInt())
        )
        ,
        modifier = Modifier.fillMaxWidth(0.9f)
            .clickable {
                val job = transitViewModel.transitOffer(id, user.id)
                job.invokeOnCompletion {
                    if (!job.isCancelled)
                        navController.navigate("AppScreen")
                }
            }
    ) {
        Column(modifier = Modifier.padding(6.dp)
            .fillMaxWidth()) {
            Text (text = user.fullName)
        }
    }
}