package com.amidezcod.impulse2k18.adapter

/**
 * Created by amidezcod on 15/3/18.
 */
interface ViewTypeSchedule {
    fun getViewType(): Int

    companion object {
        val HEADER = 1
        val LINE = 2
        val ITEM = 3
    }
}