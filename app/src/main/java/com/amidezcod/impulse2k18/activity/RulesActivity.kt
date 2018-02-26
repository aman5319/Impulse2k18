package com.amidezcod.impulse2k18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_rules.*
import java.util.*

class RulesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        val rules: MutableList<String> = ArrayList()
        rules.add("1. Impulse 2k18 will be held in The Oxford College of Engineering campus.")
        rules.add("2. Online Registrations can be done at www.impulse2k18.tk or in the Impulse 2k18 Android app")
        rules.add("3. Last date for online registration is 21st March 2018.")
        rules.add("4. Spot registrations will be open on the event days from 09:00 IST.")
        rules.add("5. Any number of participants can register from same college.")
        rules.add("6. Participants are allowed to take part in any number of events provided the event's schedule does not clash.")
        rules.add("7. ID card is compulsory for registration.")
        rules.add("8. All the events will start on time. Participants arriving late to the venue will be disqualified.")
        rules.add("9. The Impulse Team reserves the right to disqualify any participant for unmoral, unethical or vulgar behavior.")

        list_views.adapter = ArrayAdapter<String>(this@RulesActivity,

                android.R.layout.simple_list_item_1, rules)
        code_button.setOnClickListener({
            startActivity(Intent(this, CodeOfConductActivity::class.java))
        })
    }
}



