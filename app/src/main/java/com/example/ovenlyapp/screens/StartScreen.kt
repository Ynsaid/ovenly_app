package com.example.ovenlyapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ovenlyapp.R
import com.example.ovenlyapp.Route
import com.example.ovenlyapp.ui.theme.Bg_Color_Primary
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.Sub_Text_Color
import kotlinx.coroutines.delay

@Composable
fun StartScreen( navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    val images = listOf(
        // to set list of images to painter should use painterResource in list
        painterResource(R.drawable.pizza_img),
        painterResource(R.drawable.image),
        painterResource(R.drawable.image2),
        painterResource(R.drawable.image4),
    )
    val totalSteps = images.size
    var currentStep by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while(true){
            delay(3000)
            currentStep = (currentStep + 1) % totalSteps
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row of logo and language dropdown
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Spread logo and dropdown
        ) {
            // Logo + App name
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Ovenly",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                )
            }

            // Language dropdown trigger
            Column {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { expanded = true }
                        .clip(RoundedCornerShape(16.dp))
                        .background(Sub_Text_Color.copy(alpha = 0.1f))
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down_icon), // replace with your flag icon
                        contentDescription = "EN",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "English",color = Sub_Text_Color, fontSize = 14.sp,modifier = Modifier.padding(horizontal = 8.dp ), fontFamily = FontFamily(Font(R.font.poppins_regular)))
                }

                // Dropdown menu
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_down_icon),
                                    contentDescription = "EN",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(text = "English", color = Sub_Text_Color, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
                            }
                        },
                        onClick = {
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_down_icon),
                                    contentDescription = "FR",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(text = "Français" , color = Sub_Text_Color, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
                            }
                        },
                        onClick = {
                            expanded = false
                            // handle language change
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(64.dp))
        // images
        Image(
            painter = images[currentStep],
            contentDescription = "Pizza",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .width(340.dp)
                .height(222.dp)
                // should put padding before clip to see border radius :/
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(54.dp))
            ,

        )
        Spacer(modifier = Modifier.size(64.dp))
        // Column of texts and dots stepper
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Welcome to Ovenly",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Your favorite pizzas, freshly baked and delivered to your doorstep with love and care!",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Center,
                color = Sub_Text_Color
            )
            Spacer(modifier = Modifier.size(16.dp))
            // row of dots stepper
            DotStepper(
                totalDots = 4,
                selectedIndex = currentStep,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            // 3 dots stepper change the image above

        }
        Spacer(modifier = Modifier.size(64.dp))
        // column of btns login sign up
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Bg_Color_Primary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){
                Text("Log in",fontSize = 14.sp, fontFamily =  FontFamily(Font(R.font.poppins_regular)))
            }
            Spacer(modifier = Modifier.size(4.dp))
            Button(
                onClick = {
                    navController.navigate(Route.Signup)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Bg_color_Second,
                    contentColor = Bg_Color_Primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ){
                Text("Sign me up" ,fontSize = 14.sp, fontFamily =  FontFamily(Font(R.font.poppins_regular)))
            }

        }
    }
}

@Composable
fun DotStepper(modifier: Modifier = Modifier,
               totalDots: Int,
               selectedIndex: Int,
               ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ){
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex) Bg_Color_Primary else Color.LightGray
                    )
            )

        }

    }

}
