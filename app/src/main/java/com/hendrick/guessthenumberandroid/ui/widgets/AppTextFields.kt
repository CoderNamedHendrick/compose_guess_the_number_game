package com.hendrick.guessthenumberandroid.ui.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hendrick.guessthenumberandroid.ui.theme.GuessTheNumberAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    suffixIcon: @Composable (() -> Unit)? = null,
    prefixIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    ignoredRegex: Regex? = null,
) {
    val enabled = true
    val isError = error != null
    val singleLine = true

    val textStyle = MaterialTheme.typography.bodyMedium
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val visualTransformation = VisualTransformation.None

    Column(modifier = modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title, style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W400, color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = modifier.height(6.dp))
        }


        BasicTextField(
            value = value,
            onValueChange = {
                if (ignoredRegex != null) {
                    if (!it.text.contains(ignoredRegex)) onValueChanged(it)
                    return@BasicTextField
                }

                onValueChanged(it)
            },
            modifier = modifier.fillMaxWidth(),
            visualTransformation = visualTransformation,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
        ) { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value.text,
                innerTextField = innerTextField,
                leadingIcon = prefixIcon,
                trailingIcon = suffixIcon,
                placeholder = placeholder,
                enabled = enabled,
                singleLine = true,
                isError = isError,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
            )
        } // BasicTextField

        if (error != null) {
            Column {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = error,
                    modifier = modifier,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.error,
                        lineHeight = 21.sp,
                        textAlign = TextAlign.Left,
                    )
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTextFieldPreview() {
    GuessTheNumberAndroidTheme {
        AppOutlinedTextField(title = "Sound", value = TextFieldValue("58395"), onValueChanged = {})
    }
}
