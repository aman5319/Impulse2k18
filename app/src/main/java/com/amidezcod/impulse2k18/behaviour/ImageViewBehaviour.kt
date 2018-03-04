package com.amidezcod.impulse2k18.behaviour

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Created by amidezcod on 4/3/18.
 */
class ImageViewBehaviour(): CoordinatorLayout.Behavior<ImageView>() {
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: ImageView?, dependency: View?): Boolean {
        return dependency is Toolbar
    }

    constructor(context: Context, attrs: AttributeSet) : this()


    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: ImageView?, dependency: View?): Boolean {
        child?.translationY=30F
        child?.translationX=30F
return true
    }
}