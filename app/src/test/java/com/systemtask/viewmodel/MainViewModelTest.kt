package com.systemtask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.systemtask.api.UsersDataApi
import com.systemtask.di.NetworkRepository
import com.systemtask.model.UserDetailsItem
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var viewModel: UserDetailsViewModel
    lateinit var networkRepository: NetworkRepository

    @Mock
    lateinit var usersDataApi: UsersDataApi

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        networkRepository = NetworkRepository(usersDataApi)
        viewModel = UserDetailsViewModel(networkRepository)
    }

    @Test
    fun getUserApiSuccessTest() {
        runBlocking {
            Mockito.`when`(networkRepository.getUsersList())
                .thenReturn(
                    Response.success(
                        listOf<UserDetailsItem>(
                            UserDetailsItem(
                                "Jhon@gmail.com",
                                "Male",
                                12345,
                                "Jhon",
                                "Active"
                            )
                        )
                    )
                )
            viewModel.getUserList()
            val result = viewModel.userDetailsList.value
            assertEquals(UserDetailsItem("Jhon@gmail.com", "Male", 12345, "Jhon", "Active"), result)

        }

    }
}