package com.amidezcod.impulse2k18.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.amidezcod.impulse2k18.modal.EventDivider
import com.amidezcod.impulse2k18.modal.EventHeader
import com.amidezcod.impulse2k18.modal.EventItem

/**
 * Created by amidezcod on 15/3/18.
 */
class ScheduleRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var item: ArrayList<ViewTypeSchedule>

    private var delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        item = ArrayList()
        delegateAdapter.put(ViewTypeSchedule.HEADER, EventHeaderDelegateAdapter())
        delegateAdapter.put(ViewTypeSchedule.LINE, EventDividerDelagetAdapter())
        delegateAdapter.put(ViewTypeSchedule.ITEM, EventItemDelegateAdapter())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapter.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapter.get(getItemViewType(position)).onBindViewHolder(holder, item[position])
    }

    override fun getItemViewType(position: Int): Int =
            item[position].getViewType()

    fun addEventHeader(item: EventHeader) {
        this.item.add(item)
        notifyDataSetChanged()
    }

    fun addEventDivider(item: EventDivider) {
        this.item.add(item)
        notifyDataSetChanged()
    }

    fun addEventItem(item: EventItem) {
        this.item.add(item)
        notifyDataSetChanged()
    }
}