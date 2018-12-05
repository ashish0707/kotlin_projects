package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.pojo.ChorePojo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.base_recyclerview_fragment.view.*


class ChoreListViewPagerFragment : BaseFragment() {

    override fun onBackPressed(): Boolean {
        return false
    }

    lateinit var mAdapter: HomeRecyclerViewAdapter
    var shouldFilterChore: Boolean = false
    val allChoreList: ArrayList<ChorePojo?> = arrayListOf()

    companion object {
        fun newInstance(shouldFilterChore: Boolean = false): ChoreListViewPagerFragment {
            val b = Bundle()
            b.putBoolean("shouldFilterChore", shouldFilterChore)
            val frag = ChoreListViewPagerFragment()
            frag.arguments = b
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_recyclerview_fragment, container, false)
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = HomeRecyclerViewAdapter(allChoreList, context!!)
        view.recyclerView.adapter = mAdapter
        shouldFilterChore = arguments!!.getBoolean("shouldFilterChore", false)
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
                val tempList = mutableListOf<ChorePojo?>()
                val items = dataSnapshot.children.iterator()
                while (items.hasNext()) {
                    val chorePojo = items.next().getValue(ChorePojo::class.java)
                    tempList.add(chorePojo)
                }
                if (shouldFilterChore) {
                    val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
                    val username = sharedPref.getString("username", "john")
                    val l = tempList.filter {
                        it?.assignedTo.equals(username)
                    }
                    allChoreList.addAll(l)
                } else
                    allChoreList.addAll(tempList)
                mAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
    }
}