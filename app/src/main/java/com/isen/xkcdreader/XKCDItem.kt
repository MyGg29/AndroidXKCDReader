package com.isen.xkcdreader

import android.graphics.Bitmap
import java.net.URL

data class XKCDItem(
    val id: Int,
    val url: URL,
    val title: String,
    val alttext: String,
    val img: Bitmap)