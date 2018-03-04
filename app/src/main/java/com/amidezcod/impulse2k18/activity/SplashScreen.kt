package com.amidezcod.impulse2k18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val w = window
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        video_view.setVideoPath("android.resource://" + packageName + "/" + R.raw.logo2)
        video_view.start()
        video_view.setOnCompletionListener({
            startActivity(Intent(this@SplashScreen, BubblePickerActivity::class.java))
        })
    }
}
