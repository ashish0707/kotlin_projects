package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.login_screen.view.*


class LoginFragment : Fragment(), ValueEventListener {


    private var fragmentView: View? = null
    private var loginButton: Button? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private lateinit var mDatabase: DatabaseReference

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.login_screen, container, false)
            email = fragmentView?.login_id_input
            password = fragmentView?.password_input
            loginButton = fragmentView?.login_button
            mDatabase = FirebaseDatabase.getInstance().reference

        }
        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        loginButton?.setOnClickListener {
            mDatabase.child("users").addListenerForSingleValueEvent(this) }
    }

    override fun onCancelled(p0: DatabaseError) {
        Toast.makeText(context, "on Cancelled", Toast.LENGTH_LONG).show()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        val isEmailPresent = dataSnapshot.child(email?.text.toString()).exists()
        if(isEmailPresent) {
            val isPasswordCorrect = dataSnapshot.child(email?.text.toString()).value?.equals(password?.text.toString())
                    ?: false
            if (isPasswordCorrect){
                saveUserInfo(email?.text.toString(), password?.text.toString())
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(context, MainActivity::class.java))
            }
        }
        else
            Toast.makeText(context, "Incorrect Username or password", Toast.LENGTH_LONG).show()
    }

    private fun saveUserInfo(username: String, password: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        with (sharedPref.edit()) {
            Log.d("username", username)
            putString("username", username)
            putString("password", password)
            apply()
        }
    }
}