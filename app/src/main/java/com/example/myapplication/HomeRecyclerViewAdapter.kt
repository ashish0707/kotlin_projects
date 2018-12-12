package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.myapplication.pojo.ChorePojo
import kotlinx.android.synthetic.main.chorelist_viewpager_list_child.view.*

class HomeRecyclerViewAdapter(private val items: ArrayList<ChorePojo?>, private val context: Context)
    : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position]?.name

        holder.title.setOnClickListener { view ->
            val b = Bundle()
            b.putParcelable("pojo", items[position])
            val directions =
                    HomeFragmentDirections.ActionHomeFragmentToChoreDetailFragment()
            directions.setPojo(items[position])
            view.findNavController().navigate(directions)
        }
        if (items[position]?.completed == true)
            holder.linearlayout.setBackgroundColor(Color.GRAY)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.chorelist_viewpager_list_child, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.cardTitle
        val linearlayout : LinearLayout = view.linearlayout
    }
}

