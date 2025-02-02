/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.device.display.samples.composegallery.ui.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.device.display.samples.composegallery.R
import com.microsoft.device.display.samples.composegallery.models.DataProvider
import com.microsoft.device.display.samples.composegallery.models.ImageModel
import com.microsoft.device.dualscreen.twopanelayout.TwoPaneLayout
import com.microsoft.device.dualscreen.twopanelayout.TwoPaneMode
import com.microsoft.device.dualscreen.windowstate.WindowState

@Composable
fun ComposeGalleryApp(windowState: WindowState) {
    // Check if app should be in dual mode
    val isDualMode = windowState.isDualPortrait()

    // Get relevant image data for the panes
    val models = DataProvider.imageModels

    // Remember app state variables
    var selectedImageIndex by rememberSaveable { mutableStateOf(0) }
    val updateImageIndex: (Int) -> Unit = { index -> selectedImageIndex = index }

    ComposeGalleryAppContent(models, isDualMode, selectedImageIndex, updateImageIndex)
}

@Composable
fun ComposeGalleryAppContent(
    models: List<ImageModel>,
    isDualMode: Boolean,
    selectedImageIndex: Int,
    updateImageIndex: (Int) -> Unit,
) {
    TwoPaneLayout(
        paneMode = TwoPaneMode.HorizontalSingle,
        pane1 = { ListPane(models, isDualMode, selectedImageIndex, updateImageIndex) },
        pane2 = { DetailPane(models, isDualMode, selectedImageIndex) }
    )
}

@Composable
fun ComposeGalleryTopAppBar(actions: @Composable () -> Unit, title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp,
        actions = { actions() },
        modifier = Modifier.testTag(stringResource(R.string.top_app_bar))
    )
}
