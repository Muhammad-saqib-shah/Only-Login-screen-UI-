package com.example.uiusingjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController){

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF3F7FF)),
        contentAlignment = Alignment.Center) {
        androidx.compose.material3.Surface(
          modifier = Modifier.padding(24.dp),
            shadowElevation = 12.dp,
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(24.dp),
            color = Color.White
        ){
            Image(painter = painterResource(R.drawable.home),
                contentDescription = "",
                modifier = Modifier.size(280.dp).padding(16.dp))
        }
    }
}