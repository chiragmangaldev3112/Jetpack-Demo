package com.assignment.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.assignment.theme.AppTheme
import com.assignment.theme.whiteColor


@Composable
fun LoadingPlaceholder(

) {
    Dialog(
        onDismissRequest = {

        },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(AppTheme.dimens.size_1_5)
                )
        ) {
            CircularProgressIndicator(color = whiteColor)
        }
    }
}