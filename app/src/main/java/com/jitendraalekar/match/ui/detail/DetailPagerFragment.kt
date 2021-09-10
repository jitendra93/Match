package com.jitendraalekar.match.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.match.databinding.FragmentDetailPagerBinding
import com.jitendraalekar.match.ui.MainViewModel
import com.jitendraalekar.match.ui.dashboard.DashboardViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailPagerFragment : Fragment() {

    private lateinit var binding: FragmentDetailPagerBinding
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val args: DetailPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailPagerBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.apply {
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = 100
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }
        sharedViewModel.result
            .onEach {
                if (it is DashboardViewState.Content) {
                    val animate = binding.pager.adapter == null
                    val currentItem = if (animate) {
                        it.list.indexOfFirst {
                            it.uuid == args.uuid
                        }
                    } else {
                        binding.pager.currentItem
                    }
                    binding.pager.adapter = PagerAdapter(this, it.list)
                    binding.pager.post {
                        binding.pager.setCurrentItem(currentItem, animate)
                    }
                }
            }.launchIn(lifecycleScope)
    }
}