package com.jitendraalekar.match.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.match.databinding.DashboardListItemBinding

class DashboardListViewholder(val binding: DashboardListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        DashboardUser: DashboardUser,
        onRowClick: (uuid : String) -> Unit,
         onAcceptBtnClick: (uuid : String) -> Unit,
         onDeclineBtnClick: (uuid : String) -> Unit
    ) {
        binding.apply {
            root.setOnClickListener { onRowClick(DashboardUser.uuid) }
            acceptBtn.setOnClickListener { onAcceptBtnClick(DashboardUser.uuid) }
            declineBtn.setOnClickListener { onDeclineBtnClick(DashboardUser.uuid) }
            binding.user = DashboardUser
        }

    }
}