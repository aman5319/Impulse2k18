package com.amidezcod.impulse2k18.modal

import com.amidezcod.impulse2k18.adapter.ViewTypeSchedule

/**
 * Created by amidezcod on 15/3/18.
 */
data class EventDivider(val timePoint: String, val description: String) : ViewTypeSchedule {
    override fun getViewType(): Int = ViewTypeSchedule.LINE
}