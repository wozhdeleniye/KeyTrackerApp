package com.example.keytrackerapp.View.AppScreen.RequestList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.keytrackerapp.R
import com.example.keytrackerapp.domain.Entities.Enums.RequestStatusEnum
import com.example.keytrackerapp.domain.Entities.Models.ReqModel
import com.example.keytrackerapp.ui.theme.RequestItemText


@Composable
fun RequestListScreen(navController: NavHostController){
    val profileViewModel: RequestListViewModel = viewModel()

    var requests by remember { mutableStateOf<List<ReqModel>?>(null)}

    LaunchedEffect(Unit) {
        requests = profileViewModel.getReqList().await()?.requests ?: emptyList()
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
            Row(Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.End) {
                Box(Modifier.padding(horizontal = 6.dp)){
                    Image(painter = painterResource(id = R.drawable.baseline_call_received_24),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            navController.navigate("TransitRequests")
                        })
                }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(0.dp)
        ) {
            if (requests != null && requests!!.isNotEmpty()) {
                RequestList(reqs = requests!!, navController)
            }
        }

    }
}

@Composable
fun RequestList(reqs: List<ReqModel>, navController: NavHostController) {
    Column(
        Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        reqs.forEach { req ->
            RequestItem(req, navController)
        }
    }
}

@Composable
fun RequestItem(req: ReqModel, navController: NavHostController){
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(5.dp))
        .background(color = if (req.status == RequestStatusEnum.IN_PROCESS) Color("#EECF2A".toColorInt())
            else if (req.status == RequestStatusEnum.APPROVED)  Color("#1EC360".toColorInt())
            else if (req.status == RequestStatusEnum.DECLINED)  Color("#DC5858".toColorInt())
            else if (req.status == RequestStatusEnum.GIVEN) Color("#0072BC".toColorInt())
            else Color.Gray
        )
        .padding(6.dp)
        .clickable {
            navController.navigate("DetailsScreen/${req.keyId}")
        }){
        RequestItemText(text = "Деканат: " + req.name)
        RequestItemText(text = "Аудитория: " + req.number)

        val (date, time) = convertDateTimeFormat(req.requestedDateTime)
        RequestItemText(text = "Дата: " + date)
        RequestItemText(text = "Время: " + time)
        RequestItemText(text = "Статус: " + req.status)
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
fun convertDateTimeFormat(dateTimeString: String): Pair<String, String> {
    val parts = dateTimeString.split("T")
    if (parts.size != 2) {
        return Pair("Неверный формат даты и времени", "")
    }

    val datePart = parts[0]
    val timePart = parts[1]

    val dateParts = datePart.split("-")
    if (dateParts.size != 3) {
        return Pair("Неверный формат даты", "")
    }

    val year = dateParts[0]
    val month = dateParts[1]
    val day = dateParts[2]

    val timeParts = timePart.split(":")
    if (timeParts.size != 3) {
        return Pair("Неверный формат времени", "")
    }

    val hour = timeParts[0]
    val minute = timeParts[1]

    val dateOutput = "$day.$month.$year"
    val timeOutput = "$hour:$minute"

    return Pair(dateOutput, timeOutput)
}
