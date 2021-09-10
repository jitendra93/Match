package com.jitendraalekar.match.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.databinding.DashboardListItemBinding

class DashboardListViewholder(val binding: DashboardListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        dashboardUser: DashboardUser,
        onRowClick: (dashboardUser: DashboardUser ) -> Unit,
         onActionClick: (dashboardUser: DashboardUser ) -> Unit

    ) {
        binding.apply {
            binding.user = dashboardUser
            root.setOnClickListener { onRowClick(dashboardUser) }
            acceptBtn.setOnClickListener { onActionClick(dashboardUser.copy(actionStatus = ActionStatus.ACCEPTED)) }
            declineBtn.setOnClickListener { onActionClick(dashboardUser.copy(actionStatus = ActionStatus.DECLINED)) }
        }

    }
}