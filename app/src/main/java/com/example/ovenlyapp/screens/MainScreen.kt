import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ovenlyapp.R
import com.example.ovenlyapp.Route

@Composable
fun MainScreen() {
    val innerNavController = rememberNavController()
    var selectedIndex by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val navItems = listOf(
        Route.Home,
        "Explore",
        "Cart",
        "Favorites",
        Route.Settings
    )

    Scaffold(
        bottomBar = {
            FloatingBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                    innerNavController.navigate(navItems[index]) {
                        popUpTo(innerNavController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = innerNavController,
            startDestination = Route.Home,
            modifier = Modifier.padding(padding)
        ) {
            composable(Route.Home) { HomeScreen(navController) }

            composable("Explore") {  Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Soon",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),

                        )
                    Spacer(modifier = Modifier.width(8.dp))
                    PizzaLoadingAnimation()
                }

            }
            }
            composable("Cart") {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Soon",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        PizzaLoadingAnimation()
                    }

                }
            }

            composable("Favorites") {  Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Soon",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),

                        )
                    Spacer(modifier = Modifier.width(8.dp))
                    PizzaLoadingAnimation()
                }

            } }
            composable(Route.Settings){ SettingsScreen() }
        }
    }
}

sealed class BottomNavItem {
    data class VectorIcon(val icon: ImageVector) : BottomNavItem()
    data class DrawableIcon(@DrawableRes val resId: Int) : BottomNavItem()
}
@Composable
fun FloatingBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    // Icons.Default is ImageVector and R.drawble is int so we have issues
    // to fix this issus we should use  sealed class

    val items = listOf(
        BottomNavItem.VectorIcon(Icons.Default.Home),
        BottomNavItem.DrawableIcon(R.drawable.menu_book_icon),
        BottomNavItem.DrawableIcon(R.drawable.bag_icon),
        BottomNavItem.DrawableIcon(R.drawable.favorite_icon),
        BottomNavItem.DrawableIcon(R.drawable.person_icon)
    )

    val bgColor = Color(0xFF121212) // bg of navBar
    val selectedColor = Color(0xFF9B4C2C) // color of selected item
    val unselectedColor = Color.Gray // color of unselected item

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // row to display all items
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(bgColor)
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .fillMaxWidth(0.95f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // loop to display all items
            items.forEachIndexed { index, item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onItemSelected(index) }
                ) {
                    if (index == selectedIndex) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.White, CircleShape)
                        )
                    }

                    when (item) {
                        is BottomNavItem.VectorIcon -> {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = if (index == selectedIndex) selectedColor else unselectedColor,
                                modifier = Modifier.size(if (index == selectedIndex) 20.dp else 24.dp)
                            )
                        }

                        is BottomNavItem.DrawableIcon -> {
                            Icon(
                                painter = painterResource(id = item.resId),
                                contentDescription = null,
                                tint = if (index == selectedIndex) selectedColor else unselectedColor,
                                modifier = Modifier.size(if (index == selectedIndex) 20.dp else 24.dp)
                            )
                        }

                        else -> {}
                    }
                }
            }

        }
    }
}
@Composable
fun PizzaLoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing)
        ),
        label = "rotationAnim"
    )

    // Keep it compact, not full screen
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until 3) {
            Image(
                painter = painterResource(id = R.drawable.pepperoni_pizza),
                contentDescription = "Pizza Slice",
                modifier = Modifier
                    .size(28.dp)
                    .graphicsLayer {
                        rotationZ = rotation + (i * 120) // Stagger slices
                    }
                    .padding(4.dp)
            )
        }
    }
}


