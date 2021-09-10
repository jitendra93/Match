package com.jitendraalekar.match.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jitendraalekar.match.R
import com.jitendraalekar.match.databinding.FragmentDashboardBinding
import com.jitendraalekar.match.ui.MainViewModel
import com.jitendraalekar.match.util.isNetworkConnected

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val dashboardListAdapter = DashboardListAdapter(::navigateToDetail, ::onActionBtnClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDashboardBinding.inflate(inflater, container, false).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

        binding.swiperefresh.setOnRefreshListener {
            refreshData()
        }

        mainViewModel.result.onEach(::setViewState).launchIn(lifecycleScope)
    }

    private fun setViewState(state: DashboardViewState) {
        when (state) {
            is DashboardViewState.Loading -> {
                binding.swiperefresh.isRefreshing = true
            }
            is DashboardViewState.Content -> {
                binding.noDataMsg.visibility = View.GONE
                binding.swiperefresh.isRefreshing = false
                dashboardListAdapter.submitList(state.list)
            }
            is DashboardViewState.Error -> {
                binding.swiperefresh.isRefreshing = false
            }
        }
    }

    private fun refreshData() {
        if (context?.isNetworkConnected() == true) {
            mainViewModel.refreshData()
        } else {
            Snackbar.make(binding.root, getString(R.string.no_internet_msg), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun setAdapter() {

        with(binding.matches) {
            adapter = dashboardListAdapter
            addItemDecoration(LinearItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_24)))
        }
    }


    private fun onActionBtnClick(dashboardUser: DashboardUser) {
        mainViewModel.updateActionStatus(dashboardUser)
    }

    private fun navigateToDetail(dashboardUser: DashboardUser) {
        findNavController().navigate(DashboardFragmentDirections.showDetail(dashboardUser.uuid))
    }
}