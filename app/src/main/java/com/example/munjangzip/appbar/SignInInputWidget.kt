package com.example.munjangzip.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignInInputWidget(textLabel: String, textInputValue: String, onTextChange: (String) -> Unit, textFieldColor: Color, fishImage :Int) {
    TextField(
        value = textInputValue,
        onValueChange = onTextChange, // 상위에서 값 관리
        placeholder = { Text(textLabel, modifier = Modifier.fillMaxWidth(), color = Color.Gray, textAlign = TextAlign.Center) },
        singleLine = true,
        modifier = Modifier
            .padding(4.dp)
            .height(55.dp)
            .width(120.dp)
        //.shadow(4.dp)
        //.zIndex(0f)
        ,
        shape = RoundedCornerShape(20.dp),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Gray
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = textFieldColor, //매개변수로 받은 컬러
            unfocusedContainerColor = textFieldColor,
            cursorColor = Color.Black,
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
    )
    Image(
        painter = painterResource(fishImage),
        contentDescription = "붕어빵 아이콘",
        modifier = Modifier
            .size(80.dp)
            //.align(Alignment.CenterStart)
            .offset(x = (-40).dp)
        //.zIndex(1f)
    )
}
