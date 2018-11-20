package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.base_recyclerview_fragment.view.*


class ChoreListViewPagerFragment : Fragment() {


    companion object {
        fun newInstance(): ChoreListViewPagerFragment {
            return ChoreListViewPagerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_recyclerview_fragment, container, false)
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.recyclerView.adapter = HomeRecyclerViewAdapter(createDemoList("Demo task "), context!!)
        return view
    }

    fun createDemoList(demoString: String): ArrayList<String> {
        val list: ArrayList<String> = arrayListOf()
        for (i in 1..20) {
            list.add("$demoString $i")
        }
        return list
    }

}