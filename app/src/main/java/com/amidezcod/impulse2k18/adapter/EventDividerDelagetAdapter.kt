package com.amidezcod.impulse2k18.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
        setAnimation(holder.itemView , holder.adapterPosition)
    }
    var lastPosition: Int = -1

    private fun setAnimation(itemView: View?, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(itemView?.context, android.R.anim.fade_in)
            itemView?.startAnimation(animation)
            lastPosition = position
        }
    }
    inner class WeatherItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_divider, parent, false)) {

        fun bind(item: EventDivider) = with(itemView) {
            day.setImageBitmap(CardPagerAdapter.decodeSampledBitmapFromResource(resources, item.imageId, 50, 50))
        }

    }

}