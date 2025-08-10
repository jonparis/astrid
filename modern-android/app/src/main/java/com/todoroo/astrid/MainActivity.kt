package com.todoroo.astrid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.todoroo.astrid.databinding.ActivityMainBinding
import com.todoroo.astrid.ui.TasksAdapter
import com.todoroo.astrid.ui.TasksViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TasksAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.tasks.collectLatest { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.addTask("Task " + System.currentTimeMillis().toString().takeLast(4))
            Snackbar.make(binding.root, "Task added", Snackbar.LENGTH_SHORT).show()
        }
    }
} 