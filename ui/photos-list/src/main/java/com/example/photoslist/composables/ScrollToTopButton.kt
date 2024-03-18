package com.example.photoslist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        Alignment.BottomCenter,
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(65.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Green,
            ),
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                modifier = Modifier.size(48.dp),
                contentDescription = "arrow up",
            )
        }
    }
}
