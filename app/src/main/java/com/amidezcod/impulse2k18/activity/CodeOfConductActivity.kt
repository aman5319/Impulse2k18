package com.amidezcod.impulse2k18.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_code_of_conduct.*

class CodeOfConductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_of_conduct)
        pdfView.fromAsset("code_of_conduct.pdf")
                .defaultPage(0)
                .load()
    }
}
