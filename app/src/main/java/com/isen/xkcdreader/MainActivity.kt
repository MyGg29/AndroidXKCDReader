package com.isen.xkcdreader

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import java.net.URL

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
    }
}
