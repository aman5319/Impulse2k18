package com.amidezcod.impulse2k18.modal

import com.amidezcod.impulse2k18.adapter.ViewTypeSchedule

/**
 * Created by amidezcod on 15/3/18.
 */
data class EventHeader(
        val eventName: String,
        val eventLocation: String,
        val eventTime: String) : ViewTypeSchedule {
    override fun getViewType(): Int = ViewTypeSchedule.HEADER


}