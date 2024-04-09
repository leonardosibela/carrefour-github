package com.hikarisource.carrefourgithub.domain.usecase

import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER
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
class GetUserByLoginUseCaseTest {

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var getUserByNameUseCase: GetUserByNameUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN UserRepository getUser return user WHEN GetAllUsersUseCaseTest called THEN must return same value`() =
        runTest {
            // GIVEN
            val user = FIRST_USER
            val username = "mojombo"
            coEvery { userRepository.getUser(username) } returns user

            // WHEN
            val actual = getUserByNameUseCase(username)

            // THEN
            coVerify(exactly = 1) { userRepository.getUser(username) }
            Assert.assertEquals(user, actual)
        }
}