package com.macedos.mytasksfinalproject.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.ActivityMainBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity
import com.macedos.mytasksfinalproject.ui.tab.TaskFragment
import com.macedos.mytasksfinalproject.utils.MyViewPagerAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), TaskFragment.TaskFragmentListener {
    //Variables
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var myViewPagerAdapter: MyViewPagerAdapter

    override fun setViewConfiguration(): Triple<Int, Int, Class<MainViewModel>> {
        return Triple(R.layout.activity_main, BR.viewModel, MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the FirebaseAnalytics instance
        firebaseAnalytics = Firebase.analytics


//       val respo = TaskRepository()
//        respo.insert(
//            Task(1,"Teste Title", "Descrição suceeeesssss",Task.TODO)
//        )

        //TabLayout Configuration
        val tabLayout = binding.tabLayout

        //ViewPager Configuration
        val viewPager = binding.myViewPager
        myViewPagerAdapter = MyViewPagerAdapter(this, this)
        viewPager.adapter = myViewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.todo_tabbar)
                1 -> getString(R.string.in_progress_tabbar)
                else -> getString(R.string.done_tabbar)
            }
        }.attach()

        binding.searchField.addTextChangedListener { editable ->
            (0 until myViewPagerAdapter.itemCount).forEach {
                val frag = myViewPagerAdapter.getItem(it) as TaskFragment
                frag.setFilter(editable.toString())
            }
        }
    }

    override fun showFilter(show: Boolean, status:Int) {
        binding.contentSearch.visibility = if(show) View.VISIBLE else View.GONE

        binding.searchField.hint = when(status){
            Task.TODO -> getString(R.string.search_tasks_todos)
            Task.IN_PROGRESS -> getString(R.string.search_tasks_in_progress)
            Task.DONE -> getString(R.string.search_tasks_done)
            else -> ""
        }

    }


}