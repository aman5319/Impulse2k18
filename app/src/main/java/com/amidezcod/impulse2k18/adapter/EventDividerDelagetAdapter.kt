package com.amidezcod.impulse2k18.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.amidezcod.impulse2k18.modal.EventDivider
import impulse2k18.R
import kotlinx.android.synthetic.main.event_divider.view.*

/**
 * Created by amidezcod on 15/3/18.
 */
class EventDividerDelagetAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = WeatherItemViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewTypeSchedule) {
        holder as WeatherItemViewHolder
        holder.bind(item as EventDivider)
    }

    inner class WeatherItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_divider, parent, false)) {

        fun bind(item: EventDivider) = with(itemView) {
            time.text = item.timePoint
            time_description.text = item.description
        }

    }

}