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
        val gmail = view.findViewById<LinearLayout>(R.id.gmail)
        val youtube = view.findViewById<LinearLayout>(R.id.youtube)
        val youtube_id = "https://www.youtube.com/watch?v=WB1WpNEZUh8"
        val facebookUrl = "https://www.facebook.com/toce.impulse"
        val instaUrl = "https://www.instagram.com/impulse2018/"
        val twitterUrl = "https://www.twitter.com/Impulse2k18"
        val ratingUrl = "https://play.google.com/store/apps/details?id=com.amidezcod.impulse2k18&hl=en"
        facebook.setOnClickListener({ openUrl(facebookUrl) })
        rating.setOnClickListener({ openUrl(ratingUrl) })
        insta.setOnClickListener({ openUrl(instaUrl) })
        twitter.setOnClickListener({ openUrl(twitterUrl) })
        gmail.setOnClickListener({ composeEmail(arrayOf("toce.impulse@gmail.com")) })
        youtube.setOnClickListener({ openUrl(youtube_id) })
        view.findViewById<LinearLayout>(R.id.web_url)
                .setOnClickListener({ openUrl("https://www.impulse2k18.in") })
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

    fun composeEmail(address: Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tell us your issues or send us a feedback " +
                "we appreciate all your effort to make us better \n #IMPULSE'18")
        if (intent.resolveActivity(context?.packageManager) != null) {
            startActivity(intent)
        }
    }
}

