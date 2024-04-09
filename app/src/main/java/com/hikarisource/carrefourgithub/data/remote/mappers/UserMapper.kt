package com.hikarisource.carrefourgithub.data.remote.mappers

import com.hikarisource.carrefourgithub.data.remote.reponse.UserResponse
import com.hikarisource.carrefourgithub.domain.model.User

object UserMapper : DomainResponseMapper<User, UserResponse> {

    override fun UserResponse.toDomain() = User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )

    override fun User.fromDomain() = UserResponse(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )
}