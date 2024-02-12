package com.peterchege.coopbanktest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.peterchege.coopbanktest.R

@Composable
fun CoopLogo(
    modifier: Modifier
){
    Image(
        painter = painterResource(id = R.drawable.coop_logo),
        contentDescription = "Cooperative Bank Logo",
        modifier
            .heightIn(max = 160.dp)
            .widthIn(max = 160.dp)
            .clip(RoundedCornerShape(0.dp))
    )
}