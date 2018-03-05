package com.amidezcod.impulse2k18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.amidezcod.impulse2k18.adapter.RulesAdapter
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_rules.*

class RulesActivity : AppCompatActivity() {
    lateinit var parentRules: ArrayList<String>
    lateinit var childrules: HashMap<String, ArrayList<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        childrules = HashMap()
        parentRules = ArrayList()
        fillData()
        expandable_list_views.setAdapter(RulesAdapter(this@RulesActivity, childrules, parentRules))
    }

    private fun fillData() {
        parentRules.add("Code of Conduct")
        parentRules.add("Code for Participants")
        parentRules.add("Fest Leads")
        parentRules.add("License")

        val list0: ArrayList<String> = ArrayList()
        list0.add("We value the presence of every attendee and want everyone to have an enjoyable and fulfilling experience.\n")
        list0.add("All attendees are therefore expected to show respect and courtesy to other attendees throughout the fest and its various events.\n")
        list0.add("All attendees at Impulse-18 are required to confirm to the following Code of Conduct. \n")
        list0.add("Organizers will ensure this code throughout the fest.\n")
        list0.add("We strive to create a safe and open environment so that every participant can participate confidently and feel enriched at the end of the fest.\n")
        list0.add("We strive to create a safe and open environment so that every participant can participate confidently and feel enriched at the end of the fest.\n")
        list0.add("This code of conduct is to create a safe environment so that everyone can voice their perspective freely.\n")
        childrules[parentRules[0]] = list0

        val list1: ArrayList<String> = ArrayList()
        list1.add("1. Participants are required to refrain from harassing behavior towards organizers and other participants, sponsors, media personnel, volunteers, and  staff Harassment includes:\n")
        list1.add("\t a. Offensive verbal comments related to gender, sexual orientation, disability, physical appearance, body size, race and religion.\n")
        list1.add("\t b. Sexual images in public spaces deliberate intimidation, stalking, photography recording without explicit consent, sustained disruption of talks or other events.\n")
        list1.add("\t c. Inappropriate physical contact, unwelcome sexual attention.\n")
        list1.add("2. Attendees asked to stop any harassing behavior are expected to comply immediately.\n")
        list1.add("3. Participants must take care of their belongings and components as organizers are not responsible for any of their things.\n")
        list1.add("4. Smoking and consumption of alcohol is strictly prohibited during the course of the fest.\n")
        list1.add("5. ID card of your respective college is compulsory.\n")
        list1.add("6. The judges and organizers words are the final judgment and no sort of arguments will be entertained.\n")
        list1.add("7. If an attendee engages in behavior that violates this code of conduct, the fest organizers may take any action they deem appropriate, including warning the offender or expelling them from the premises with no refund.\n")
        list1.add("8. We hope that you will help make Impulse-18 a welcoming, friendly event for all.\n")
        childrules[parentRules[1]] = list1

        val list2: ArrayList<String> = ArrayList()
        list2.add("If you would like to report another attendee, sponsor, or volunteer, please approach one of the TOCE organizers or TOCE staff and file your complaint.\n")
        list2.add("If the matter is especially urgent, please call/contact any of these individuals Crew members are there to help participants contact the organizing team and assist those experiencing harassment/any other problem to feel safe for the duration of the fest.\n")
        childrules[parentRules[2]] = list2

        val list3: ArrayList<String> = ArrayList()
        list3.add("This Code of Conduct was forked from The Fifth Elephant-2016 Code of Conduct which in turn\n" +
                "was forked from the PyCon US Code of Conduct, which in turn was forked from the Geek\n" +
                "Feminism wiki, created by the Ada Initiative and other volunteers and available under a Creative\n" +
                "Commons Zero license.")
        childrules[parentRules[3]] = list3

    }
}



