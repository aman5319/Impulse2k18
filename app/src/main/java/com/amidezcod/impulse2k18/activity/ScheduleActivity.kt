package com.amidezcod.impulse2k18.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.amidezcod.impulse2k18.adapter.ScheduleRecyclerAdapter
import com.amidezcod.impulse2k18.database.MyDatabase
import com.amidezcod.impulse2k18.modal.EventDivider
import com.amidezcod.impulse2k18.modal.EventHeader
import com.amidezcod.impulse2k18.modal.EventItem
import impulse2k18.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {
    companion object {
        val eventHeaderDay1: EventHeader = EventHeader("22nd March 2018 (Day 1)",
                "Welcome to Impulse 2k18", "9am-5pm")
        val eventHeaderDay2: EventHeader = EventHeader("23rd March 2018 (Day 2)",
                "Welcome to Impulse 2k18", "9am-5pm")


        val eventItemListDay1: ArrayList<EventItem> = arrayListOf(
                EventItem("Impulse Inauguration", "6th floor seminar hall new Building", "9am-11am"),
                EventItem("Gaming(Mini Militia)", "N-313,314 Cse Dept.", "11am-4pm"),
                EventItem("Gaming(FIFA)", "Spdp lab Cse Dept.", "11am-4pm"),
                EventItem("Gaming(CS)", "Spdp lab Cse Dept.", "11am-4pm"),
                EventItem("Gaming(NFS)", "Spdp lab Cse Dept.", "11am-4pm"),
                EventItem("GearUp", "IV Floor ISE Seminar Hall New Building", "11am-4pm"),
                EventItem("WheelsOnFire", "Play Ground", "11am-4pm"),
                EventItem("TechnoVation", "3rd Floor seminar Hall", "11am-2pm"),
                EventItem("MockPress", "6th floor Seminar Hall", "1:30pm-4pm"),
                EventItem("Un-KnowIT", "6th floor Seminar Hall", "11am-1pm"),
                EventItem("PaperPlane", "2nd floor Drawing hall New Building", "1:30pm-4:00pm"),
                EventItem("Montage", "II Floor Drawing Hall", "11am-1pm"),
                EventItem("HotWheels", "Automobile Lab", "11am-3pm"),
                EventItem("DevilSegue", "PSS Lab 5th Floor new Building", "12pm-3pm"),
                EventItem("OxfordRoadies", "Old Building Ground Floor\n606 old building", "11am-1pm\n1:30pm-5pm"),
                EventItem("WebDesigning", "MCA Lab 6th floor new Building", "11pm-4pm", isLastItem = true))
        val eventItemListDay2: ArrayList<EventItem> = arrayListOf(
                EventItem("Gaming(FIFA)", "Spdp lab Cse Dept.", "9am-4pm"),
                EventItem("Gaming(CS)", "Spdp lab Cse Dept.", "9am-4pm"),
                EventItem("Gaming(NFS)", "Spdp lab Cse Dept.", "9am-4pm"),
                EventItem("StarOfImpulse(prelim)", "N-602 new Building", "11pm-1pm"),
                EventItem("StarOfImpulse", "6th floor seminar new Building", "1:30pm-4pm"),
                EventItem("ಅಭ್ಯುದಯ", "6th floor seminar new Building", "9:30am-1pm"),
                EventItem("PicturesQue", "IV Floor ISE Seminar Hall new Building", "9am-4pm"),
                EventItem("Techno-Opus", "7th floor Aryabatta hall new Building", "9am-1pm"),
                EventItem("RollCameraAction", "5th floor seminar hall new Building", "1:30pm-4pm"),
                EventItem("Construct", "II Floor Drawing Hall", "9am-1pm"),
                EventItem("Roboventure", "Footpath near Workshop", "1:30pm-4pm"),
                EventItem("TreasureHunt", "MCA Seminar Hall", "9am-1pm", isLastItem = true)

        )
        var roomDatabase: MyDatabase? = null
    }

    lateinit var schudleRecyclerAdapter: ScheduleRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        Toast.makeText(this@ScheduleActivity, "Carry your college Id card in the technical fest", Toast.LENGTH_LONG).show()
        roomDatabase = Room.databaseBuilder(this@ScheduleActivity,
                MyDatabase::class.java, "mydb").build()
        schudleRecyclerAdapter = ScheduleRecyclerAdapter(getRegisteredEventName())


        recycler_view_schedule.adapter = schudleRecyclerAdapter
        recycler_view_schedule.layoutManager = LinearLayoutManager(this)
        schudleRecyclerAdapter.addEventHeader(eventHeaderDay1)
        for (i in eventItemListDay1) {
            schudleRecyclerAdapter.addEventDivider(EventDivider(R.drawable.day1))
            schudleRecyclerAdapter.addEventItem(i)
        }

        schudleRecyclerAdapter.addEventHeader(eventHeaderDay2)

        for (i in eventItemListDay2) {
            schudleRecyclerAdapter.addEventDivider(EventDivider(R.drawable.day2))
            schudleRecyclerAdapter.addEventItem(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.schedule_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.profile -> {
                alertShow()
                return true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getRegisteredEventName(): MutableList<String> {
        val eventList: MutableList<String> = ArrayList()
        roomDatabase?.registrationDao()?.getAllReg()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { reg -> reg.map { x -> x.event }.forEach { eventList.add(it) } }
        return eventList
    }

    private fun alertShow() {
        val textView = TextView(this@ScheduleActivity)
        textView.setTextColor(ContextCompat.getColor(this@ScheduleActivity, android.R.color.black))
        textView.text = "Your Registrations"
        textView.textSize = 24f
        textView.setTextColor(ContextCompat.getColor(this@ScheduleActivity, R.color.goingEvent))
        textView.setPadding(32, 32, 8, 8)
        AlertDialog.Builder(this@ScheduleActivity)
                .setCustomTitle(textView)
                .setAdapter(ArrayAdapter(this@ScheduleActivity,
                        android.R.layout.select_dialog_item, getRegisteredEventName())) { _, _ ->
                }
                .show()
    }
}
