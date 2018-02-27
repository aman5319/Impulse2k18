package com.amidezcod.impulse2k18.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.amidezcod.impulse2k18.adapter.CardPagerAdapter
import com.amidezcod.impulse2k18.modal.EventDetailsInfo
import impulse2k18.R

import kotlinx.android.synthetic.main.activity_event_detail.*


class EventDetailsActivity : AppCompatActivity() {
    var register_url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val toolbar = findViewById<Toolbar>(R.id.event_detail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val imageId = intent.getIntExtra(CardPagerAdapter.EVENT_IMAGE_INTENT, 0)
        val text = intent.getStringExtra(CardPagerAdapter.EVENT_TEXT_INTENT)
        val parcel: EventDetailsInfo = intent.getParcelableExtra<EventDetailsInfo>(CardPagerAdapter.EVENT_DETAILS_INTENT)
        description_text.text = parcel.description
        rules_text.text = parcel.rules
        registration_price.text = "\u20B9" + parcel.registrationFee
        prize.text = parcel.prize
        venue_detail.text = parcel.venue
        cord1.text = parcel.coordinator[0]
        cord2.text = parcel.coordinator[1]
        cord1.setOnClickListener({ dialPhone(getNumber(cord1.text)) })
        cord2.setOnClickListener({ dialPhone(getNumber(cord2.text)) })

        register_url = parcel.url
        image_detail.setImageResource(imageId)
        collapse.title = text
        register_now.setOnClickListener({
            sendUrlIntent()
        })

    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun sendUrlIntent() {
        val intent = Intent(this@EventDetailsActivity, RegisterActivity::class.java)
        intent.data = Uri.parse(register_url)
        if (isNetworkAvailable()) {
            startActivity(intent)
        } else {
            Snackbar.make(coa, "No Internet Connection", Snackbar.LENGTH_SHORT)
                    .setAction("Retry", { sendUrlIntent() }).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun dialPhone(phoneNo: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + phoneNo)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun getNumber(chharSequence: CharSequence): String {
        return "+" + chharSequence.toString()
                .substringAfter("+", "323")
    }
}
