package com.example.authdemo.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.authdemo.presentation.ui.theme.PrimaryButtonColor

@Composable
fun CustomButton(
    modifier: Modifier = Modifier, buttonText: String,
    onClick: () -> Unit, enabled: Boolean = true,isProcessing : Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryButtonColor),
        modifier = modifier.height(55.dp)
    ) {
        if (isProcessing) {
            CircularProgressIndicator(color = Color.White)
        }else{
            Text(text = buttonText, style = MaterialTheme.typography.titleMedium)

        }
    }

}