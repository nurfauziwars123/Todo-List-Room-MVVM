package com.example.submission8.view.main


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.submission8.R
import kotlinx.android.synthetic.main.fragment_result.*

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var getName    : String
    private lateinit var getEmail   : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
        }

        getName     = arguments?.getString("name") ?:  ""
        getEmail    = arguments?.getString("email") ?: ""

        et_result_name.setText(getName)
        et_result_email.setText(getEmail)

        navController = Navigation.findNavController(view)
        btn_Finish.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_Finish -> navController.navigate(R.id.action_resultFragment_to_login_Fragment2)
        }
    }

}
