package com.example.myapplication

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment(){

    abstract fun onBackPressed(): Boolean
}
