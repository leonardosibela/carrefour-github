package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.model.Repository

class GetRepositoriesFromUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(name: String): List<Repository> {
        return userRepository.getUserRepos(name)
    }
}