package com.isen.xkcdreader

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.net.URL


class SplashActivity : Activity() {
    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH : Long = 3000

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.splash_layout)
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        val queue = Volley.newRequestQueue(this)
        val url = "https://xkcd.com/info.0.json"

        // Request a string response from the provided URL.
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,

            Response.Listener { response ->

                // Display the response and save it temporarily
                Log.d("Pulling data", "Response is: $response")

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },

            Response.ErrorListener {
                Log.e("Volley", "Error while waiting for response", it)
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonRequest)
    }
}