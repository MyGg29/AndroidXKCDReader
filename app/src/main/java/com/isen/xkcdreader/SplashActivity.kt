package com.isen.xkcdreader

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler


class SplashActivity : Activity() {
    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH : Long = 3000

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.splash_layout)
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler()
            .postDelayed({
                // This method will be executed once the timer is over
                // Start your app main activity

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, SPLASH_DISPLAY_LENGTH)
    }
}