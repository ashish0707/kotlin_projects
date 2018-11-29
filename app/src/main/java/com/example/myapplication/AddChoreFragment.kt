package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.android.synthetic.main.add_chore_fragment.*
import kotlinx.android.synthetic.main.add_chore_fragment.view.*
import kotlinx.android.synthetic.main.initial_screen.view.*


class AddChoreFragment : Fragment() {


    companion object {
        fun newInstance(): AddChoreFragment {
            return AddChoreFragment()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var fragmentView = inflater.inflate(R.layout.add_chore_fragment, container, false)
        val spinner = fragmentView.assigned_to_spinner
        val dateButton = fragmentView.assign_button
        setUpSpinner(spinner)
        setUpDate(dateButton)
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpDate(button: Button) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        button.setOnClickListener {
            val dpd = DatePickerDialog(this.context, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                due_date_tv.setText("" + (mMonth + 1) + "/" + mDay + "/" + mYear)
            }, year, month, day)
            dpd.show()
        }
    }


}
