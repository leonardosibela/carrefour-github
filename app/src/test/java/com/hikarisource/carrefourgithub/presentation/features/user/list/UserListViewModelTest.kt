package com.hikarisource.carrefourgithub.presentation.features.user.list

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
    fun `GIVEN userListViewModel WHEN fetchAllUsers called THEN must set fetchUsersState with expected value`() =
        runTest(unconfinedTestDispatcher) {
            // GIVEN
            coEvery { getAllUsersUseCase() } returns listOf(FIRST_USER, SECOND_USER)
            val expected = Fetched(listOf(FIRST_USER_PRESENTATION, SECOND_USER_PRESENTATION))

            // WHEN
            userListViewModel.fetchAllUsers()

            // THEN
            Assert.assertEquals(expected, userListViewModel._fetchUsersState.value)
        }
}