package com.example.keytrackerapp.View.AppScreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.ui.theme.AddRequestContent
import com.example.keytrackerapp.ui.theme.AddRequestHeader
import com.example.keytrackerapp.ui.theme.TextButtonLabel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AddRequestScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableStateOf(1) }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(1.dp, Color("#949695".toColorInt()))){
                Row(Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Image(painter = painterResource(id = R.drawable.goback_icon),
                        contentDescription = null,
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("AppScreen")
                        })
                    Text(
                        text = "Создание заявки",
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
                border = BorderStroke(1.dp,Color.Black),
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
                    AddRequestHeader(text = "Бронь на имя:")
                    AddRequestContent(text = "Гальперина Екатерина Асимовна")
                    AddRequestHeader(text = "Номер пары:")
                    DropDownMenu(onValueChange = { number -> selectedNumber = number })
                    AddRequestHeader(text = "Номер кабинета:")
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
                    navController.navigate("AppScreen")
                    /*if (fillCheckerLog(username, password)) {
                        val job = loginViewModel.login(LoginRequestBody(username, password))
                        job.invokeOnCompletion {
                            if (!job.isCancelled) navController.navigate("AppScreen")
                        }
                    }*/
                }
            ){
                TextButtonLabel(text = "Создать заявку")
            }
        }
        Text("еонец")
    }
}
@Composable
fun DropDownMenu(onValueChange: (Int) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var currentValue by remember { mutableStateOf(1) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row {
            /*OutlinedTextField(
                value = currentValue.toString(),
                onValueChange = {
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Color("#8C9195".toColorInt())
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color("#8C9195".toColorInt()),
                    unfocusedBorderColor = Color("#8C9195".toColorInt())
                ),

            )*/
            AddRequestContent(text = currentValue.toString())
            Box(Modifier.clickable { expanded = !expanded }){
                Image(painter = painterResource(id = R.drawable.menu_icon),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for(i in 1..7){
                MenuItem(onValueChange = {
                        number ->
                    currentValue = number
                    onValueChange(number)
                    }, i)
            }
        }
    }
}
@Composable
fun MenuItem(onValueChange: (Int) -> Unit, i: Int){
    DropdownMenuItem(
        text = { Text(i.toString()) },
        onClick = { onValueChange(i)}
    )
}

