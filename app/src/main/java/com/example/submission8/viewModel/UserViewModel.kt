package com.example.submission8.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission8.database.model.User
import com.example.submission8.repository.Repository

class UserViewModel(application : Application) : AndroidViewModel(application) {
    val repository = Repository(application.applicationContext)

    var responseLogin       = MutableLiveData<List<User>>()
    var isError             = MutableLiveData<Throwable>()
    var responseRegister    = MutableLiveData<Unit>()
    var responseEmail       = MutableLiveData<User>()
    var responseExist       = MutableLiveData<User>()
    var responseToast       = MutableLiveData<String>()




    fun getUserView(){
        repository.getUser({
            responseLogin.value = it
        },{
            isError.value = it
        })
    }

    fun getUserEmailView(email : String, password : String) : LiveData<User>{
        val user = MutableLiveData<User>()

        repository.getUserEmail(email, {
            if (password == it.password){
                responseEmail.value = it
            }else{
                responseToast.value = "Username atau Password Salah !"
            }
        },{
            isError.value = it
        })

        return user
    }

    fun getUserExistView(email : String, name : String, password :String, confPassword : String) : LiveData<User>{

        val user = MutableLiveData<User>()
        if (password == confPassword){
            repository.getUserExist(email, name, {
                responseExist.value = it
            },{
                isError.value = it
            })
        }else{
            responseToast.value = "password ngga sama hey !"
        }

        return user
    }

    fun insertUserView(item: User) {
        if (item?.email!!.isEmpty() || item?.name!!.isEmpty() || item?.password!!.isEmpty()) {
            responseToast.value = "harap Lengkapi Semua Form !"
        }else{
            repository.insetUser(item,
                {
                    responseRegister.value = it
                }, {
                    isError.value = it
                })
        }

    }
}