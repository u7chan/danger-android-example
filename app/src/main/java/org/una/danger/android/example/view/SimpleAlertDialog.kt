package org.una.danger.android.example.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDialog(
    title: String,
    show: Boolean,
    onClickOk: () -> Unit,
    onClickCancel: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Confirm")
            },
            text = {
                Text(text = title)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickOk()
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onClickCancel()
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}
