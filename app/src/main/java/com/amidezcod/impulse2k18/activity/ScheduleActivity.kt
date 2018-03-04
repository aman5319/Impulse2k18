package com.amidezcod.impulse2k18.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.amidezcod.impulse2k18.database.EventOnly
import com.amidezcod.impulse2k18.database.MyDatabase
import impulse2k18.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScheduleActivity : AppCompatActivity() {
    companion object {
        var roomDatabase: MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        roomDatabase = Room.databaseBuilder(this@ScheduleActivity,
                MyDatabase::class.java,
                "mydb").build()


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
