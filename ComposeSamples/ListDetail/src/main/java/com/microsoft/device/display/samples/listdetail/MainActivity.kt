/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.device.display.samples.listdetail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.window.DisplayFeature
import androidx.window.WindowManager
import com.microsoft.device.display.samples.listdetail.models.AppStateViewModel
import com.microsoft.device.display.samples.listdetail.ui.theme.ListDetailComposeSampleTheme
import com.microsoft.device.display.samples.listdetail.ui.view.SetupUI
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private lateinit var windowManager: WindowManager
    private lateinit var appStateViewModel: AppStateViewModel

    private val handler = Handler(Looper.getMainLooper())
    private val mainThreadExecutor = Executor { r: Runnable -> handler.post(r) }

    override fun onCreate(savedInstanceState: Bundle?) {
        windowManager = WindowManager(this)
        appStateViewModel = ViewModelProvider(this).get(AppStateViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            ListDetailComposeSampleTheme {
                SetupUI(appStateViewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        windowManager.registerLayoutChangeCallback(
            mainThreadExecutor,
            { windowLayoutInfo ->
                reserveScreenState(windowLayoutInfo.displayFeatures)
            }
        )
    }

    override fun onStop() {
        super.onStop()
        windowManager.unregisterLayoutChangeCallback {}
    }

    private fun reserveScreenState(displayFeatures: List<DisplayFeature>) {
        val isAppSpanned = displayFeatures.isNotEmpty()
        appStateViewModel.isAppSpannedLiveData.value = isAppSpanned
    }
}
