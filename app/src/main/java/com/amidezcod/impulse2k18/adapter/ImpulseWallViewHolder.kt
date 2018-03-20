package com.amidezcod.impulse2k18.adapter

import android.graphics.BitmapFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.amidezcod.impulse2k18.modal.ImpulseWallModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import impulse2k18.R

/**
 * Created by amidezcod on 20/3/18.
 */
class ImpulseWallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userImage: ImageView = itemView.findViewById(R.id.imageView_wall_profile_pic)
    val userUploadedImage: ImageView = itemView.findViewById(R.id.imageView_photo_upload)
    val username: TextView = itemView.findViewById(R.id.textView_profile_name)
    private val usertext: TextView = itemView.findViewById(R.id.textView_text)
    private val timeText: TextView = itemView.findViewById(R.id.time_wall)
    private val dateText: TextView = itemView.findViewById(R.id.date_wall)

    fun bind(impulseWallModel: ImpulseWallModel) {
        username.text = impulseWallModel.profileName
        if (impulseWallModel.writterText.isNotEmpty()) {
            usertext.visibility = View.VISIBLE
            usertext.text = impulseWallModel.writterText
        } else {
            usertext.visibility = View.GONE
        }
        if (impulseWallModel.ProfilePic.isNotEmpty()) {
            Glide.with(itemView.context)
                    .load(impulseWallModel.ProfilePic)
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage)
        } else {
            val a = RoundedBitmapDrawableFactory.create(itemView.resources, BitmapFactory.decodeResource(itemView.resources, R.drawable.impulse_logo))
            a.isCircular = true
            userImage.setImageDrawable(a)
        }
        if (impulseWallModel.uploadedPhoto.isNotEmpty()) {
            userUploadedImage.visibility = View.VISIBLE
            Glide.with(itemView.context)
                    .load(impulseWallModel.uploadedPhoto)
                    .into(userUploadedImage)

        } else {
            userUploadedImage.visibility = View.GONE
        }
        timeText.text = impulseWallModel.timeText
        dateText.text = impulseWallModel.dateText
    }
}