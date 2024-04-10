package com.hikarisource.carrefourgithub.data.fake

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.model.Repository
import com.hikarisource.carrefourgithub.domain.model.User

object UserRepositoryFake : UserRepository {

    private val users = listOf(
        User(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            url = "https://api.github.com/users/mojombo"
        ),
        User(
            id = 2,
            login = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            url = "https://api.github.com/users/defunkt"
        ),
        User(
            id = 3,
            login = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
            url = "https://api.github.com/users/pjhyett"
        ),
    )

    private val repos = listOf(
        Repository(
            id = 1861402,
            name = "ace",
            url = "https://github.com/defunkt/ace"
        ),
        Repository(
            id = 3594,
            name = "acts_as_textiled",
            url = "https://github.com/defunkt/acts_as_textiled"
        ),
    )

    override suspend fun getAll(): List<User> {
        return users
    }

    override suspend fun getUserRepos(username: String): List<Repository> {
        return repos
    }
}