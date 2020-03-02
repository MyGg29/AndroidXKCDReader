package com.isen.xkcdreader


import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import java.net.URL
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

        val queue = Volley.newRequestQueue(this)
        val url = "https://xkcd.com/info.0.json"

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                Log.d("Pulling data", "Response is: $response")
            },
            Response.ErrorListener {
                Log.d("Pulling data","That didn't work!")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

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
    }
}
