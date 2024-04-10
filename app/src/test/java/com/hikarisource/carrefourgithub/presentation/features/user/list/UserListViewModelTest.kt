package com.hikarisource.carrefourgithub.presentation.features.user.list

import com.hikarisource.carrefourgithub.domain.common.Result
import com.hikarisource.carrefourgithub.domain.usecase.GetAllUsersUseCase
import com.hikarisource.carrefourgithub.util.CoroutineTestRule
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER
import com.hikarisource.carrefourgithub.util.TestData.FIRST_USER_PRESENTATION
import com.hikarisource.carrefourgithub.util.TestData.SECOND_USER
import com.hikarisource.carrefourgithub.util.TestData.SECOND_USER_PRESENTATION
import com.hikarisource.carrefourgithub.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    @InjectMockKs
    private lateinit var userListViewModel: UserListViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN getAllUsersUseCase returns list WHEN fetchAllUsers called THEN must set fetchUsersState with expected value`() =
        runTest(unconfinedTestDispatcher) {
            // GIVEN
            coEvery { getAllUsersUseCase() } returns Result.Success(listOf(FIRST_USER, SECOND_USER))
            val expected = Fetched(listOf(FIRST_USER_PRESENTATION, SECOND_USER_PRESENTATION))

            // WHEN
            userListViewModel.fetchAllUsers()

            // THEN
            Assert.assertEquals(expected, userListViewModel._fetchUsersState.value)
        }

    @Test
    fun `GIVEN getAllUsersUseCase returns error WHEN fetchRepositoriesFromUser called THEN must set fetchRepositoriesState with error`() =
        runTest(unconfinedTestDispatcher) {
            // GIVEN
            val throwable = Throwable("Message")
            coEvery { getAllUsersUseCase() } returns Result.Error(throwable)
            val expected = Error(throwable)

            // WHEN
            userListViewModel.fetchAllUsers()

            // THEN
            Assert.assertEquals(expected, userListViewModel._fetchUsersState.value)
        }
}