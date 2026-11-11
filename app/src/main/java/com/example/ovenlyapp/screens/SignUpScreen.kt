import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ovenlyapp.R
import com.example.ovenlyapp.Route
import com.example.ovenlyapp.ui.theme.Bg_Color_Primary
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.Sub_Text_Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController : NavHostController) {
    var languageExpanded by remember { mutableStateOf(false) }
    var phoneCodeExpanded by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }

    var containerColorBtn = if(phoneNumber.isNotEmpty()) Bg_Color_Primary else Bg_color_Second
    var textColorBtn = if(phoneNumber.isNotEmpty()) Color.White else Sub_Text_Color
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical =32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo and App Name
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ovenly",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                )
            }

            // Language Dropdown
            Column {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Sub_Text_Color.copy(alpha = 0.1f))
                        .clickable { languageExpanded = true }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down_icon),
                        contentDescription = "EN",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "English",
                        color = Sub_Text_Color,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }

                DropdownMenu(
                    expanded = languageExpanded,
                    onDismissRequest = { languageExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_down_icon),
                                    contentDescription = "EN",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "English",
                                    color = Sub_Text_Color,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )
                            }
                        },
                        onClick = { languageExpanded = false }
                    )
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_down_icon),
                                    contentDescription = "FR",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Français",
                                    color = Sub_Text_Color,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                )
                            }
                        },
                        onClick = { languageExpanded = false }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Welcome texts
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Welcome to Ovenly",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We’ll send you a verification code to get started",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Sub_Text_Color
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Phone Number Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Phone number")
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append(" *")
                    }
                },
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Row: Phone Code + Input Placeholder (no TextField yet)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                // Phone Code Selector
                Row(
                    modifier = Modifier
                        .height(50.dp) // 👈 نفس الارتفاع
                        .clip(RoundedCornerShape(8.dp))
                        .background(Sub_Text_Color.copy(alpha = 0.1f))
                        .clickable { phoneCodeExpanded = true }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "+213",
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down_icon),
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Phone Number Input
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        if(it.length <= 10 && it.all {char -> char.isDigit()}){
                            phoneNumber = it
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Bg_Color_Primary,
                        unfocusedBorderColor = Sub_Text_Color,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    placeholder = {
                        Text(
                            text = "9876XXXXXX",
                            textAlign = TextAlign.Start,
                            color = Sub_Text_Color,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    },
                    singleLine = true
                )
                DropdownMenu(
                    expanded = phoneCodeExpanded,
                    onDismissRequest = { phoneCodeExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("+213", fontFamily = FontFamily(Font(R.font.poppins_regular))) },
                        onClick = { phoneCodeExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("+33", fontFamily = FontFamily(Font(R.font.poppins_regular))) },
                        onClick = { phoneCodeExpanded = false }
                    )
                }
            }

            // DropdownMenu for phone code

            Spacer(modifier = Modifier.height(16.dp))
            // Column of continue btn anf terms and privacy
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){

                Button(
                    onClick = {
                        navController.navigate(Route.ConfirmNumber)
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColorBtn,
                        contentColor = textColorBtn
                    )
                ){
                    Text("Continue", fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_semibold)))

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        append("You agree to our")
                        withStyle(style = SpanStyle(color = Bg_Color_Primary)) {
                            append(" Terms of Service")
                        }
                        append(" & ")
                        withStyle(style = SpanStyle(color = Bg_Color_Primary)) {
                            append("Privacy Policy.")
                        }
                    },
                    color = Sub_Text_Color,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )



            }
        }
    }
}
