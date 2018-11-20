package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.initial_screen.view.*


class InitialFragment :Fragment(){


    companion object{
        fun newInstance():InitialFragment{
            return InitialFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var fragmentView = inflater.inflate(R.layout.initial_screen,container,false)
        fragmentView.sign_with_email.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_initialFragment_to_loginFragment2)
        }

        return fragmentView
    }


}