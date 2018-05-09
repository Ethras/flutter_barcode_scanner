/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ethras.barcodescan.barcode

import android.util.Log

import com.ethras.barcodescan.ui.GraphicOverlay
import com.google.android.gms.vision.MultiProcessor
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.barcode.Barcode


class BarcodeTrackerFactory(private val graphicOverlay: GraphicOverlay<BarcodeGraphic>,
                            private val barcodeUpdateListener: BarcodeUpdateListener, private val showText: Boolean) : MultiProcessor.Factory<Barcode> {

    override fun create(barcode: Barcode): Tracker<Barcode>? {
        val graphic = BarcodeGraphic(graphicOverlay, showText)
        try {
            return BarcodeGraphicTracker(graphicOverlay, graphic, barcodeUpdateListener)
        } catch (ex: Exception) {
            Log.d("BarcodeTrackerFactory", ex.message, ex)
        }

        return null
    }

}
