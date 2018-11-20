package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.login_screen.view.*


class LoginFragment :Fragment(){


    companion object{
        fun newInstance():LoginFragment{
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.login_screen,container,false)
        view.login_button.setOnClickListener { button ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}