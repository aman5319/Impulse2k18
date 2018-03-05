package com.amidezcod.impulse2k18.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import impulse2k18.R

/**
 * Created by amidezcod on 5/3/18.
 */
class RulesAdapter(val context: Context, var childRules: HashMap<String, ArrayList<String>>, var parentRules: ArrayList<String>) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): Any {
        return parentRules[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        if (convertView == null) {
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.rules_parent, parent, false)

        }
        val txtParent = convertView!!.findViewById<View>(R.id.parent_rules_text) as TextView
        txtParent.text = getGroup(groupPosition) as String
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childRules[parentRules[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childRules[parentRules[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        if (convertView == null) {
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.rules_child, parent, false)
        }
        val txtChild = convertView!!.findViewById<View>(R.id.child_rules_text) as TextView
        txtChild.text = getChild(groupPosition, childPosition) as String
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return parentRules.size
    }
}