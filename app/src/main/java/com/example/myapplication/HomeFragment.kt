package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.homefragment.view.*


class HomeFragment : Fragment() {

    private var fragmentView: View? = null

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.homefragment, container, false)
            fragmentView?.tabLayout?.setupWithViewPager(fragmentView?.viewPager)
            setUpViewPager(fragmentView?.viewPager, fragmentManager)
        }
        return fragmentView
    }

    private fun setUpViewPager(viewPager: ViewPager?, fragmentManager: FragmentManager?) {
        val viewPagerAdapter = HomeViewPagerAdapter(fragmentManager!!)
        viewPagerAdapter.addFrag(ChoreListViewPagerFragment.newInstance(), "All Chores")
        viewPagerAdapter.addFrag(ChoreListViewPagerFragment.newInstance(), "My Chores")
        viewPager?.adapter = viewPagerAdapter
    }



}