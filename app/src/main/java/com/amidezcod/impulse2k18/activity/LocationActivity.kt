package com.amidezcod.impulse2k18.activity

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        location_text.paintFlags = Paint.UNDERLINE_TEXT_FLAG or location_text.paintFlags
        location_text.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark))
        location_text.setOnClickListener({ intentToMaps() })
    }

    fun intentToMaps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:12.9014963,77.6290913?z=17")
        val chooserIntent = Intent.createChooser(intent, "Your Destination")
        if (intent.resolveActivity(packageManager) != null)
            startActivity(chooserIntent)

    }
}
