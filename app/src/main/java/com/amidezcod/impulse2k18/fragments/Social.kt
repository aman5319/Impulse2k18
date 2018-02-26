package com.amidezcod.impulse2k18.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import impulse2k18.R

/**
 * Created by amidezcod on 26/2/18.
 */
class Social : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_social, container, false)
        val rating = view.findViewById<LinearLayout>(R.id.play_store)
        val facebook = view.findViewById<LinearLayout>(R.id.facebook)
        val insta = view.findViewById<LinearLayout>(R.id.instagram)
        val twitter = view.findViewById<LinearLayout>(R.id.twitter)
        val facebookUrl = "https://www.facebook.com/toce.impulse"
        val instaUrl = "https://www.instagram.com/impulse2018/"
        val twitterUrl = "https://twitter.com/Impulse2k18"
        facebook.setOnClickListener({ openUrl(facebookUrl) })
        insta.setOnClickListener({ openUrl(instaUrl) })
        twitter.setOnClickListener({ openUrl(twitterUrl) })
        return view
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        val chooser = Intent.createChooser(intent, "Connect with us")
        if (intent.resolveActivity((context?.packageManager)) != null) {
            startActivity(chooser)
        }
    }
}