package com.hikarisource.carrefourgithub.data.remote.api

import com.hikarisource.carrefourgithub.data.remote.reponse.RepositoryResponse
import com.hikarisource.carrefourgithub.data.remote.reponse.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users")
    suspend fun searchUsers(): List<UserResponse>

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): UserResponse

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String
    ): List<RepositoryResponse>
}