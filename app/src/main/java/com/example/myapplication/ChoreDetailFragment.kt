package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.pojo.ChorePojo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.chore_detail_fragment.view.*


class ChoreDetailFragment : BaseFragment() {

    private var chorePojo: ChorePojo? = null
    private var navController: NavController? = null

    companion object {
        fun newInstance(): ChoreDetailFragment {
            return ChoreDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(R.layout.chore_detail_fragment, container, false)
        val spinner = fragmentView.assigned_spinner
        chorePojo = ChoreDetailFragmentArgs.fromBundle(arguments).pojo
        fragmentView.chorename.text = chorePojo?.name
        fragmentView.about_to_tv.setText(chorePojo?.about)
        fragmentView.time_to_complete_et.setText(chorePojo?.time_to_complete.toString())
        fragmentView.feedback_et.setText(chorePojo?.feedback)
        fragmentView.mark_complete.setOnClickListener { onCompleteChoreButtonPressed() }
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setUpSpinner(spinner)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun setUpSpinner(spinner: Spinner?) {
        ArrayAdapter.createFromResource(activity, R.array.roommate_array, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    spinner?.adapter = adapter
                    spinner?.setSelection(adapter.getPosition(chorePojo?.assignedTo));
                }
    }

    private fun onCompleteChoreButtonPressed() {
        val database = FirebaseDatabase.getInstance().reference
        chorePojo?.completed = true
        database.child("task_list")
                .child(chorePojo?.id ?: "")
                .updateChildren(chorePojo?.toMap() ?: ChorePojo().toMap())
        Toast.makeText(context, "Marked Complete Successfully", Toast.LENGTH_LONG).show()
        navController?.navigate(ChoreDetailFragmentDirections.actionGlobalHomeFragment())
    }

    override fun onBackPressed(): Boolean {
        navController?.navigate(ChoreDetailFragmentDirections.actionGlobalHomeFragment())
        return true
    }
}