package com.amidezcod.impulse2k18.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.amidezcod.impulse2k18.modal.EventItem
import impulse2k18.R
import kotlinx.android.synthetic.main.event_item.view.*
import pl.hypeapp.materialtimelineview.MaterialTimelineView

/**
 * Created by amidezcod on 15/3/18.
 */
class EventItemDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = EventItemViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewTypeSchedule) {
        holder as EventItemViewHolder
        holder.bind(item as EventItem)
    }

    inner class EventItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)) {

        fun bind(item: EventItem) = with(itemView) {
            // If is last item we need to change position type to last.
            if (item.isLastItem) {
                item_weather_timeline.position = MaterialTimelineView.POSITION_LAST
            }
            event_name.text = item.eventName
            event_location.text = item.eventLocation
            event_time.text = item.eventTime
        }

    }

}