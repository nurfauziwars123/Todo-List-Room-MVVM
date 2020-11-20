package com.example.submission8.view.dasboard


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.submission8.R
import com.example.submission8.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.prefs.AbstractPreferences


class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var navController : NavController
    private lateinit var sharepref : SharedPreferences
    var getName : String? = null
    companion object{
        val NAME = "LOGIN"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            AlertDialog.Builder(context).apply {
                setTitle("Konfirmasi Keluar")
                setMessage("Yakin Mau Keluar?")
                setPositiveButton("Ya"){
                        dialog, which -> activity?.finish()
                }

                setNegativeButton("Tidak"){
                        dialog, which ->  dialog.dismiss()
                }

            }.show()
        }
        getName = requireActivity().intent.getStringExtra("name")
        sharepref = requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_dashboard_welcome.text = "Welcome Back, $getName Have A Nice Day!"
        navController = Navigation.findNavController(view)
        btn_home_logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_home_logout -> {
                AlertDialog.Builder(context).apply {
                    setTitle("Konfirmasi Logout")
                    setMessage("Yakin Mau Logout?")
                    setPositiveButton("Ya") { dialog, which ->
                        sharepref.edit().clear().commit()
                        navController.navigate(R.id.action_homeFragment_to_mainActivity)
                        activity?.finish()
                        Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Tidak"){dialog, which ->
                        dialog.dismiss()
                    }
                }.show()

            }
        }

    }

}
