package fr.hiroohg.project_qualdev

import androidx.compose.ui.graphics.Color
import fr.hiroohg.project_qualdev.ui.theme.typesColors
import fr.hiroohg.project_qualdev.ui.theme.typesImages
import io.mockk.*
import org.junit.Test
import org.junit.Assert.assertEquals

class UnitTesting {
    @Test
    fun getColorByType() {
        val type = "Plante"
        val colorType = typesColors[type]
        assertEquals(Color(36, 158, 28), colorType)
    }

    @Test
    fun getIdImageByType() {
        val type = "Plante"
        val idType = typesImages[type]
        assertEquals(R.drawable.herb, idType)
    }
}