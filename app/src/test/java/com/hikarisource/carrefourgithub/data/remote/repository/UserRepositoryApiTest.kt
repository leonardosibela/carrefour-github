package com.hikarisource.carrefourgithub.data.remote.repository

import com.hikarisource.carrefourgithub.data.remote.api.GithubApi
import com.hikarisource.carrefourgithub.util.TestData.FIRST_REPO
import com.hikarisource.carrefourgithub.util.TestData.FIRST_REPO_RESPONSE
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER_RESPONSE
import com.hikarisource.carrefourgithub.util.TestData.SECOND_REPO
import com.hikarisource.carrefourgithub.util.TestData.SECOND_REPO_RESPONSE
import com.hikarisource.carrefourgithub.util.TestData.SECOND_USER
import com.hikarisource.carrefourgithub.util.TestData.SECOND_USER_RESPONSE
import com.hikarisource.carrefourgithub.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryApiTest {

    @RelaxedMockK
    private lateinit var githubApi: GithubApi

    @InjectMockKs
    private lateinit var userRepositoryApi: UserRepositoryApi

    init {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN userRepositoryApi WHEN getAll called THEN must return same value as GithubApi fetchUsers mapped`() =
        runTest {
            // GIVEN
            coEvery { githubApi.fetchUsers() } returns listOf(
                FIRST_USER_RESPONSE,
                SECOND_USER_RESPONSE
            )

            val expected = listOf(FIRST_USER, SECOND_USER)

            // WHEN
            val actual = userRepositoryApi.getAll()

            // THEN
            coVerify(exactly = 1) { githubApi.fetchUsers() }
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `GIVEN userRepositoryApi WHEN getUserRepos called THEN must return same value as GithubApi getUserRepos mapped`() =
        runTest {
            // GIVEN
            val username = "leonardosibela"
            coEvery {
                githubApi.getUserRepos(username)
            } returns listOf(
                FIRST_REPO_RESPONSE,
                SECOND_REPO_RESPONSE
            )

            val expected = listOf(FIRST_REPO, SECOND_REPO)

            // WHEN
            val actual = userRepositoryApi.getUserRepos(username)

            // THEN
            coVerify(exactly = 1) { githubApi.getUserRepos(username) }
            Assert.assertEquals(expected, actual)
        }
}