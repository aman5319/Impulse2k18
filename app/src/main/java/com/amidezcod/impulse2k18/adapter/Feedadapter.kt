package com.amidezcod.impulse2k18.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amidezcod.impulse2k18.modal.FeedModal
import com.squareup.picasso.Picasso
import impulse2k18.R

/**
 * Created by amidezcod on 28/2/18.
 */
class Feedadapter(var context: Context, private var list: MutableList<FeedModal>) : RecyclerView.Adapter<Feedadapter.FeedHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedHolder {
        return FeedHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_feed, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FeedHolder?, position: Int) {
        holder?.bind(list[position])
    }

    inner class FeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)
        val photoImageView = itemView.findViewById<ImageView>(R.id.photoImageView)

        fun bind(feedModal: FeedModal) {
            if (feedModal.photoUrl!!.isNotEmpty())
                Picasso.with(context)
                        .load(feedModal.photoUrl)
                        .into(photoImageView)
            messageTextView.text=feedModal.text
        }
    }
}