package com.amidezcod.impulse2k18.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.amidezcod.impulse2k18.database.MyDatabase
import com.amidezcod.impulse2k18.modal.RegisterModal
import com.amidezcod.impulse2k18.modal.RegistrationEntity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import impulse2k18.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    companion object {
        var roomDatabase: MyDatabase? = null
    }

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Registration"

        val eventName = intent.getStringExtra("EventName").substringAfter("#", "ಅಭ್ಯುದಯ")
        register_event.text = eventName
        if (FirebaseDatabase.getInstance() == null)
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        databaseReference = FirebaseDatabase.getInstance().getReference("register")
        roomDatabase = Room.databaseBuilder(this@RegisterActivity, MyDatabase::class.java, "mydb").build()
        register_button.setOnClickListener({
            if (checkEditext()) {
                enterIntoFirebase(eventName)
            } else {
                Toast.makeText(this@RegisterActivity, "Please enter all fields", Toast.LENGTH_LONG).show()
            }
        })

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun checkEditext(): Boolean =
            editTextToString(register_name).isNotEmpty()
                    && editTextToString(register_email).isNotEmpty()
                    && editTextToString(register_college).isNotEmpty()
                    && editTextToString(register_phone).isNotEmpty()

    private fun enterIntoFirebase(event_name: String) {
        if (isValidEmail(editTextToString(register_email))) {
            if (isValidPhone(editTextToString(register_phone))) {
                if (isNetworkAvailable()) {

                    databaseReference.push()
                            .setValue(RegisterModal(
                                    editTextToString(register_name),
                                    editTextToString(register_email),
                                    editTextToString(register_phone),
                                    editTextToString(register_college),
                                    event_name))
                    addReg(event_name)
                    showNotification(editTextToString(register_name), event_name)
                    addCalendarEvent()
                } else {
                    Snackbar.make(linear_layout_snackbar, "No Internet Connectivity", Snackbar.LENGTH_LONG)
                            .setAction("Retry", {
                                enterIntoFirebase(event_name)
                            }).show()
                }

            } else {
                toast("Enter a valid phone no of 10 Digits")
            }
        } else {
            toast("Enter a valid email")
        }

    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun editTextToString(editText: EditText): String = editText.text.toString()
    private fun isValidEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun toast(message: String) = Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
    private fun isValidPhone(phone: String): Boolean = phone.length == 10

    fun addReg(event: String) {
        val registerModal = RegistrationEntity(uid = 0L, event = event)
        Single.fromCallable {
            roomDatabase?.registrationDao()?.insert(registerModal)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

    fun showNotification(fullname: String, event: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                    "com.amidezcod.impulse",
                    "Impulse 2k18",
                    "\"$fullname your seat has been booked for $event see you at T.O.C.E\"")

        }
        val intent = Intent(this@RegisterActivity, ScheduleActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val notification: NotificationCompat.Builder =
                NotificationCompat.Builder(this@RegisterActivity, "com.amidezcod.impulse")
                        .setSmallIcon(R.drawable.ic_pen)
                        .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.impulse_logo))
                        .setContentTitle("Impulse 2k18")
                        .setContentText("Successful")
                        .setStyle(NotificationCompat.BigTextStyle()
                                .setBigContentTitle("Thank you")
                                .bigText("$fullname you have been registered for $event \n see you at T.O.C.E"))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setVibrate(longArrayOf(1000, 1000))
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        .setContentIntent(PendingIntent.getActivity(this@RegisterActivity, 0, intent, PendingIntent.FLAG_ONE_SHOT))
        NotificationManagerCompat.from(this@RegisterActivity)
                .notify(123, notification.build())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager.createNotificationChannel(channel)
    }

    fun addCalendarEvent() {
        val intent = Intent(Intent.ACTION_INSERT)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "Impulse 2k18 event")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The Oxford college of engineering")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1521689400000)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1521804600000)
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
