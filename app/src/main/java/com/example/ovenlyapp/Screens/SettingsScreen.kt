import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ovenlyapp.R
import com.example.ovenlyapp.ui.theme.Bg_color_Second

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Profile Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Younes Aid", fontSize = 22.sp, color = Color.Black, fontFamily = FontFamily(Font(R.font.poppins_bold)))
            Image(
                painter = painterResource(R.drawable.pict),
                contentScale = ContentScale.Crop,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
            )
        }


        // box of personal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(60.dp)
                .background(Bg_color_Second)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Personal",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.person_icon),
                        contentDescription = "Person Icon",
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.arrow_down_icon),
                    contentDescription = "Arrow Down",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quick actions row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionItem("Favorite", R.drawable.star)
            QuickActionItem("Wallet", R.drawable.purse)
            QuickActionItem("Orders", R.drawable.received)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Menu items
        SettingsItem("Promotions", "Exclusive deals for you", R.drawable.offer_icon)
        SettingsItem("View Rewards", null, R.drawable.cup_icon)
        SettingsItem("Send Feedback", null, R.drawable.send_icon)
        SettingsItem("App Preferences", null, R.drawable.settings_icon)
        SettingsItem("Support", null, R.drawable.baseline_call_24)
        SettingsItem("About Ovenly", "Passion for pizza explained", R.drawable.info_icon)
    }
}

@Composable
fun QuickActionItem(title: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F7F7))
            .padding(12.dp)
            .clickable { }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)))
    }
}

@Composable
fun SettingsItem(title: String, subtitle: String?, iconRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(26.dp))
        Column {
            Text(title, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)))
            if (subtitle != null) {
                Text(subtitle, fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}
