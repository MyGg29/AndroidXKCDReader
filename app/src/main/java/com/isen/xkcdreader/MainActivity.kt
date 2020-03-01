package com.isen.xkcdreader

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shareButton.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            val uriToXKCD = Uri.parse("https://picsum.photos/200")
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToXKCD)
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareMessage))
            shareIntent.type = "image/jpeg"
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
        }
    }
}
