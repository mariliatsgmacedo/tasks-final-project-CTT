package com.macedos.mytasksfinalproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.macedos.mytasksfinalproject.BR
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.data.AppDataBase
import com.macedos.mytasksfinalproject.data.model.Task
import com.macedos.mytasksfinalproject.databinding.ActivityMainBinding
import com.macedos.mytasksfinalproject.ui.base.BaseActivity
import com.macedos.mytasksfinalproject.utils.MyViewPagerAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    //Variables
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun setViewConfiguration(): Triple<Int, Int, Class<MainViewModel>> {
        return Triple(R.layout.activity_main,BR.viewModel,MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the FirebaseAnalytics instance
        firebaseAnalytics = Firebase.analytics

//
//       val  db = AppDataBase.getDatabase()
//        db.getTaskDao().findByStatus(Task.DONE)

        //TabLayout Configuration
        val tabLayout = binding.tabLayout

        //ViewPager Configuration
        val viewPager = binding.myViewPager

        viewPager.adapter = MyViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){  tab, position ->
           tab.text = when(position){
               0 -> getString(R.string.todo_tabbar)
               1 -> getString(R.string.in_progress_tabbar)
               else -> getString(R.string.done_tabbar)
           }
        }.attach()
    }


}