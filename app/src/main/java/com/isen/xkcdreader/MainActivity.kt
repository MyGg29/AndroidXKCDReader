package com.isen.xkcdreader


import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import java.net.URL
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private val xkcds : ArrayList<XKCDItem> = arrayListOf()
    private lateinit var pagerAdapter: XKCDFragmentStatePagerAdapter
    private val latestXKCDIndex : Int = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: check if savedInstanceState has saved state and if so recover it

        // TODO: replace this with network fetch
        // Probably only a few of XKCDs around the current one should be fetched

        // TODO: make a better code
        // This is completely asynchronous, I don't know how it will act if the activity is
        // half loaded or if the connection is down
        val queue = Volley.newRequestQueue(this)
        val url = "https://xkcd.com/info.0.json"

        // Request a string response from the provided URL.
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null,

            Response.Listener { response ->

                // Display the response and save it temporarily
                Log.d("Pulling data", "Response is: $response")
                val tempXkcd : XKCDItem = XKCDItem(
                    response.getInt("num"),
                    URL("https://xkcd.com/${response.getString("link")}"),
                    response.getString("safe_title"),
                    response.getString("alt"),
                    BitmapFactory.decodeResource(this.resources,
                        R.drawable.placeholder)
                )

                // Create a request to get the images
                val imageRequest = ImageRequest(response.getString("img"),

                    Response.Listener { response_img ->
                        // Here we finally add the final XKCD with the proper image
                        xkcds.add(tempXkcd.copy(img = response_img))
                        pagerAdapter.notifyDataSetChanged()
                    },
                    1920, 1080, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565,

                    Response.ErrorListener {
                        // If we can't get the image, we'll just add the temporary xkcd with the
                        // placeholder image
                        xkcds.add(tempXkcd)
                        pagerAdapter.notifyDataSetChanged()
                    }
                )

                queue.add(imageRequest)
            },

            Response.ErrorListener { }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonRequest)

        // Dummy XKCDs for test purposes
        for (index in 0..latestXKCDIndex) {
            xkcds.add(
                XKCDItem(
                    1179,
                    URL("https://xkcd.com/${index + 1}/"),
                    "This is the XKCD n°${index + 1}",
                    "This is the alt text for the XKCD n°${index + 1}.",
                    BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.placeholder)
                )
            )
        }

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

        randomButton.setOnClickListener { switchToRandomXKCD() }
    }

    private fun switchToRandomXKCD() {
        val randomInt : Int = (0..latestXKCDIndex).random()
        viewPager.currentItem = randomInt
    }
}
