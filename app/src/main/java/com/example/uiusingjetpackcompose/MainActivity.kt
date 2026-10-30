package com.example.uiusingjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uiusingjetpackcompose.ui.theme.BrightGreen
import com.example.uiusingjetpackcompose.ui.theme.UiUsingJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login"){
                composable("login"){
                    LoginScreen(onLoginSuccess = {
                        navController.navigate("home"){
                            popUpTo(0)
                        }
                    })
                }
                composable("home"){
                    HomeScreen(navController = navController)
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F7FF))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(R.drawable.logo_ui),
            contentDescription = "",
            modifier = Modifier.size(150.dp),
            colorFilter = ColorFilter.tint(BrightGreen)
        )


        Text(text = "Welcome Back!", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFF212121))

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Sign in to Continue", fontSize = 18.sp, color = Color(0xFF757575))

        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {

            Column(modifier = Modifier.padding(24.dp)) {
                Text("Username",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121))

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(value = userName,
                    onValueChange = {userName = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text = "Enter Your UserName")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person,
                            contentDescription = "",
                            tint = Color(0xFF757575))
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = BrightGreen,
                        unfocusedTextColor = Color(0xFFBDBDBD)
                    ))

                Spacer(modifier = Modifier.height(24.dp))

                Text("Password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121))

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(value = password,
                    onValueChange = {password = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text = "Enter Your Password")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock,
                            contentDescription = "",
                            tint = Color(0xFF757575))
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = BrightGreen,
                        unfocusedTextColor = Color(0xFFBDBDBD)
                    ),
                    visualTransformation = PasswordVisualTransformation())

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Forgot Password?",
                    fontSize = 16.sp,
                    color = Color(0xFF212121),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 4.dp))
                
                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    if (authenticate(userName,password)){
                        onLoginSuccess()
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Invalid UserName or Password", Toast.LENGTH_SHORT).show()
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightGreen
                    )) {
                    Text(text = "Sign in",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)
                }
            }

        }
    }
}

private fun authenticate(username: String,password: String) : Boolean {
    val validUserName = "admin"
    val validPassword = "admin123"

    return username == validUserName && password == validPassword
}