
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ovenlyapp.R
import com.example.ovenlyapp.Route
import com.example.ovenlyapp.ui.theme.Bg_Color_Primary
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.Sub_Text_Color
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ConfirmNumberScreen(navController : NavHostController) {
    val otpValues = remember { mutableStateListOf("", "", "", "", "") } // can use mutableStateListOf to store list values
    val focusRequesters = List(5) { FocusRequester() }
    var showLoading by remember { mutableStateOf(false) }

    /*  ------ logic ------
    * 1:: create head
    * 2:: create texts
    * 3:: create otp input
    * 4:: make otp input focusable
    * 5:: can jump auto to next input
    * 6:: can jump auto to prev input
    * 7:: if we are in the last input make bg darker with cirlce indicator
    * 8:: go to the next page
    * ------ end ------  */


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
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
            Text(
                text = "Need help?",
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline, // to put line under text
                color = Bg_Color_Primary,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            otpValues.forEachIndexed { index, value ->
                OtpInput(
                    value = value,
                    onValueChange = { newValue ->
                        otpValues[index] = newValue
                        if (index == 4 && newValue.isNotEmpty()) {
                            showLoading = true
                        }
                    },
                    focusRequester = focusRequesters[index],
                    nextFocusRequester = focusRequesters.getOrNull(index + 1),
                    prevFocusRequester = focusRequesters.getOrNull(index - 1)
                )
            }
        }
        LaunchedEffect(Unit) {
            focusRequesters[0].requestFocus()
        }
        Spacer(modifier = Modifier.height(16.dp))
        // text of try another way to login
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Try another way",
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = Bg_Color_Primary,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
            )
        }

    }
    // darker bg when we are in the last input
    if(showLoading){
        LaunchedEffect(Unit) {
            delay(3000.milliseconds)
            navController.navigate(Route.Main)
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.Black.copy(0.3f)),
            contentAlignment = Alignment.Center
        ){
            // custom progress indicator
            // TwoToneProgressIndicator()


            // cirlce indicator
            AnimatedCircularProgress()

        }
    }
}

@Composable
fun AnimatedCircularProgress() {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(50.milliseconds)
            progress += 0.01f
        }
    }

    CircularProgressIndicator(
        // coerceIn is used to make sure the progress is between 0 and 1
        progress = {progress.coerceIn(0f, 1f)},
        color = Color(0xFFB3472C),
        trackColor = Color.White,
        strokeWidth = 6.dp,
        modifier = Modifier.size(48.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInput(
    value : String,
    onValueChange : (String) -> Unit = {},
    focusRequester : FocusRequester,
    nextFocusRequester : FocusRequester?,
    prevFocusRequester : FocusRequester?
    ) {
    TextField(
        value = value ,
        onValueChange = {
            // check is inputs is digits
            if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                onValueChange(it)
                // check if the current input is filled and jump to the next input
                if(it.isNotEmpty()){
                    nextFocusRequester?.requestFocus()
                }else{
                    // if the current input is empty and jump to the prev input
                    prevFocusRequester?.requestFocus()
                }

            }
        },
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp))
            // make input focusable
            .focusRequester(focusRequester)
            .background(Bg_color_Second),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            color = Sub_Text_Color,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            textAlign = TextAlign.Center
        ),
        // hide text like password 
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Bg_color_Second,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Bg_Color_Primary,

        )
    )
}

//@Composable
//fun TwoToneProgressIndicator() {
//    val infiniteTransition = rememberInfiniteTransition(label = "")
//
//    val angle by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 1000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        ),
//        label = ""
//    )
//
//    Canvas(modifier = Modifier.size(48.dp)) {
//        val stroke = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
//
//        // Background arc (white)
//        drawArc(
//            color = Color.White,
//            startAngle = angle,
//            sweepAngle = 270f,
//            useCenter = false,
//            style = stroke
//        )
//
//        // Foreground arc (red)
//        drawArc(
//            color = Color(0xFFB3472C),
//            startAngle = angle + 270f,
//            sweepAngle = 90f,
//            useCenter = false,
//            style = stroke
//        )
//    }
//}


