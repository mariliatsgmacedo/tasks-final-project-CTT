package com.macedos.mytasksfinalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.data.model.Task

class TaskAdapter(
    private var myListener: TaskAdapterListener
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var tasksList: List<Task> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_task_item)
        val description: TextView = view.findViewById(R.id.description_task_item)
        val contentCard: MaterialCardView = view.findViewById(R.id.my_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tasksList[position].title
        holder.description.text = tasksList[position].description
        holder.contentCard.isCheckable = true
        holder.contentCard.isFocusable = true

        holder.contentCard.setOnClickListener {
            myListener.onClick(tasksList[position])
        }
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    interface TaskAdapterListener {
        fun onClick(item: Task)
    }

    fun setAllTasks(tasksList: List<Task>){
        this.tasksList = tasksList
        notifyDataSetChanged()
    }
}