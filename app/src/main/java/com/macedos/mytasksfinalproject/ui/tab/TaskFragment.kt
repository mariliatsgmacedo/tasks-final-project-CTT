package com.macedos.mytasksfinalproject.ui.tab

import android.os.Bundle
import android.view.View
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.FragmentTaskBinding
import com.macedos.mytasksfinalproject.ui.adapter.TaskAdapter
import com.macedos.mytasksfinalproject.ui.base.BaseFragment
import com.macedos.mytasksfinalproject.utils.MyViewPagerAdapter

class TaskFragment(private val status:Int): BaseFragment<FragmentTaskBinding,TaskViewModel>(),TaskAdapter.TaskAdapterListener{

    private lateinit var adapter: TaskAdapter

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskViewModel>> {
        return Triple(R.layout.fragment_task,BR.viewModel,TaskViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()

        adapter = TaskAdapter(this)
        binding.myRecyclerView.adapter = adapter
        viewModel.getAllTasks().observe(viewLifecycleOwner){ results ->
            updateListFiltered(results)
        }

        viewModel.taskList.observe(viewLifecycleOwner){
            adapter.setAllTasks(it)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllTasks().value?.let {
            updateListFiltered(it)
        }
    }

    private fun updateListFiltered(results: List<Task>) {
        val list = results.filter {
            it.status == status
        }
        viewModel.taskList.value = list
    }

    private fun configureView(){
        binding.buttonAdd.visibility = if(status == Task.TODO) View.VISIBLE else View.GONE
        when(status) {
            Task.TODO -> {
                binding.emptyMessage.text = getString(R.string.message_things_todo)
            }
            Task.IN_PROGRESS -> {
                binding.emptyMessage.text = getString(R.string.message_things_in_progress)
            }
            Task.DONE -> {
                binding.emptyMessage.text = getString(R.string.message_things_done)
            }
        }

    }

    override fun onClick(item: Task) {

    }

}