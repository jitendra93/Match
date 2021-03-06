package com.jitendraalekar.match.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.databinding.FragmentDetailBinding
import com.jitendraalekar.match.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

const val UUID = "com.jitendraalekar.match.ui.detail.DetailFragment.uuid"

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val sharedViewModel : MainViewModel by activityViewModels()

    companion object{
        fun getInstance(uuid: String) : DetailFragment{
            val bundle = Bundle()
            bundle.putString(UUID,uuid)
            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailBinding.inflate(inflater,container,false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user= sharedViewModel.getUser(requireArguments().getString(UUID)!!)
        binding.user = user
        binding.executePendingBindings()
        binding.acceptBtn.setOnClickListener {
            sharedViewModel.updateActionStatus(user.copy(actionStatus = ActionStatus.ACCEPTED))
        }
        binding.declineBtn.setOnClickListener {
            sharedViewModel.updateActionStatus(user.copy(actionStatus = ActionStatus.DECLINED))
        }
    }
}