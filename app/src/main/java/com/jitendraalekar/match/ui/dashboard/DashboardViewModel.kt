package com.jitendraalekar.match.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.data.source.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext


@HiltViewModel
class DashboardViewModel @Inject constructor( val repository: Repository) : ViewModel() {

    private val _results = MutableStateFlow<DashboardViewState>(DashboardViewState.Loading)

    val result : Flow<DashboardViewState> = _results

    init {
        load()
    }
    fun load(){
        _results.value = DashboardViewState.Loading

        viewModelScope.launch {
            when(val res = repository.getUsers()){
                is Result.Success ->{
                    res.data.conflate().collect{
                        _results.value = DashboardViewState.Content(it.map { DashboardUser.fromUser(it) })
                    }
                }
                is Result.Error -> _results.value = DashboardViewState.Error(res.exception)
            }
        }
    }
    fun updateDb(user : User){
        viewModelScope.launch {
            repository.updateUserStatus(user.copy(firstName = "jitendra"))
        }
    }

}