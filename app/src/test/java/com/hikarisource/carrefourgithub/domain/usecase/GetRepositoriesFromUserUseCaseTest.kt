package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.util.TestData.FIRST_REPO
import com.hikarisource.carrefourgithub.util.TestData.SECOND_REPO
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
class GetRepositoriesFromUserUseCaseTest {

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var getRepositoriesFromUserUseCase: GetRepositoriesFromUserUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN UserRepository getUser return user WHEN GetAllUsersUseCaseTest called THEN must return same value`() =
        runTest {
            // GIVEN
            val repositories = listOf(FIRST_REPO, SECOND_REPO)
            val username = "mojombo"
            coEvery { userRepository.getUserRepos(username) } returns repositories

            // WHEN
            val actual = getRepositoriesFromUserUseCase(username)

            // THEN
            coVerify(exactly = 1) { userRepository.getUserRepos(username) }
            Assert.assertEquals(repositories, actual)
        }
}