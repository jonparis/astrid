package com.todoroo.astrid.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class TaskRepository private constructor(context: Context) {
    private val dao = AstridDatabase.get(context).taskDao()

    val tasks: Flow<List<TaskEntity>> = dao.observeTasks()

    suspend fun addTask(title: String) {
        val now = System.currentTimeMillis()
        dao.upsert(TaskEntity(title = title, created = now, modified = now))
    }

    companion object {
        @Volatile private var INSTANCE: TaskRepository? = null
        fun get(context: Context): TaskRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: TaskRepository(context.applicationContext).also { INSTANCE = it }
        }
    }
} 