package com.example.keytrackerapp.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.keytrackerapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@Composable
fun TextButtonLabel(text: String){
    Text(
        text = text,
        // 15 SB Label
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(400),
            color = Color.White,

            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun TextLabel(text: String){
    Text(
        text = text,
        // 15 M Label
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(500),
            color = Color("#6B737A".toColorInt()),

            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun RequestItemText(text: String){
    Text(
        text = text,
        // 15 M Label
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(500),
            color = Color("#FFFFFF".toColorInt()),

            textAlign = TextAlign.Center,
        )
    )
}
@Composable
fun AddRequestHeader(text: String){
    Text(
        text = text,
        // 15 M Label
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(400),
            color = Color("#6B737A".toColorInt()),

            textAlign = TextAlign.Start,
        )
    )
}

@Composable
fun AddRequestContent(text: String){
    Text(
        text = text,
        // 15 M Label
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.inter)),
            fontWeight = FontWeight(400),
            color = Color("#000000".toColorInt()),

            textAlign = TextAlign.Start,
        )
    )
}