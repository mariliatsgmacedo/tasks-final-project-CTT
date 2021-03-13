package com.macedos.mytasksfinalproject.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.ui.tab.TaskFragment

class MyViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val mList: ArrayList<Fragment> = arrayListOf(
        TaskFragment(Task.TODO),
        TaskFragment(Task.IN_PROGRESS),
        TaskFragment(Task.DONE)
    )

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mList[position]
    }

}