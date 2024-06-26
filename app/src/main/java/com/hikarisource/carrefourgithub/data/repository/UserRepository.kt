package com.hikarisource.carrefourgithub.data.repository

import com.hikarisource.carrefourgithub.domain.model.Repository
import com.hikarisource.carrefourgithub.domain.model.User

interface UserRepository {

    suspend fun getAll(): List<User>

    suspend fun getUserRepos(username: String): List<Repository>
}