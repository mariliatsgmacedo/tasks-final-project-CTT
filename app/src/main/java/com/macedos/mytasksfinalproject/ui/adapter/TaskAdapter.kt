package com.macedos.mytasksfinalproject.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.macedos.mytasksfinalproject.data.model.Task

class TaskAdapter(
        var tasksList: ArrayList<Task>,
        private var myListener: TaskAdapterListener) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    interface TaskAdapterListener{}
}