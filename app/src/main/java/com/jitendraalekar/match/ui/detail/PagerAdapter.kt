package com.jitendraalekar.match.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.ui.dashboard.DashboardUser

class PagerAdapter(parentFragment: Fragment,var listOfUsers : List<DashboardUser>)
    : FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int {
       return listOfUsers.size
    }

    override fun createFragment(position: Int): Fragment {
        return DetailFragment.getInstance(listOfUsers[position].uuid)
    }
}