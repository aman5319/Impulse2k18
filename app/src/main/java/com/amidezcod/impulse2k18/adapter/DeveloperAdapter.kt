package com.amidezcod.impulse2k18.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.amidezcod.impulse2k18.modal.DeveloperModal
import impulse2k18.R

/**
 * Created by amidezcod on 27/2/18.
 */
class DeveloperAdapter(var list: ArrayList<DeveloperModal>, val context: Context) : RecyclerView.Adapter<DeveloperAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.developer_item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bind(list[position])
        setAnimation(holder?.itemView, position)
        holder?.itemView?.setOnClickListener({
            Toast.makeText(context, "Swipe to Contact any of us", Toast.LENGTH_SHORT).show()
        })
    }

    val lastPosition: Int = -1

    private fun setAnimation(itemView: View?, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(itemView?.context, android.R.anim.slide_in_left)
            itemView?.startAnimation(animation)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: TextView = itemView.findViewById(R.id.developer_name)
        var textViewType: TextView = itemView.findViewById(R.id.developer_type)

        fun bind(developerModal: DeveloperModal) {
            textViewName.text = developerModal.name
            textViewType.text = developerModal.type
        }
    }
}