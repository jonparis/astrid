package com.todoroo.astrid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.todoroo.astrid.data.TaskEntity
import com.todoroo.astrid.databinding.ItemTaskBinding

class TasksAdapter : ListAdapter<TaskEntity, TasksAdapter.TaskViewHolder>(Diff) {
    object Diff : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity) = oldItem == newItem
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskEntity) { binding.title.text = item.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
} 