package com.example.sampleapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleapp.datasource.RemoteDataSource
import com.example.sampleapp.model.ToDo
import com.example.sampleapp.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ToDoRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val remoteDataSource: RemoteDataSource = mockk(relaxed = true)
    private lateinit var toDoRepository: ToDoRepository
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        toDoRepository = ToDoRepository(remoteDataSource)

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should emit same object as api response`() = runTest {
        val body = "error".toResponseBody("text".toMediaTypeOrNull())
        val response: Response<List<ToDo>> = Response.error(401, body)
        coEvery {
            remoteDataSource.getToDo()
        } returns response
       val flow = toDoRepository.getToDo()
        flow.collect{
        }
        coVerify {
            remoteDataSource.getToDo()
        }


    }
}