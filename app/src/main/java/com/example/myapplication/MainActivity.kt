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
import androidx.compose.ui.graphics.vector.ImageVector
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
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCardTheme(
    darkTheme: Boolean = androidx.compose.foundation.isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        androidx.compose.material3.darkColorScheme(
            primary = Color(0xFFFFB4AB), // Lighter red for dark mode
            background = Color(0xFF1A1C1E),
            surface = Color(0xFF222427),
            onPrimary = Color(0xFF690005),
            onBackground = Color(0xFFE2E2E6),
            onSurface = Color(0xFFE2E2E6),
            secondary = Color(0xFFD4AF37) // Accent
        )
    } else {
        androidx.compose.material3.lightColorScheme(
            primary = Brand.Primary,
            background = Brand.Background,
            surface = Brand.CardBackground,
            onPrimary = Color.White,
            onBackground = Color.Black,
            onSurface = Color.Black,
            secondary = Brand.Accent
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

object Brand {
    // Colors
    val Background = Color(0xFFF4F4F4)
    val Primary = Color(0xFF800000)
    val Accent = Color(0xFFD4AF37)
    val CardBackground = Color.White
    val SecondaryText = Color.Gray

    // Sizes & Spacing
    val AvatarSize = 140.dp
    val AvatarBorder = 4.dp
    val CardPadding = 24.dp
    val CardInnerPadding = 24.dp
    val CardElevation = 8.dp
    val CornerRadius = 20.dp
    val SpacingSmall = 12.dp
    val SpacingMedium = 20.dp
    val SpacingLarge = 30.dp
    val IconSpacing = 15.dp

    // Typography
    val TitleSize = 30.sp
    val SubtitleSize = 18.sp
    val BodySize = 18.sp
}

@Composable
fun BusinessCard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Brand.CardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Brand.CardElevation
            ),
            shape = RoundedCornerShape(Brand.CornerRadius)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Brand.CardInnerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(Brand.AvatarSize)
                        .clip(CircleShape)
                        .border(
                            Brand.AvatarBorder,
                            MaterialTheme.colorScheme.secondary,
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Brand.SpacingMedium))

                Text(
                    text = "Doniel Eunson Ko",
                    fontSize = Brand.TitleSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "BS Information Technology Student",
                    fontSize = Brand.SubtitleSize,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(Brand.SpacingLarge))

                ContactRow(
                    icon = Icons.Default.Phone,
                    text = "+63 919 927 6060",
                    onClickLabel = "Call Phone"
                ) {
                    // Action stub for calling
                }

                Spacer(modifier = Modifier.height(Brand.SpacingSmall))

                ContactRow(
                    icon = Icons.Default.Email,
                    text = "deko74415@liceo.edu.ph",
                    onClickLabel = "Send Email"
                ) {
                    // Action stub for emailing
                }

                Spacer(modifier = Modifier.height(Brand.SpacingSmall))

                ContactRow(
                    icon = Icons.Default.Person,
                    text = "github.com/kodoniel19",
                    onClickLabel = "Visit Profile"
                ) {
                    // Action stub for visiting profile
                }

            }
        }
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
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
            contentDescription = null, // Accessibility handled by onClickLabel and Text
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(Brand.IconSpacing))

        Text(
            text = text,
            fontSize = Brand.BodySize,
            color = MaterialTheme.colorScheme.onSurface
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
    BusinessCardTheme {
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
    BusinessCardTheme {
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
    BusinessCardTheme {
        BusinessCard()
    }
}
