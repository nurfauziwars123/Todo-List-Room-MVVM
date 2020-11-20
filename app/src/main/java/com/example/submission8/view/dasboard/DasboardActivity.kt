package com.example.submission8.view.dasboard

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.submission8.R
import com.example.submission8.database.model.Todo
import com.example.submission8.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_dasboard.*
import kotlinx.android.synthetic.main.alert_add.view.*

class DasboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dasboard)

        val navigationUI = Navigation.findNavController(this, R.id.nav_host_home)
        NavigationUI.setupWithNavController(bottom_navigation, navigationUI)

    }
}
