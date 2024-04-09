package com.hikarisource.carrefourgithub.util

import com.hikarisource.carrefourgithub.domain.model.User


object TestData {
    val FIRST_USER = User(
        id = 1,
        login = "mojombo",
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        url = "https://api.github.com/users/mojombo"
    )

    val SECOND_USER = User(
        id = 2,
        login = "defunkt",
        avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
        url = "https://api.github.com/users/defunkt"
    )
}