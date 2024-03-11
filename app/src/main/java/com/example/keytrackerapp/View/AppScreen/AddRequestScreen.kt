package com.example.keytrackerapp.View.AppScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.View.AppScreen.RequestList.RequestList
import com.example.keytrackerapp.View.AppScreen.RequestList.RequestListViewModel
import com.example.keytrackerapp.View.AppScreen.RequestList.bottomBorder
import com.example.keytrackerapp.View.AppScreen.UserProfile.ProfileViewModel
import com.example.keytrackerapp.domain.Entities.Models.KeyListModel
import com.example.keytrackerapp.domain.Entities.Models.KeyModel
import com.example.keytrackerapp.domain.Entities.Models.ProfileModel
import com.example.keytrackerapp.domain.Entities.Models.ReqModel
import com.example.keytrackerapp.domain.Entities.RequestBodies.CreateReqBody
import com.example.keytrackerapp.ui.theme.AddRequestContent
import com.example.keytrackerapp.ui.theme.AddRequestHeader
import com.example.keytrackerapp.ui.theme.TextButtonLabel
import java.time.LocalDate

@Composable
fun AddRequestScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableStateOf(1) }
    var selectedOffice by remember { mutableStateOf("") }
    var selectedId by remember { mutableStateOf("") }

    val profileViewModel: ProfileViewModel = viewModel()
    val requestViewModel: RequestListViewModel = viewModel()

    var keys by remember { mutableStateOf<List<KeyModel>?>(null)}

    var profileData by remember { mutableStateOf<ProfileModel?>(null) }

    var fullname by remember { mutableStateOf("")}
    var login by remember { mutableStateOf("")}
    var role by remember { mutableStateOf("")}

    LaunchedEffect(Unit) {
        profileData = profileViewModel.getProfile().await()
        fullname = profileData?.fullname ?: ""
        login = profileData?.login ?: ""
        role = profileData?.role ?: ""
        keys = requestViewModel.getKeyList().await()?.keys ?: emptyList()
    }

    var username by rememberSaveable { mutableStateOf("") }
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
                    AddRequestContent(text = fullname)
                    AddRequestHeader(text = "Дата:")
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        placeholder = {
                            Text("dd.mm.yyyy")
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
                    AddRequestHeader(text = "Номер пары:")
                    DropDownMenu(onValueChange = { number -> selectedNumber = number })
                    AddRequestHeader(text = "Номер кабинета:")
                    if (keys != null && keys!!.isNotEmpty()) {
                        DropDownMenuRoom(keys = keys!!, onValueChange = {office -> selectedOffice = office}, onValueChangeId = { keyId -> selectedId = keyId})
                    }
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
                    var dateTime = convertDateFormat(username)
                    if(selectedNumber == 1) dateTime += "T08:45:00"
                    else if(selectedNumber == 2) dateTime += "T10:35:00"
                    else if(selectedNumber == 3) dateTime += "T12:25:00"
                    else if(selectedNumber == 4) dateTime += "T14:45:00"
                    else if(selectedNumber == 5) dateTime += "T16:35:00"
                    else if(selectedNumber == 6) dateTime += "T18:25:00"
                    else if(selectedNumber == 7) dateTime += "T20:15:00"
                    val job = requestViewModel.createReq(CreateReqBody(dateTime, selectedId, selectedOffice))
                    job.invokeOnCompletion {
                        if (!job.isCancelled) navController.navigate("AppScreen")
                    }
                }
            ){
                TextButtonLabel(text = "Создать заявку")
            }
        }
        Text("")
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
            AddRequestContent(text = textReturner(currentValue))
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
        text = { Text(textReturner(i)) },
        onClick = { onValueChange(i)}
    )
}

fun textReturner(i: Int): String{
    var a = "8:45-10:20"
    if (i == 2) a = "10:35-12:10"
    else if (i == 3) a = "12:25-14:00"
    else if (i == 4) a = "14:45-16:20"
    else if (i == 5) a = "16:35-18:10"
    else if (i == 6) a = "18:25-20:00"
    else if (i == 7) a = "20:15-20:50"
    return "Пара: " + i.toString() + " (" + a + ")"
}
@Composable
fun DropDownMenuRoom(keys: List<KeyModel>, onValueChange: (String) -> Unit, onValueChangeId: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var officeNumber by remember { mutableStateOf(keys[0].officeNumber) }
    var officeName by remember { mutableStateOf(keys[0].officeName) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row {
            AddRequestContent(text = officeNumber.toString() + ", " + officeName )
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
            onDismissRequest = { expanded = false },
            //modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            keys.forEach { key->
                DropdownMenuItem(
                    text = { key.officeNumber.toString() + ", " + key.officeName },
                    onClick = {
                        onValueChange(key.officeID)
                        onValueChangeId(key.keyId)
                        officeName = key.officeName
                        officeNumber = key.officeNumber
                    }
                )
            }
        }
    }
}

fun convertDateFormat(dateString: String): String {
    val parts = dateString.split(".")
    if (parts.size != 3) {
        return "Неверный формат даты"
    }

    val day = parts[0]
    val month = parts[1]
    val year = parts[2]

    return "$year-$month-$day"
}



