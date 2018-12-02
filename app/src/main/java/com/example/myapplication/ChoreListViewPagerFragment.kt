package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.pojo.ChorePojo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.base_recyclerview_fragment.view.*


class ChoreListViewPagerFragment : Fragment() {

    lateinit var mAdapter: HomeRecyclerViewAdapter
    val allChoreList : ArrayList<ChorePojo?> = arrayListOf()
    companion object {
        fun newInstance(): ChoreListViewPagerFragment {
            return ChoreListViewPagerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_recyclerview_fragment, container, false)
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = HomeRecyclerViewAdapter(allChoreList, context!!)
        view.recyclerView.adapter = mAdapter
        return view
    }

    private lateinit var database: DatabaseReference

    override fun onResume() {
        super.onResume()
        database = FirebaseDatabase.getInstance().reference
        database.child("task_list").addValueEventListener(getTaskListListener())
    }

    private fun getTaskListListener(): ValueEventListener {
        return object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                allChoreList.clear()
                val items = dataSnapshot.children.iterator()
                while(items.hasNext()) {
                    val chorePojo = items.next().getValue(ChorePojo::class.java)
                    allChoreList.add(chorePojo)
                }
                mAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
    }
}