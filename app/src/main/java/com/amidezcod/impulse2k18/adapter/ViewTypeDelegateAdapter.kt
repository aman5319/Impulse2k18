package com.amidezcod.impulse2k18.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by amidezcod on 15/3/18.
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewTypeSchedule)

}