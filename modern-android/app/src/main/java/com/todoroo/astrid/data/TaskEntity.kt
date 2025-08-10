package com.todoroo.astrid.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tasks", indices = [Index(value = ["title"])])
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "importance")
    val importance: Int = 0,
    @ColumnInfo(name = "dueDate")
    val dueDate: Long = 0,
    @ColumnInfo(name = "hideUntil")
    val hideUntil: Long = 0,
    @ColumnInfo(name = "created")
    val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "modified")
    val modified: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "completed")
    val completed: Long = 0,
    @ColumnInfo(name = "deleted")
    val deleted: Long = 0,
) 