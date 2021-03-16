package com.macedos.mytasksfinalproject.ui.tab

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.config.AppConstants
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.FragmentTaskBinding
import com.macedos.mytasksfinalproject.ui.adapter.TaskAdapter
import com.macedos.mytasksfinalproject.ui.base.BaseFragment
import com.macedos.mytasksfinalproject.ui.details.TaskDetailsActivity
import com.macedos.mytasksfinalproject.ui.form.TaskFormActivity

class TaskFragment(private val status: Int, var listener: TaskFragmentListener): BaseFragment<FragmentTaskBinding,TaskViewModel>(),TaskAdapter.TaskAdapterListener{

    private lateinit var adapter: TaskAdapter
    private val observerMain = Observer<List<Task>> {
        updateListFiltered(it)
    }
    private val observerFilteredList = Observer<List<Task>> {
        adapter.setAllTasks(it)
        binding.loading.visibility = View.GONE
    }

    override fun setViewConfiguration(): Triple<Int, Int, Class<TaskViewModel>> {
        return Triple(R.layout.fragment_task,BR.viewModel,TaskViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()

        adapter = TaskAdapter(this)
        binding.myRecyclerView.adapter = adapter

        binding.buttonAdd.setOnClickListener {
            startFormActivity()
        }

    }

    override fun onResume() {
        super.onResume()
        binding.loading.visibility = View.VISIBLE
        viewModel.getAllTasks().observe(viewLifecycleOwner,observerMain)
        viewModel.taskList.observe(viewLifecycleOwner, observerFilteredList)
        viewModel.getAllTasks().value?.let {
            updateListFiltered(it)
        }
    }

    override fun onPause() {
        viewModel.taskList.removeObserver(observerFilteredList)
        viewModel.getAllTasks().removeObserver(observerMain)
        super.onPause()
    }



    private fun updateListFiltered(results: List<Task>) {
        val list = results.filter {
            it.status == status
        }

        listener.showFilter(list.isNotEmpty(), status)

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
        startDetailActivity(item)
    }

    private fun startFormActivity(task:Task? = null){
        val intent = Intent(requireContext(),TaskFormActivity::class.java)
        intent.putExtra(AppConstants.KEY_TASK,task)
        startActivity(intent)
    }

    private fun startDetailActivity(task:Task){
        val intent = Intent(requireContext(), TaskDetailsActivity::class.java)
        intent.putExtra(AppConstants.KEY_TASK,task)
        startActivity(intent)
    }


    fun setFilter(query:String){
        adapter.query = query
        adapter.executeFilter()
    }

    interface TaskFragmentListener{
        fun showFilter(show: Boolean, status: Int)
    }

}