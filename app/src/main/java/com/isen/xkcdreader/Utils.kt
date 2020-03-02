package com.isen.xkcdreader

import android.content.Context
import android.graphics.Bitmap
import android.content.Context.MODE_PRIVATE
import android.content.ContextWrapper
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class Utils(context:Context) {
    private var myContext: Context

    init{
        this.myContext = context;
    }
    // Method to save an image to internal storage

}