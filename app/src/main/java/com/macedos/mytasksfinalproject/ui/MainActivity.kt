package com.macedos.mytasksfinalproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.macedos.mytasksfinalproject.R
import com.macedos.mytasksfinalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Variables
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance
        firebaseAnalytics = Firebase.analytics

        //TabLayout Configuration
        val tabLayout = binding.tabLayout

        //ViewPager Configuration
        val viewPager = binding.myViewPager
    }
}