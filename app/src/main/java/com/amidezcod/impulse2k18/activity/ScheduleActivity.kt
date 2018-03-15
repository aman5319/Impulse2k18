package com.amidezcod.impulse2k18.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.amidezcod.impulse2k18.adapter.ScheduleRecyclerAdapter
import com.amidezcod.impulse2k18.database.EventOnly
import com.amidezcod.impulse2k18.database.MyDatabase
import com.amidezcod.impulse2k18.modal.EventDivider
import com.amidezcod.impulse2k18.modal.EventHeader
import com.amidezcod.impulse2k18.modal.EventItem
import impulse2k18.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_developer.*
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {
    companion object {
        val eventHeader: EventHeader = EventHeader("Impulse 2k18",
                "Impulse 2k18 Inaugaration", "9:00 am")

        val eventDividers: ArrayList<EventDivider> = arrayListOf(
                EventDivider("", ""),
                EventDivider("", ""),
                EventDivider("Next day", "star of impulse"),
                EventDivider("Next 2 days from now", "picturesques"),
                EventDivider("Next 3 days from now", "robo"),
                EventDivider("Next week", "array"),
                EventDivider("Next 2 days from now", "picturesques"),
                EventDivider("Next 3 days from now", "robo"),
                EventDivider("Next week", "array"),
                EventDivider("Next week", "array"),
                EventDivider("Next 2 days from now", "picturesques"),
                EventDivider("Next 3 days from now", "robo"),
                EventDivider("Next week", "array"),
                EventDivider("Next 2 days from now", "picturesques"),
                EventDivider("Next 3 days from now", "robo")
        )

        val eventItemList: ArrayList<EventItem> = arrayListOf(
                EventItem("Gaming", "spdp lab cse 3rd floor", "9 am"),
                EventItem("kanadinga", "6th floor seminar hall", "1pm"),
                EventItem("starof impulse", "6th floor seminar hall", "18.5f"),
                EventItem("23rd", "", "18f"),
                EventItem("23rd", "Sunny", "21.5f"),
                EventItem("23rd", "Windy", "19.7", isLastItem = true),
                EventItem("23rd", "", "18f"),
                EventItem("23rd", "Sunny", "21.5f"),
                EventItem("23rd", "Windy", "19.7f", isLastItem = true),
                EventItem("22nd", "coding", "24f"),
                EventItem("22nd", "gaming", "22.2f"),
                EventItem("22nd", "webdesinging", "18.5f"),
                EventItem("23rd", "", "18f"),
                EventItem("23rd", "", "18f"),
                EventItem("23rd", "Sunny", "21.5f"))
        var roomDatabase: MyDatabase? = null
    }

    lateinit var schudleRecyclerAdapter: ScheduleRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        Toast.makeText(this@ScheduleActivity, "Carry your college Id card in the technical fest", Toast.LENGTH_LONG).show()
        roomDatabase = Room.databaseBuilder(this@ScheduleActivity,
                MyDatabase::class.java,
                "mydb").build()
        schudleRecyclerAdapter = ScheduleRecyclerAdapter()
        recycler_view_schedule.adapter = schudleRecyclerAdapter
        recycler_view_schedule.layoutManager = LinearLayoutManager(this)
        schudleRecyclerAdapter.addEventHeader(eventHeader)
        for (i in 0..14) {
            schudleRecyclerAdapter.addEventDivider(eventDividers[i])
            schudleRecyclerAdapter.addEventItem(eventItemList[i])
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.schedule_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.profile -> {
                showEventsRegistered()
                return true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showEventsRegistered() {
        roomDatabase?.registrationDao()?.getAllReg()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { reg -> alertShow(reg) }
    }

    private fun alertShow(list: MutableList<EventOnly>) {

        AlertDialog.Builder(this@ScheduleActivity)
                .setTitle("Your registrations")
                .setAdapter(ArrayAdapter(this@ScheduleActivity,
                        android.R.layout.select_dialog_item, list.map { x -> x.event })) { dialog, which ->
                }
                .show()
    }
}
