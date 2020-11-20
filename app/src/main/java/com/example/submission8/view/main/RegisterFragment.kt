package com.example.submission8.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.submission8.R
import com.example.submission8.database.model.User
import com.example.submission8.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var navController : NavController
    private lateinit var viewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        attachObserve()
        emailValidation()
        viewModel.responseToast
        btn_SignUp.setOnClickListener(this)
        tv_register_back_login.setOnClickListener(this)
    }



    private fun attachObserve() {
        viewModel.responseRegister.observe(viewLifecycleOwner, Observer { showRegister(it) })
        viewModel.responseExist.observe(viewLifecycleOwner, Observer { showExist(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { shownotExist() })
        viewModel.responseToast.observe(viewLifecycleOwner, Observer { showToast(it) })
    }

    private fun showRegister(it: Unit?) {
        val bundle = bundleOf("name" to et_register_name.text.toString(), "email" to et_register_email.text.toString())
        navController.navigate(R.id.action_register_Fragment_to_resultFragment, bundle)
    }

    private fun showToast(it: String?) {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showExist(it: User?) {
        Toast.makeText(context, "Username Atau Email Sudah Terdaftar", Toast.LENGTH_SHORT).show()
    }

    private fun shownotExist() {
        viewModel.insertUserView(User(null, et_register_name.text.toString(), et_register_email.text.toString(), et_register_password.text.toString()))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_SignUp -> validation(et_register_email.text.toString(), et_register_name.text.toString())
            R.id.tv_register_back_login -> navController.navigate(R.id.action_register_Fragment_to_login_Fragment)
        }
    }



    private fun validation(email : String, name : String){
        viewModel.getUserExistView(email, name, et_register_password.text.toString(), et_confirm_password.text.toString())
    }

    private fun emailValidation() {
        et_register_email.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_register_email.text.toString()).matches())
                    btn_SignUp.isEnabled = true
                else{
                    btn_SignUp.isEnabled = false
                    et_register_email.setError("Email Tidak Valid")
                }
            }
        })
    }

}
