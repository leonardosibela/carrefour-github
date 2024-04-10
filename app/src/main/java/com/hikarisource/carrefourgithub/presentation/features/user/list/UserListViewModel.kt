package com.hikarisource.carrefourgithub.presentation.features.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.carrefourgithub.domain.usecase.GetAllUsersUseCase
import com.hikarisource.carrefourgithub.presentation.mappers.UserMapper.fromDomainList
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _fetchUsersState = MutableStateFlow<FetchUserState>(Fetching)
    val fetchUsersState = _fetchUsersState.asStateFlow()

    fun fetchAllUsers() = viewModelScope.launch {
        val users = getAllUsersUseCase().fromDomainList()
        _fetchUsersState.value = if (users.isEmpty()) {
            EmptyList
        } else {
            Fetched(users)
        }
    }
}

sealed interface FetchUserState
data object Fetching : FetchUserState
data object EmptyList : FetchUserState
data class Fetched(val users: List<UserPresentation>) : FetchUserState
