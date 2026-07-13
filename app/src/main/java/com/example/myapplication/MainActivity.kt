package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Brand.Background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

object Brand {
    val Background = Color(0xFFF4F4F4)
    val Primary = Color(0xFF800000)
    val Accent = Color(0xFFD4AF37)
    val Card = Color.White

    val AvatarSize = 140.dp
    val CardPadding = 24.dp
    val CardElevation = 8.dp
    val CornerRadius = 20.dp
}

@Composable
fun BusinessCard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brand.Background)
            .padding(Brand.CardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Brand.Card
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Brand.CardElevation
            ),
            shape = RoundedCornerShape(Brand.CornerRadius)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(Brand.AvatarSize)
                        .clip(CircleShape)
                        .border(
                            4.dp,
                            Brand.Accent,
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Doniel Eunson Ko",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Brand.Primary
                )

                Text(
                    text = "BS Information Technology Student",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(30.dp))

                ContactRow(
                    icon = Icons.Default.Phone,
                    text = "+63 919 927 6060",
                    onClickLabel = "Call Phone"
                ) {
                    // Future call action
                }

                Spacer(modifier = Modifier.height(12.dp))

                ContactRow(
                    icon = Icons.Default.Email,
                    text = "deko74415@liceo.edu.ph",
                    onClickLabel = "Send Email"
                ) {
                }

                Spacer(modifier = Modifier.height(12.dp))

                ContactRow(
                    icon = Icons.Default.Person,
                    text = "github.com/kodoniel19",
                    onClickLabel = "Visit Profile"
                ) {
                }

            }
        }
    }
}

@Composable
fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClickLabel: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = onClickLabel
            ) {
                onClick()
            }
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Brand.Primary
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BusinessCardPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BusinessCardDarkPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(
    name = "Large Font",
    showBackground = true,
    showSystemUi = true,
    fontScale = 1.5f
)
@Composable
fun BusinessCardLargeFontPreview() {
    MaterialTheme {
        BusinessCard()
    }
}