package com.isen.xkcdreader


import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import java.net.URL
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private val xkcds : ArrayList<XKCDItem> = arrayListOf()
    private lateinit var pagerAdapter: XKCDFragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: check if savedInstanceState has saved state and if so recover it

        // TODO: replace this with network fetch
        // Probably only a few of XKCDs around the current one should be fetched
        // Dummy XKCDs for test purposes
        xkcds.add(
            XKCDItem(
                1179,
                URL("https://xkcd.com/1179/"),
                "ISO 8601",
                "ISO 8601 was published on 06/05/88 and most recently amended on 12/01/04.",
                BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder)
            )
        )
        xkcds.add(
            XKCDItem(
                149,
                URL("https://xkcd.com/149/"),
                "Sandwich",
                "Proper User Policy apparently means Simon Says.",
                BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.placeholder)
            )
        )

        // END OF DUMMY DATA

        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = XKCDFragmentStatePagerAdapter(supportFragmentManager, xkcds)
        viewPager.adapter = pagerAdapter

        // TODO: check if this can be improved, or if this should only be done when savedInstanceState == null
        viewPager.currentItem = 0
        shareButton.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            //val uriToXKCD = Uri.parse("https://imgs.xkcd.com/comics/stargazing_3.png")
            val uriToXKCD = Uri.parse("android.resource://com.isen.xkcdreader/" + R.drawable.placeholder)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToXKCD)
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareMessage))
            shareIntent.type = "image/png"
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
        }

        downloadButton.setOnClickListener{
            saveImageToInternalStorage(R.drawable.placeholder, "title")
        }
    }
    fun saveImageToInternalStorage(drawableId:Int, title: String): Uri {
        // Get the image from drawable resource as drawable object
        val drawable = ResourcesCompat.getDrawable(this.getResources(),drawableId, null)

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Save image to gallery
        val savedImageURL = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            title,
            "Image of $title"
        )
        Log.d("test",savedImageURL)
        // Parse the gallery image url to uri
        return Uri.parse(savedImageURL)
    }
}
