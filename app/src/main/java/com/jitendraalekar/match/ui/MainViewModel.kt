package com.jitendraalekar.match.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.source.repository.Repository
import com.jitendraalekar.match.network.Result
import com.jitendraalekar.match.ui.dashboard.DashboardUser
import com.jitendraalekar.match.ui.dashboard.DashboardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val filterStatus = MutableStateFlow<ActionStatus?>(null)

    private val _results = MutableStateFlow<DashboardViewState>(DashboardViewState.Loading)

    val result : Flow<DashboardViewState> = _results

    fun refreshData(){
        viewModelScope.launch {
            repository.refreshData()
        }
    }
    fun load(){
        _results.value = DashboardViewState.Loading
        refreshData()
        viewModelScope.launch {

            when(val res = repository.getUsers()){
                is Result.Success ->{
                    res.data.conflate().collect{
                        _results.value = DashboardViewState.Content(it.map {
                            DashboardUser.fromUser(it)
                        })
                    }
                }
                is Result.Error -> _results.value = DashboardViewState.Error(res.exception)
            }
        }
    }

    fun updateActionStatus( dashboardUser: DashboardUser){
        viewModelScope.launch {
            repository.updateUserActionStatus(dashboardUser.uuid, dashboardUser.actionStatus)
        }
    }

    fun getUser(uuid : String) : DashboardUser {

        return (_results.value as DashboardViewState.Content).list.first { it.uuid == uuid }
    }
}