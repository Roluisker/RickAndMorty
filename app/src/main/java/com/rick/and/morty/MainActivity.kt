package com.rick.and.morty

import android.os.Bundle
import com.rick.and.morty.core.BaseActivity
import com.rick.and.morty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)
    }

}