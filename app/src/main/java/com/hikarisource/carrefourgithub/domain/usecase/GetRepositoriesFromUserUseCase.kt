package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.common.resultBy
import com.hikarisource.carrefourgithub.domain.model.Repository

class GetRepositoriesFromUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(name: String) = resultBy {
        userRepository.getUserRepos(name)
    }
}