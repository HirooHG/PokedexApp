package fr.hiroohg.project_qualdev.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fr.hiroohg.project_qualdev.R

val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily =
                    FontFamily(
                        Font(R.font.ubuntu_bold, FontWeight.Bold),
                        Font(R.font.ubuntu_light, FontWeight.Light),
                        Font(R.font.ubuntu_medium, FontWeight.Medium),
                        Font(R.font.ubuntu_regular, FontWeight.Normal),
                    ),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            )
    )

