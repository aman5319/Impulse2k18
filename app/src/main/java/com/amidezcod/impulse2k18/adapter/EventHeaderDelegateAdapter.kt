package com.amidezcod.impulse2k18.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.amidezcod.impulse2k18.modal.EventHeader
import impulse2k18.R
import kotlinx.android.synthetic.main.event_header.view.*

/**
 * Created by amidezcod on 15/3/18.
 */
class EventHeaderDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewTypeSchedule) {
        holder as WeatherItemViewHolder
        holder.bind(item as EventHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = WeatherItemViewHolder(parent)

    inner class WeatherItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_header, parent, false)) {

        fun bind(item: EventHeader) = with(itemView) {
            event_name_header.text = item.eventName
            event_location_header.text = item.eventLocation
            event_time_header.text = item.eventTime

        }
    }
}