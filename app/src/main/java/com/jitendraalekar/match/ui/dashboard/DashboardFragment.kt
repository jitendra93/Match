package com.jitendraalekar.match.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitendraalekar.match.databinding.FragmentDashboardBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import timber.log.Timber


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    lateinit var binding : FragmentDashboardBinding
    private val dashboardViewModel : DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDashboardBinding.inflate(inflater,container,false).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardListAdapter = setAdapter()

        dashboardViewModel.result.onEach { state ->
            when(state){
                is DashboardViewState.Loading -> {
                    binding.message.visibility=View.VISIBLE
                    binding.message.text="Loading"
                    Timber.d("dashboard onEach Loading")
                }
                is DashboardViewState.Content -> {
                    binding.message.visibility=View.GONE
                    dashboardListAdapter.submitList(state.list)
                    Timber.d("dashboard onEach ${state.list.size} items")
                }
                is DashboardViewState.Error -> {
                    binding.message.visibility=View.VISIBLE
                    binding.message.text = state.throwable.toString()
                    Timber.d("dashboard onEach error")
                }
            }

        }.launchIn(lifecycleScope)
    }

    private fun setAdapter(): DashboardListAdapter {
        val dashboardListAdapter = DashboardListAdapter(onRowClick = {
            navigateToDetail(it)
        }, onActionBtnClick = {
            onActionBtnClick(it)
        })
        with(binding.matches) {
            adapter = dashboardListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        return dashboardListAdapter
    }


    private fun onActionBtnClick(dashboardUser: DashboardUser) {
        Toast.makeText(context,"Mark action ${dashboardUser.actionStatus} for ${dashboardUser.name}",Toast.LENGTH_SHORT).show()
        dashboardViewModel.updateActionStatus(dashboardUser)
    }

    private fun navigateToDetail(dashboardUser: DashboardUser)  {
        Toast.makeText(context,"navigate to detail for ${dashboardUser.name}",Toast.LENGTH_SHORT).show()
    }
}