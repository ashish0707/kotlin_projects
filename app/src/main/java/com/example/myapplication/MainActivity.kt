package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var backStackEntryCount : Int = 0
    lateinit var navController: NavController
    lateinit var navHostFragment: Fragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navController.navigate(R.id.action_global_homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                navController.navigate(R.id.action_global_AddChoreFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                navController.navigate(R.id.action_global_NotificationFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController  = findNavController(R.id.fragmentContainer)
        val nav = findViewById<BottomNavigationView>(R.id.navigation)
        nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        backStackEntryCount= navHostFragment.childFragmentManager.fragments?.size ?: 0

    }


    override fun onBackPressed() {
        backStackEntryCount= navHostFragment.childFragmentManager.backStackEntryCount
        if(backStackEntryCount > 1)
            super.onBackPressed()
        else
            finish()
    }
}
