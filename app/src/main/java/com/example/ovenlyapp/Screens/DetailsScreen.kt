import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ovenlyapp.R
import com.example.ovenlyapp.ui.theme.Bg_Color_Primary
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.Sub_Text_Color
import com.example.ovenlyapp.ui.theme.White

@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {
    var cpt by remember { mutableIntStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }
    val images = listOf(
        R.drawable.mushroom,
        R.drawable.pepperoni,
        R.drawable.spinach,
        R.drawable.food,
        R.drawable.pineapple
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Bg_color_Second)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical =32.dp),
            ) {
                // Back Button
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_icon),
                    contentDescription = "Return Back",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White)
                        .padding(6.dp)
                )

                // Image and Counter Column
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pepperoni_pizza),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(280.dp)
                            .width(280.dp)
                    )

                    // Counter Box
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(32.dp)
                            .clip(CircleShape)
                            .background(Bg_Color_Primary)
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "-",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                modifier = Modifier.clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    if (cpt > 0) cpt--
                                }
                            )

                            Text(
                                text = cpt.toString(),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold))
                            )

                            Text(
                                text = "+",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                modifier = Modifier.clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    if (cpt < 10) cpt++
                                }
                            )
                        }
                    }
                }

                // Favorite Button
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.selected_heart_icon
                        else R.drawable.unselected_heart_icon
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White)
                        .padding(6.dp)
                        .clickable { isFavorite = !isFavorite }
                )
            }

            // Column of Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Color.White)
                    .padding(bottom = 72.dp) // space for bottom bar
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Popular Pizza",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )

                    Row {
                        Text(
                            text = "4.5",
                            color = Sub_Text_Color,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.star_icon),
                            contentDescription = "Rating",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                ReadText("Baked to perfection on a crispy golden crust, this pizza delivers the perfect balance of bold flavors and cheesy goodness")
                Spacer(modifier = Modifier.height(16.dp))

                // time :: kcal :: hot
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.alarm_clock_icon),
                            contentDescription = "Time",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "8-16 Min",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.hot_pepper_icon),
                            contentDescription = "Hot",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Medium",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.fire_icon),
                            contentDescription = "Kcal",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "250 Kcal",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Toppings",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    images.forEach { image ->
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Bg_color_Second),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }
        }

        // Bottom Bar
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Total Price",
                    color = Sub_Text_Color,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Row(
                    horizontalArrangement = Arrangement.Center
                ){

                    Text(
                        text = "$10.00",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$12.99",
                        color = Sub_Text_Color,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        // to make line through
                        textDecoration =  TextDecoration.LineThrough
                    )
                }
            }
            Button(
                onClick = { /* Add to cart action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Bg_Color_Primary),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.width(196.dp).height(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bag_icon), // Your icon
                    contentDescription = "Favorite",
                    modifier = Modifier.size(50.dp)
                )

                Text(
                    text = "Add to Bug",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                )
            }
        }
    }
}

@Composable
fun ReadText(text: String) {
    var expanded by remember { mutableStateOf(false) }
    var textWeight = if (text.length > 100) text.take(100) + "..." else text

    Column {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = if (expanded) text else textWeight,
            color = Sub_Text_Color,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        TextButton(onClick = { expanded = !expanded }) {
            Text(
                text = if (expanded) "Read Less" else "Read More",
                color = Bg_Color_Primary,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
    }
}
