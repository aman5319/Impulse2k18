package com.amidezcod.impulse2k18.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_bubble_picker.*
import android.view.animation.Animation
import android.view.animation.AlphaAnimation




class BubblePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble_picker)
        val titles = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)
        val images = resources.obtainTypedArray(R.array.images)
        val w = window
        val anim = AlphaAnimation(1.0f, 0.3f)

        anim.duration = 750 //You can manage the time of the blink with this parameter
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        blink_text.startAnimation(anim)
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        picker.adapter = object : BubblePickerAdapter {

            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
//                    color = colors.getColor(position, ContextCompat.getColor(this@BubblePickerActivity, R.color.colorAccent))
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.HORIZONTAL)
                    typeface = ResourcesCompat.getFont(applicationContext, R.font.oxygen_regular)!!
                    textColor = ContextCompat.getColor(this@BubblePickerActivity, android.R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@BubblePickerActivity, images.getResourceId(position, 0))
                }
            }
        }



        colors.recycle()
        images.recycle()
        picker.bubbleSize = 30
        picker.centerImmediately = true
        picker.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {
                when (item.title) {
                    "Event" -> respondToIntent(Intent(this@BubblePickerActivity, MainActivity::class.java))
                    "Location" -> respondToIntent(Intent(this@BubblePickerActivity, LocationActivity::class.java))

                    "Developer Info" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, DeveloperActivity::class.java))
                    }
                    "Rules and Details" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, RulesActivity::class.java))
                    }
                    "About Us" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, AboutActivity::class.java))

                    }
                    "Action Wall" -> {
                    }
                    "Schedule" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, ScheduleActivity::class.java))
                    }
                }
            }


            override fun onBubbleDeselected(item: PickerItem) = toast("${item.title} deselected")
        }
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()


    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    fun respondToIntent(intent: Intent) {
        Handler().postDelayed({
            startActivity(intent)
        }, 250)
    }

    override fun onBackPressed() {
        finish()
    }


}
