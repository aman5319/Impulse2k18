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
class AboutChildrenEducationSociety : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_impulse, container, false)
        val textContentAbout = view.findViewById<TextView>(R.id.content_about)
        val textTitleAbout = view.findViewById<TextView>(R.id.title_about)
        val imageAbout = view.findViewById<ImageView>(R.id.about_image)
        val imageAboutDesc = view.findViewById<TextView>(R.id.about_image_desc)
        textTitleAbout.text = "Children's\nEducation\nSociety "
        textContentAbout.text = "With an aim of providing a center for academic excellence and achievement,Children's Education Society (Regd.), was established in the year 1974, headed by the visionary educationist, Founder Chairman Vidyashree S. Narasa Raju. \n\n" +
                "In its glorious journey of over four decades, the society has established under its wings more than 32 Educational Institutes in Bengaluru, from Pre-Nursery to Post-Graduate , Doctoral courses including Medical, Dental, Engineering, Management, Nursing, Pharmacy, Physiotherapy, Education, Life Sciences and Law. \n\n" +
                "The Society also runs various free services for the benefit of the mankind such as free dental services, medical camps and workshops. It works with a mission to develop competencies of students with good value system to face challenges of the continuously changing world. The Oxford Group of Educational Institutions Established In the year 1974 under the aegis of Children's Education Society (Regd.) has proved a beckon to knowledge seeker not only all over the country but also all over the world. It made a humble beginning with 24 students and 2 teachers, but during the last four decades, The Oxford Group has seen an immense growth of establishing Medical, Engineering, Dental Institutions under the great guidance and leadership of Late. Sri S Narasa Raju and Sri S.N.V.L Narasimha Raju. \n"
        imageAbout.setImageResource(R.drawable.late)
        imageAboutDesc.text = "Late. Sri S Narasa Raju"
        return view
    }
}