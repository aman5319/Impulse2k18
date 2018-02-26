package com.amidezcod.impulse2k18.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import impulse2k18.R

/**
 * Created by amidezcod on 26/2/18.
 */
class AboutCollege : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_impulse, container, false)
        val textContentAbout = view.findViewById<TextView>(R.id.content_about)
        val textTitleAbout = view.findViewById<TextView>(R.id.title_about)
        val imageAbout = view.findViewById<ImageView>(R.id.about_image)
        val imageAboutDesc = view.findViewById<TextView>(R.id.about_image_desc)
        textTitleAbout.text = "Children\n Education\n Society"
        textContentAbout.text = "val view = inflater.inflate(R.layout.about_impulse, container, false)\n" +
                "        val textContentAbout = view.findViewById<TextView>(R.id.content_about)\n" +
                "        val textTitleAbout = view.findViewById<TextView>(R.id.title_about)\n" +
                "        val imageAbout = view.findViewById<ImageView>(R.id.about_image)\n" +
                "        val imageAboutDesc = view.findViewById<TextView>(R.id.about_image_desc)"
        imageAbout.setImageResource(R.drawable.coding)
        imageAboutDesc.text = "Coding Logo"
        return view
    }
}