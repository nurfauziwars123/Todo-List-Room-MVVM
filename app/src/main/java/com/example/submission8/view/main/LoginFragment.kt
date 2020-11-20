package com.example.submission8.view.main

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
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.submission8.R
import com.example.submission8.database.model.User
import com.example.submission8.view.dasboard.DasboardActivity
import com.example.submission8.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener{

    private lateinit var navController : NavController
    private lateinit var viewModel : UserViewModel
    private lateinit var sharePref: SharedPreferences

    companion object{
    val NAME = "LOGIN"
    val LOGIN_SESSION = "login_session"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        sharePref = requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE)

        viewModel.getUserView()
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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = Navigation.findNavController(view)

        tv_Signup.setOnClickListener(this)
        btn_SignIn.setOnClickListener(this)

        viewModel.responseLogin.observe(this, Observer { showUser(it) })
        viewModel.responseEmail.observe(this, Observer { showLogin(it) })
        viewModel.responseToast.observe(this, Observer { showToast(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }

    private fun showToast(it: String?) {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
    }


    private fun showError(it: Throwable?) {
        Toast.makeText(context, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
    }

    private fun showUser(it: List<User>?) {
        if (sharePref.getInt(LOGIN_SESSION, 0) == 1){
            val bundle = bundleOf("name" to it?.get(0)?.name)
            navController.navigate(R.id.action_login_Fragment_to_dasboardActivity, bundle)
            activity?.finish()
        }
    }

    private fun showLogin(it : User) {
        sharePref.edit().putInt(LOGIN_SESSION, 1).commit()
        Toast.makeText(context, "berhasil login", Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("name" to it.name)
        navController.navigate(R.id.action_login_Fragment_to_dasboardActivity, bundle)
        activity?.finish()
    }

    private fun validation(email : String, password : String){
            viewModel.getUserEmailView(email, password)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_Signup  -> navController.navigate(R.id.action_login_Fragment_to_register_Fragment)
            R.id.btn_SignIn -> {
                validation(et_login_email.text.toString(), et_login_password.text.toString())
            }
        }
    }
}
