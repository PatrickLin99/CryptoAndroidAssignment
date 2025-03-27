package com.example.cryptoandroidtest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onGoToShowCaseClickListener: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(), content = { paddingValues ->
        DescriptionItem(modifier = Modifier.padding(paddingValues), onGoToShowCaseClickListener)
    })
}

@Composable
fun DescriptionItem(modifier: Modifier, onGoToShowCaseClickListener: () -> Unit ) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(36.dp)
        .clickable {
            onGoToShowCaseClickListener.invoke()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(com.example.resource.R.drawable.ic_click),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(com.example.resource.R.string.MainScreen_Description),
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(com.example.resource.R.string.MainScreen_Description_Assignment),
            fontSize = 16.sp,
            color = Color.LightGray
        )
    }
}

@Preview
@Composable
fun PreviewDescriptionItem() {
    DescriptionItem(Modifier) {}
}