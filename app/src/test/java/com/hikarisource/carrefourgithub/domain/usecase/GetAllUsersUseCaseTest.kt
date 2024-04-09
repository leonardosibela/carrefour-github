package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.model.User
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER
import com.hikarisource.carrefourgithub.util.TestData.SECOND_USER
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
class GetAllUsersUseCaseTest {

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN UserRepository getAll return list WHEN GetAllUsersUseCaseTest called THEN must return same values`() =
        runTest {
            // GIVEN
            val users = listOf(FIRST_USER, SECOND_USER)
            coEvery { userRepository.getAll() } returns users

            // WHEN
            val actual = getAllUsersUseCase()

            // THEN
            coVerify(exactly = 1) { userRepository.getAll() }
            Assert.assertEquals(users, actual)
        }

    @Test
    fun `GIVEN UserRepository getAll return empty list WHEN GetAllUsersUseCaseTest called THEN must return empty list`() =
        runTest {
            // GIVEN
            val users = emptyList<User>()
            coEvery { userRepository.getAll() } returns users

            // WHEN
            val actual = getAllUsersUseCase()

            // THEN
            coVerify(exactly = 1) { userRepository.getAll() }
            Assert.assertEquals(users, actual)
        }
}