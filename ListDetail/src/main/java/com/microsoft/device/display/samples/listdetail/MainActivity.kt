/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.device.display.samples.listdetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.device.display.samples.listdetail.ui.theme.ListDetailComposeSampleTheme
import com.microsoft.device.display.samples.listdetail.ui.view.ListDetailApp
import com.microsoft.device.dualscreen.windowstate.WindowState
import com.microsoft.device.dualscreen.windowstate.rememberWindowState

class MainActivity : AppCompatActivity() {
    private lateinit var windowState: WindowState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            windowState = rememberWindowState()

            ListDetailComposeSampleTheme {
                ListDetailApp(windowState)
            }
        }
    }
}
