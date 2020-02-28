package com.isen.xkcdreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private val xkcds : ArrayList<XKCDItem> = arrayListOf()
    private lateinit var pagerAdapter: XKCDFragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        pagerAdapter = XKCDFragmentStatePagerAdapter(supportFragmentManager, xkcds)

        viewPager.adapter = pagerAdapter
    }
}
