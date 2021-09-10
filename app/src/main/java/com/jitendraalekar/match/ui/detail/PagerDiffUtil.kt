package com.jitendraalekar.match.ui.detail

import androidx.recyclerview.widget.DiffUtil
import com.jitendraalekar.match.ui.dashboard.DashboardUser

class PagerDiffUtil(private val oldList: List<DashboardUser>, private val newList: List<DashboardUser>)
    : DiffUtil.Callback() {



    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uuid == newList[newItemPosition].uuid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
