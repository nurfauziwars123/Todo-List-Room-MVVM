package com.example.submission8.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission8.database.TodoDAO
import com.example.submission8.database.model.Todo
import com.example.submission8.repository.Repository

class TodoViewModel(applicantion : Application) : AndroidViewModel(applicantion){
    val repository = Repository(applicantion.applicationContext)

    var responseToast       = MutableLiveData<String>()
    var responseUpdate      = MutableLiveData<Unit>()
    var responseDelete      = MutableLiveData<Unit>()
    var responseInput       = MutableLiveData<Unit>()
    var responseShowTodo    = MutableLiveData<List<Todo>>()
    var issError            = MutableLiveData<Throwable>()

    fun showTodoView(){
        repository.showTodo({
            responseShowTodo.value = it
        },{
            issError.value = it
        })
    }

    fun inputTodoView(item : Todo){
        if (item?.title!!.isEmpty() || item?.deskription!!.isEmpty() || item.date!!.isEmpty()){
            responseToast.value = "Lengkapi semua isian anda ! "
        }else{
            repository.inputTodo(item,{
                responseInput.value = it
            },{
                issError.value = it
            })
        }
        }


    fun deleteTodoView(item : Todo){
        repository.deleteTodo(item, {
            responseDelete.value = it
        },{
            issError.value = it
        })
    }

    fun updateTodoView(item : Todo){
        if (item?.title!!.isEmpty() || item?.deskription!!.isEmpty() || item.date!!.isEmpty()){
            responseToast.value = "Lengkapi semua isian anda ! "
        }else{
            repository.updateTodo(item, {
                responseUpdate.value = it
            },{
                issError.value = it
            })
        }
        }

}