package com.todoroo.astrid.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.todoroo.astrid.data.TaskEntity
import com.todoroo.astrid.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = TaskRepository.get(app)

    val tasks: StateFlow<List<TaskEntity>> = repo.tasks
        .map { it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch { if (title.isNotBlank()) repo.addTask(title.trim()) }
    }
} 