package com.example.authdemo.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authdemo.presentation.ui.theme.PrimaryBorderColor

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    placeHolder : String,
    isError : Boolean=false,
    errorText : String = ""
) {

    Column(modifier = modifier) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            placeholder = {Text(placeHolder)},
            singleLine = true,
            isError = isError,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
                .border(
                width = 1.dp,
                color = if (isError) MaterialTheme.colorScheme.error else PrimaryBorderColor,
                shape = RoundedCornerShape(12.dp)
            ).clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        if (isError){
            Text(text = errorText, modifier = Modifier, color = MaterialTheme.colorScheme.error)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(
        modifier = Modifier,
        value = "John",
        onValueChange = {},
        text = "First name",
        placeHolder = ""
    )
}