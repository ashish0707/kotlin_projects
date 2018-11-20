package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import 	android.support.v7.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.base_recyclerview_fragment.view.*


class NotificationFragment : Fragment() {


    companion object {
        fun newInstance(): NotificationFragment {
            return NotificationFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_recyclerview_fragment, container, false)
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.recyclerView.adapter = NotificationRecyclerViewAdapter(createDemoList("Notification  "), context!!)
        return view
    }

    private fun createDemoList(demoString: String): ArrayList<String> {
        val list: ArrayList<String> = arrayListOf()
        for (i in 1..20) {
            list.add("$demoString $i")
        }
        return list
    }

}