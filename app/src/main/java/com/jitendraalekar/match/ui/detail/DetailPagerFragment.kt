package com.jitendraalekar.match.ui.detail

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import com.jitendraalekar.match.databinding.FragmentDetailBinding
import com.jitendraalekar.match.databinding.FragmentDetailPagerBinding
import com.jitendraalekar.match.ui.dashboard.DashboardViewModel
import com.jitendraalekar.match.ui.dashboard.DashboardViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailPagerFragment : Fragment() {

    private lateinit var binding : FragmentDetailPagerBinding
    val sharedViewModel : DashboardViewModel by activityViewModels()
    val args : DetailPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDetailPagerBinding.inflate(inflater,container,false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.offscreenPageLimit = 1
        with(binding.pager){
            setPadding(40, 0, 40, 0)
            clipToPadding = false

        }
      
        sharedViewModel.result
            .onEach {
            if(it is DashboardViewState.Content) {
                val animate = binding.pager.adapter == null
                val currentItem = if(animate){
                    it.list.indexOfFirst {
                        it.uuid==args.uuid
                    }
                }else{
                    binding.pager.currentItem
                }
                binding.pager.adapter = PagerAdapter(this,it.list)


               binding.pager.post{
                    binding.pager.setCurrentItem(currentItem,animate)
                }

            }
        }.launchIn(lifecycleScope)

    }
}