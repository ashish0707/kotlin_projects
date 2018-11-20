package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.add_chore_fragment.view.*
import kotlinx.android.synthetic.main.initial_screen.view.*


class AddChoreFragment : Fragment() {


    companion object {
        fun newInstance(): AddChoreFragment {
            return AddChoreFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var fragmentView = inflater.inflate(R.layout.add_chore_fragment, container, false)
        val spinner = fragmentView.assigned_to_spinner
        setUpSpinner(spinner)
        return fragmentView
    }

    private fun setUpSpinner(spinner: Spinner?) {
        ArrayAdapter.createFromResource(activity, R.array.planets_array, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                            // Specify the layout to use when the list of choices appears
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            // Apply the adapter to the spinner
                            spinner?.adapter = adapter
                          }

    }

}