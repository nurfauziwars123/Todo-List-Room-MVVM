package com.example.submission8.view.dasboard


import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.submission8.R
import com.example.submission8.database.model.Todo
import com.example.submission8.view.adapter.TodoAdapter
import com.example.submission8.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.alert_add.*
import kotlinx.android.synthetic.main.alert_add.view.*
import kotlinx.android.synthetic.main.alert_detail.*
import kotlinx.android.synthetic.main.alert_detail.view.*
import kotlinx.android.synthetic.main.fragment_todo.*

class TodoFragment : Fragment() {

    private lateinit var navController : NavController
    private lateinit var viewModel : TodoViewModel
    private lateinit var dialogView : Dialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btn_fab_add.setOnClickListener{
            showDialog()
        }
        attachObserver()
        viewModel.showTodoView()

    }



    private fun attachObserver() {
        viewModel.responseToast.observe(viewLifecycleOwner, Observer { showToast(it) })
        viewModel.responseDelete.observe(viewLifecycleOwner, Observer { showDelete() })
        viewModel.responseInput.observe(viewLifecycleOwner, Observer { showInsert(it) })
        viewModel.responseUpdate.observe(viewLifecycleOwner, Observer { showUpdate(it) })
        viewModel.responseShowTodo.observe(viewLifecycleOwner , Observer {showTodo(it) })
        viewModel.issError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showToast(it: String?) {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showDelete() {
        Toast.makeText(context, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()
        viewModel.showTodoView()
    }

    private fun showInsert(it: Unit?) {
        Toast.makeText(context, "Berhasil input Data", Toast.LENGTH_SHORT).show()
        dialogView.dismiss()
        viewModel.showTodoView()
    }

    private fun showUpdate(it: Unit?) {
        Toast.makeText(context, "berhasil Update Data", Toast.LENGTH_SHORT).show()
        dialogView.dismiss()
        viewModel.showTodoView()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showTodo(it: List<Todo>?) {
        rv_todo.adapter = TodoAdapter(it, object : TodoAdapter.OnclickListener{
            override fun edit(item: Todo?) {
                showDialogUpdate(item)
            }

            override fun delete(item: Todo?) {
                AlertDialog.Builder(context).apply {
                    setTitle("Hapus data")
                    setMessage("Yakin Hapus Data?")
                    setPositiveButton("Ya") { dialog, which ->
                        viewModel.deleteTodoView(item!!)
                        dialog.dismiss()

                    }
                    setNegativeButton("Tidak") { dialog, which ->
                        dialog.dismiss()
                    }
                }.show()
            }

            override fun detail(item: Todo?) {
                    Toast.makeText(context, "berhasil Detail", Toast.LENGTH_SHORT).show()
                showDialogDetail(item!!)
            }
        })

    }

    private fun showDialogDetail(item : Todo){

        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.alert_detail, null)
        dialog.setView(view)
        dialogView = dialog.create()
        dialogView = dialog.show()

        view.tv_detail_title.text = item.title.toString()
        view.tv_detail_deskripsi.text = item.deskription.toString()
        view.tv_detail_time.text = item.date.toString()

        view.btn_detail_close.setOnClickListener{
         dialogView.dismiss()
        }
    }

    private fun showDialog(){

        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.alert_add, null)
        dialog.setView(view)
        dialogView = dialog.create()
        dialogView = dialog.show()

        view.btn_add_Save.setOnClickListener{
                viewModel.inputTodoView(Todo(
                    null,
                    view.et_add_name.text.toString(),
                    view.et_add_deskription.text.toString(),
                    view.et_date_time.text.toString())
                )
                viewModel.showTodoView()
        }
    }

    private fun showDialogUpdate(item : Todo?){
        val dialog = AlertDialog.Builder(context)
        val view   = layoutInflater.inflate(R.layout.alert_add, null)
        dialog.setView(view)
        dialogView = dialog.create()
        dialogView = dialog.show()

        view.et_add_name.setText(item?.title)
        view.et_add_deskription.setText(item?.deskription)
        view.et_date_time.setText(item?.date)
        view.btn_add_Save.setText("Update")

        view.btn_add_Save.setOnClickListener{
                viewModel.updateTodoView(Todo(item?.id,
                    view.et_add_name.text.toString(),
                    view.et_add_deskription.text.toString(),
                    view.et_date_time.text.toString()))
        }
    }
}
