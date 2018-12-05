package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.pojo.ChorePojo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.add_chore_fragment.*
import kotlinx.android.synthetic.main.add_chore_fragment.view.*


class AddChoreFragment : BaseFragment() {

    private lateinit var name: EditText
    private lateinit var date: TextView
    private lateinit var about: EditText
    private lateinit var spinner: Spinner
    private lateinit var database: DatabaseReference
    private lateinit var navController: NavController

    companion object {
        fun newInstance(): AddChoreFragment {
            return AddChoreFragment()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(R.layout.add_chore_fragment, container, false)
        spinner = fragmentView.assigned_spinner
        setUpSpinner(spinner)
        name = fragmentView.chorename
        date = fragmentView.due_date_tv
        about = fragmentView.about_to_tv
        fragmentView.due_date_tv.text = getCurrentDate()
        val handle = getDatePickerDialogHandle()
        fragmentView.assign_button.let { it -> it.setOnClickListener { handle.show() } }
        fragmentView.create.setOnClickListener { onCreateChoreButtonPressed() }
        return fragmentView
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = " ${month + 1}-$day-$year"
        return date
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun onCreateChoreButtonPressed() {
        database = FirebaseDatabase.getInstance().reference
        val databaseReference = database.child("task_list").push()
        val id = "${databaseReference.key}"
        val pojo = ChorePojo(id, name.text.toString(), date.text.toString(), spinner.selectedItem.toString(), about.text.toString())
        databaseReference.setValue(pojo.toMap())
        Toast.makeText(context, "Chore saved successfully", Toast.LENGTH_LONG).show()
        date.text = ""
        about.text.clear()
        name.text.clear()
        navController.navigate(AddChoreFragmentDirections.actionGlobalHomeFragment())

    }

    private fun setUpSpinner(spinner: Spinner?) {
        ArrayAdapter.createFromResource(activity, R.array.roommate_array, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    spinner?.adapter = adapter
                }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDatePickerDialogHandle(): DatePickerDialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(this.context,
                { _, mYear, mMonth, mDay ->
                    val date = " ${mMonth + 1}-$mDay-$mYear"
                    due_date_tv.text = date
                },
                year,
                month,
                day)

    }

    override fun onBackPressed(): Boolean {
        navController.navigate(AddChoreFragmentDirections.actionGlobalHomeFragment())
        return true
    }


}
