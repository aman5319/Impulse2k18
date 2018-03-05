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
        textTitleAbout.text = "The Oxford \nCollege Of\n Engineering"
        textContentAbout.text = "The Oxford Group of Institutions, which was started with a mission to empower the students in becoming excellent human re-sources and to contribute meaningfully to the human society. \n\n" +
                "Wide range of educational programs are being offered at the Oxford group to keep pace with the latest developments and Innovations in associated disciplines.\n\n" +
                " Our Teaching-learning process is based on motivation and discipline and special emphasis is laid on the extra-curricular activities like sports, cultural programs, workshops, symposiams and seminars for the all-round development of the students.\n\n" +
                " In order to create a breed of qualified, innovative and dynamic professionals for corporate sectors, service-Industries, aca-demics, research Institutions and self-employment, both technical and soft skills training's are provided to the students.\n\n " +
                "The tie-ups with various national and international universities and organizations also contribute in widening the horizon for the students in selecting their career options. \n\n" +
                "The Oxford College of Engineering is one of the most prestigious institutions in Bangalore that provides quality training in professional education in various streams of engineering, post graduate programme in Computer Application (M.) and in Business Administration (MBA). The Oxford College of Engineering is approved by AICTE, Accredited by NBA 8, Affiliated to VTU Belgaum"
        imageAbout.setImageResource(R.drawable.vice)
        imageAboutDesc.text = "Sri S.N.V.L.Narasimha Raju"
        return view
    }
}