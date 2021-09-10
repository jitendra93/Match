package com.jitendraalekar.match.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jitendraalekar.match.databinding.DashboardListItemBinding

class DashboardListAdapter(
    private val onRowClick: (dashboardUser: DashboardUser ) -> Unit,
    private val onActionBtnClick: (dashboardUser: DashboardUser ) -> Unit,
) : ListAdapter<DashboardUser, DashboardListViewholder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListViewholder {
        return DashboardListViewholder(
            DashboardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashboardListViewholder, position: Int) {
        holder.bind(getItem(position), onRowClick,onActionBtnClick)
    }

    private object DiffCallBack : DiffUtil.ItemCallback<DashboardUser>() {
        override fun areItemsTheSame(oldItem: DashboardUser, newItem: DashboardUser): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DashboardUser, newItem: DashboardUser): Boolean {
            return oldItem.uuid == newItem.uuid && oldItem.actionStatus == newItem.actionStatus
        }
    }
}