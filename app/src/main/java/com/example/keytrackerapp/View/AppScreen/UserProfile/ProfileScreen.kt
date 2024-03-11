package com.example.keytrackerapp.View.AppScreen.UserProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.keytrackerapp.View.AppScreen.RequestList.bottomBorder
import com.example.keytrackerapp.View.LogRegScreen.AuthViewModel
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.ui.theme.AddRequestContent
import com.example.keytrackerapp.ui.theme.AddRequestHeader

@Composable
fun ProfileScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    var profileData by remember { mutableStateOf<ProfileModel?>(null) }

    var fullname by remember { mutableStateOf("")}
    var login by remember { mutableStateOf("")}
    var role by remember { mutableStateOf("")}

    LaunchedEffect(Unit) {
        profileData = profileViewModel.getProfile().await()
        fullname = profileData?.fullname ?: ""
        login = profileData?.login ?: ""
        role = profileData?.role ?: ""
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(1.dp, Color("#949695".toColorInt()))){
                Row(Modifier.padding(12.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Image(painter = painterResource(id = R.drawable.goback_icon),
                        contentDescription = null,
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("AppScreen")
                        })
                    Text(
                        text = "Профиль",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(500),
                            color = Color("#188DFE".toColorInt())
                        ),
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                        contentDescription = null,
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            val job = authViewModel.logout()
                            job.invokeOnCompletion {
                                if (!job.isCancelled) {

                                }
                            }
                        })
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.95f)
                    .padding(12.dp)) {
                AddRequestHeader(text = "ФИО:")
                AddRequestContent(text = fullname)
                AddRequestHeader(text = "Логин:")
                AddRequestContent(text = login)
                AddRequestHeader(text = "Роль:")
                AddRequestContent(text = role)
            }
        }
        Text("еонец")
    }
}