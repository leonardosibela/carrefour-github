package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.common.resultBy
import com.hikarisource.carrefourgithub.domain.model.User

class GetAllUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() = resultBy {
        userRepository.getAll()
    }
}