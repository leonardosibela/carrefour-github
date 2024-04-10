package com.hikarisource.carrefourgithub.data.remote.repository

import com.hikarisource.carrefourgithub.data.remote.api.GithubApi
import com.hikarisource.carrefourgithub.data.remote.mappers.RepositoryMapper.toDomainList
import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.toDomain
import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.toDomainList
import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.model.Repository
import com.hikarisource.carrefourgithub.domain.model.User

class UserRepositoryApi(private val githubApi: GithubApi) : UserRepository {

    override suspend fun getAll(): List<User> {
        return githubApi.fetchUsers().toDomainList()
    }

    override suspend fun getUser(username: String): User {
        return githubApi.getUser(username).toDomain()
    }

    override suspend fun getUserRepos(username: String): List<Repository> {
        return githubApi.getUserRepos(username).toDomainList()
    }
}