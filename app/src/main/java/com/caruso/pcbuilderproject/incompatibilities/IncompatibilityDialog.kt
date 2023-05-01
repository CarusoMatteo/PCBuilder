package com.caruso.pcbuilderproject.incompatibilities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.caruso.pcbuilderproject.R.string.ok_Text
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun IncompatibilityDialog(
    incompatibility: Incompatibility,
    incompatibilityDialogVisible: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = {
            incompatibilityDialogVisible.value = false
        },
        title = { Text(text = incompatibility.name) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                Text(text = incompatibility.description)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    incompatibilityDialogVisible.value = false
                }) {
                Text(text = stringResource(ok_Text))
            }
        },
    )


}

@Preview
@Composable
fun IncompatibilityDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        IncompatibilityDialog(
            incompatibility = IncompatibilityList.wrongSocket,
            incompatibilityDialogVisible = remember { mutableStateOf(true) }
        )
    }
}