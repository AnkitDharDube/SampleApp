package com.example.sampleapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleapp.base.NetworkResult
import com.example.sampleapp.model.ToDo
import com.example.sampleapp.repository.ToDoRepository
import com.example.sampleapp.utils.MainCoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()
    private lateinit var viewModel: TodoViewModel
    private val toDoRepository :ToDoRepository = mockk(relaxed = true)
    @Before
    fun setup() {
        viewModel = TodoViewModel(toDoRepository)
    }

    @After
    fun tearDown() {
        // Cleanup
        clearAllMocks()
    }

    @Test
    fun `should emit error object when api response error`() = runTest {
        val result = NetworkResult.Sucesss(emptyList<ToDo>())
        coEvery {
            toDoRepository.getToDo()
        }returns flowOf(result )
        viewModel.todo.observeForever { }
        viewModel.fetchToDo()

        advanceUntilIdle()
        coVerify {
            toDoRepository.getToDo()
        }
        assertEquals(viewModel.todo.value, result)
    }
}