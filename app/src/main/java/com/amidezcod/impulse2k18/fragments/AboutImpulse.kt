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
class AboutImpulse : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_impulse, container, false)
        val textContentAbout = view.findViewById<TextView>(R.id.content_about)
        val textTitleAbout = view.findViewById<TextView>(R.id.title_about)
        val imageAbout = view.findViewById<ImageView>(R.id.about_image)
        val imageAboutDesc = view.findViewById<TextView>(R.id.about_image_desc)
        textTitleAbout.text = "Impulse 2K18"
        textContentAbout.text = "A rhythmic Inter- Collegiate State-Level technical fest IMPULSE 2k18 a very special occasion on 22 and 23rd March 2018, an annual state level technical fest from The oxford college  of Engineering, Bangalore.\n" +
                "\n" +
                "IMPULSE 2k18 intends to be a platform for students from all colleges across Karnataka to exhibit and explore their skills and creativity. " +
                "\n" +
                "Almost 20 events have been included in the Mega Technical fest which caters to almost 10 discipline." +
                "\n" +
                "IMPULSE 2k18 hosts a large number of events to cover a number of dimensions of creative technology such as Gaming , Kannada , Star of Impulse, Hot wheels , Oxford Roadies , Mock press , Un-KnowIt ,PaperPlane , Devil segue , Roboventure   and many others. In the previous edition of Impulse, there were 40 colleges with around 500 students who took part in the fest and showcased their talent.\n" +
                "\n" +
                "Impulse 2k18 will have a host of events and competitions exploring the various dimensions of technology, which brings out creativity and innovations among the students.\n" +

                imageAbout.setImageResource(R.drawable.impulse_logo)
        imageAboutDesc.visibility = View.GONE
        return view
    }
}