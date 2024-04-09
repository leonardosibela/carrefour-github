package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.model.User

class GetUserByNameUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(name: String): User {
        return userRepository.getUser(name)
    }
}