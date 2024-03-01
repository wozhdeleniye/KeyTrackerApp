package com.example.keytrackerapp.View.LogRegScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.ui.theme.TextButtonLabel
import com.example.keytrackerapp.ui.theme.TextLabel
import androidx.compose.ui.tooling.preview.Preview as Preview1


@Composable
@Preview1
fun RegScreen(navController: NavHostController) {
    var fio by rememberSaveable {mutableStateOf("")}
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(Modifier.padding(12.dp)) {
            Image(painter = painterResource(id = R.drawable.tsu_logo),
                contentDescription = null)

            Text(
                text = "TSU.Keys",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color("#0072bc".toColorInt())
                ))
        }
        Column(Modifier.fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top)) {
            TextLabel(text = "Регистрация")
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    OutlinedTextField(
                        value = fio,
                        onValueChange = {
                            fio = it
                        },
                        placeholder = {
                            Text("ФИО")
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(
                            color = Color("#8C9195".toColorInt())
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color("#8C9195".toColorInt()),
                            unfocusedBorderColor = Color("#8C9195".toColorInt())
                        )
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        placeholder = {
                            Text("Логин")
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(
                            color = Color("#8C9195".toColorInt())
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color("#8C9195".toColorInt()),
                            unfocusedBorderColor = Color("#8C9195".toColorInt())
                        )
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    OutlinedTextField(
                        trailingIcon = {
                            Icon(painter = painterResource(id = R.drawable.eye),
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        placeholder = {
                            Text("Пароль")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(
                            color = Color("#8C9195".toColorInt())
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color("#8C9195".toColorInt()),
                            unfocusedBorderColor = Color("#8C9195".toColorInt()),
                        )
                    )
                }
            }
        }
        Column(Modifier.padding(12.dp),

            ) {
            Box(
                modifier = Modifier.alpha(
                    if (fillCheckerReg(username, password, fio)) 1f
                    else 0.5f
                )
            ) {
                TextButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color("#188DFE".toColorInt()),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(0.dp),
                    onClick = {
                        navController.navigate("AppScreen")
                        /*if (fillCheckerLog(username, password)) {
                            val job = loginViewModel.login(LoginRequestBody(username, password))
                            job.invokeOnCompletion {
                                if (!job.isCancelled) navController.navigate("AppScreen")
                            }
                        }*/
                    }
                ){
                    TextButtonLabel(text = "Регистрация")
                }
            }
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center)
            {
                Box(){
                    Text(
                        maxLines = 1,
                        text = "Есть аккаунт?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = Color("#6B737A".toColorInt()),
                        )
                    )
                }
                Box(modifier = Modifier
                    .alpha(0.7f)){
                    Text(
                        modifier = Modifier.clickable {
                            navController.navigate("LogScreen")
                        },
                        maxLines = 1,
                        text = "Вход",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color = Color("#188DFE".toColorInt()),
                        )
                    )
                }
            }
        }
    }
}
fun fillCheckerReg(
    username: String, password: String, fio: String
): Boolean {
    return (username != "") and (password != "") and (fio != "")
}