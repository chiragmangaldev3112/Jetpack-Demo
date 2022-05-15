package com.assignment.ui.common.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.assignment.theme.AppTheme
import textResource

@Composable
fun ErrorTextContent(id:Int){
    Text(textResource(id = id).toString(), style = AppTheme.typography.body1)
}