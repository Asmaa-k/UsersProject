package asmaa.khb.usersproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asmaa.khb.usersproject.api.util.Resource
import asmaa.khb.usersproject.models.User
import asmaa.khb.usersproject.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    /*Notification Settings List*/
    private val _userList: MutableSharedFlow<List<User>> = MutableSharedFlow()
    internal val userList = _userList.asSharedFlow()

    /*Error*/
    private val _error: MutableSharedFlow<Throwable?> = MutableSharedFlow()
    internal val error: SharedFlow<Throwable?> get() = _error.asSharedFlow()

    /*Loading*/
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    internal val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    private val users = repository.getRestaurants()

    fun getUsersList() {
        _loading.value = true
        viewModelScope.launch {
            users.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        if (result.data.isNullOrEmpty()) {
                            _error.emit(result.error)
                        } else {
                            _userList.emit(result.data)
                        }
                    }
                    is Resource.Success -> _userList.emit(result.data!!)
                    is Resource.Loading -> _loading.value = false
                }
            }
        }
    }
}