package com.example.ovenlyapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ovenlyapp.R
import com.example.ovenlyapp.Route
import com.example.ovenlyapp.ui.theme.Bg_Color_Primary
import com.example.ovenlyapp.ui.theme.Bg_color_Second
import com.example.ovenlyapp.ui.theme.Black
import com.example.ovenlyapp.ui.theme.Btn_Color
import com.example.ovenlyapp.ui.theme.Sub_Text_Color
import com.example.ovenlyapp.ui.theme.White

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(0) }
    /* ------- logic -------
    * Head of the screen :: Location and profile
    * Search input with filter icon
    * Special offers card with image and text
    * Navbar with :: Home,Cataloge ,Cart, Favorite, Settings
    * Populair Pizza Navigations btns
    *
    * */
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Row of profile circle and location
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Column of location
            Column {
                Text(text = "Location", color = Sub_Text_Color, fontSize = 14.sp)

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp),
                        tint = Bg_Color_Primary,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "New York, USA",
                        color = Sub_Text_Color,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down_icon),
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(16.dp),
                        tint = Sub_Text_Color
                    )
                }
            }
            // Profile image
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .background(Bg_color_Second),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.notifications_icon), // Replace with actual profile icon
                    contentDescription = "Profile",
                    modifier = Modifier.size(40.dp)
                )
            }

        }
        SearchBar(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onFilterClick = { /* Handle filter icon click */ }
        )
        // Special offer card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.card_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Row of content over background
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Texts and button
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Special Offer",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Discount 20% off applied at checkout",
                        color = Sub_Text_Color,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* Handle button click */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Bg_Color_Primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(94.dp)
                            .height(34.dp)
                    ) {
                        Text("Order Now", fontSize = 8.sp, textAlign = TextAlign.Center)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Pizza Image
                Image(
                    painter = painterResource(id = R.drawable.pizza_image_bg),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(250.dp)
                        .width(150.dp)// Adjust size as needed
                )
            }
        }

        // Popular Pizza text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Popular Pizza",
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Text(
                text = "See All",
                color = Bg_Color_Primary,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }

        // Popular Pizza btns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CategoryBtn(
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
        // pizzas carts
        // Pizza lists
        val allPizzas = listOf("Margherita", "Pepperoni", "Cheese Lovers")
        val vegetarianPizzas = listOf("Veggie Deluxe", "Mushroom Spinach", "Tomato Basil")
        val specials = listOf("BBQ Chicken", "Seafood Supreme", "Spicy Beef")
        // when to select items
        val selectedList = when (selectedIndex) {
            0 -> allPizzas
            1 -> vegetarianPizzas
            2 -> specials
            else -> emptyList()
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
       //     selectedList.forEach { pizza ->
//                PizzaCard(pizzaName = pizza)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
            // we dont use foreach directly with lazyclmn  should use


            items(selectedList) { pizza ->
                PizzaCard(pizzaName = pizza ,navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }



    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = { Text("Search your favourite pizza") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        shape = RoundedCornerShape(50.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Bg_color_Second,
            focusedBorderColor = Bg_Color_Primary,
            unfocusedBorderColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = "Filter",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onFilterClick() }
                ,
                tint = Color.Black
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Sub_Text_Color
            )
        }
    )
}

@Composable
fun CategoryBtn(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val selectedColor = Color(0xFF9B4C2C)
    val unselectedColor = Bg_color_Second
    val textColor = Color.White
    val textUnselectedBtn = Btn_Color
    val categories = listOf(
        "All Pizzas",
        "Vegetarian",
        "Specials"
    )

    Row(
        // we can make arragement with values dp and we can add alignment
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(28.dp, Alignment.Start),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        categories.forEachIndexed { index, title ->
            val isSelected = index == selectedIndex
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (isSelected) selectedColor else unselectedColor)
                    .clickable { onItemSelected(index) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = title,
                    color = if (isSelected) textColor else textUnselectedBtn,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun PizzaCard(pizzaName: String ,navController: NavHostController) {
    // mutablestate<boolean> is not boolean
    val isFavorite = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Bg_color_Second)
            .padding(horizontal = 16.dp)
            .clickable {
                navController.navigate(Route.Details)
            }
    ) {
        Row(
            modifier = Modifier.matchParentSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.pepperoni_pizza),
                contentDescription = pizzaName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(128.dp)
            )
            Column{
                // First row :: pizza name and favorite icon
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = pizzaName,
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Icon(
                        painter = painterResource(
                            id = if (isFavorite.value) R.drawable.selected_heart_icon
                            else R.drawable.unselected_heart_icon
                        ),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                // for remove ripple effect
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isFavorite.value = !isFavorite.value
                            }
                    )
                }
                // Second row :: offer text
                Text(
                    text = "Offer valid today only",
                    color = Sub_Text_Color,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                // Row :: of time and rating
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "20 min",
                        color = Sub_Text_Color,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "•",
                        color = Sub_Text_Color,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
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

                // Row :: of price and add to cart btn and discounted price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row{
                        Text(
                            text = "$10.00",
                            color = Black,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "25% Off",
                            color = White,
                            fontSize = 8.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp)) // Clip first
                                .background(Bg_Color_Primary)
                                .padding(horizontal = 8.dp)// Then apply background

                        )

                    }
                    // Add to cart btn
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .size(24.dp)
                    )

                }
            }

        }
    }
}


