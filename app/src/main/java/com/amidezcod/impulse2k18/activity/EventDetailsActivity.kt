package com.amidezcod.impulse2k18.activity

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
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

        prize.text = parcel.prize
        venue_detail.text = parcel.venue
        cord1.text = parcel.coordinator[0]
        cord2.text = parcel.coordinator[1]

        colorNumberAndUnderLine(cord1)
        colorNumberAndUnderLine(cord2)

        cord1.setOnClickListener({ dialPhone(getNumber(cord1.text)) })
        cord2.setOnClickListener({ dialPhone(getNumber(cord2.text)) })

        image_detail.setImageResource(imageId)
        collapse.title = text

        if (text != "#Gaming") {
            registration_price.text = "\u20B9" + parcel.registrationFee
            register_now.setOnClickListener({
                val intent = Intent(this@EventDetailsActivity, RegisterActivity::class.java)
                intent.putExtra("EventName", text)
                startActivity(intent)
            })
        } else {
            registration_price.text = parcel.registrationFee

            val values = arrayOf<CharSequence>(" NFS ", " FIFA ", " CS ", "Mini Militia")
            var games = ""
            register_now.setOnClickListener({
                AlertDialog.Builder(this@EventDetailsActivity)
                        .setTitle("Select your Game")
                        .setSingleChoiceItems(values, -1, { _, which ->
                            when (which) {
                                0 -> {
                                    games = "NFS"
                                }
                                1 -> {
                                    games = "FIFA"
                                }
                                2 -> {
                                    games = "CS"
                                }
                                3 -> {
                                    games = "Mini Militia"
                                }
                            }

                        }).setPositiveButton("Ok", { _, _ ->
                            if (games.isNotEmpty()) {
                                val intent = Intent(this@EventDetailsActivity, RegisterActivity::class.java)
                                intent.putExtra("EventName", "$text($games)")
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@EventDetailsActivity, "First select any game to register", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .setNegativeButton("Cancel", { dialog, _ ->
                            games = ""
                            dialog.dismiss()
                        })
                        .setCancelable(false)
                        .show()

            })

        }

    }

    private fun colorNumberAndUnderLine(cord1: TextView) {
        cord1.paintFlags = cord1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        cord1.setTextColor(ContextCompat.getColor(this@EventDetailsActivity, R.color.colorPrimaryDark))
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
