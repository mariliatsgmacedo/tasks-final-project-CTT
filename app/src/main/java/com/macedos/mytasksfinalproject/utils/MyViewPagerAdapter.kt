package com.macedos.mytasksfinalproject.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.ui.tab.TaskFragment

class MyViewPagerAdapter(fa: FragmentActivity, listener:  TaskFragment.TaskFragmentListener) : FragmentStateAdapter(fa) {
    private val mList: ArrayList<Fragment> = arrayListOf(
        TaskFragment(Task.TODO, listener),
        TaskFragment(Task.IN_PROGRESS, listener),
        TaskFragment(Task.DONE, listener)
    )

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mList[position]
    }

    fun getItem(position: Int): Fragment {
        return mList[position]
    }

}