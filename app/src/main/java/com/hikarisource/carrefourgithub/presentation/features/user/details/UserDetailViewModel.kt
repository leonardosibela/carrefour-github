package com.hikarisource.carrefourgithub.presentation.features.user.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.carrefourgithub.domain.usecase.GetRepositoriesFromUserUseCase
import com.hikarisource.carrefourgithub.presentation.mappers.RepositoryMapper.fromDomainList
import com.hikarisource.carrefourgithub.presentation.model.RepositoryPresentation
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getRepositoriesFromUserUseCase: GetRepositoriesFromUserUseCase
) : ViewModel() {

    @Suppress("PropertyName")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _fetchRepositoriesState = MutableStateFlow<FetchRepositoriesState>(Fetching)
    val fetchRepositoriesState = _fetchRepositoriesState.asStateFlow()

    fun fetchRepositoriesFromUser(userPresentation: UserPresentation) = viewModelScope.launch {
        val repositories = getRepositoriesFromUserUseCase(userPresentation.login).fromDomainList()
        _fetchRepositoriesState.value = if (repositories.isEmpty()) {
            EmptyList
        } else {
            Fetched(repositories)
        }
    }
}

sealed interface FetchRepositoriesState
data object Fetching : FetchRepositoriesState
data object EmptyList : FetchRepositoriesState
data class Fetched(val repositories: List<RepositoryPresentation>) : FetchRepositoriesState
