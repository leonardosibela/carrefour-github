package com.hikarisource.carrefourgithub.presentation.mappers

import com.hikarisource.carrefourgithub.domain.model.User
import com.hikarisource.carrefourgithub.presentation.model.UserPresentation

object UserMapper : DomainPresentationMapper<User, UserPresentation> {

    override fun UserPresentation.toDomain() = User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )

    override fun User.fromDomain() = UserPresentation(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )
}