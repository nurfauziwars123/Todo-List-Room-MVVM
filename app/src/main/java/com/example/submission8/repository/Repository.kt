package com.example.submission8.repository

import android.annotation.SuppressLint
import android.content.Context
import android.database.DatabaseErrorHandler
import android.transition.Scene
import android.util.AndroidException
import com.example.submission8.database.DatabaseConfig
import com.example.submission8.database.model.Todo
import com.example.submission8.database.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.util.logging.Handler


class Repository(val context : Context) {

    private var databaseConfig : DatabaseConfig

    init {
        databaseConfig = DatabaseConfig.getInstance(context)!!
    }

    @SuppressLint("CheckResult")
    fun showTodo(responseHandler: (List<Todo>) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.todoDao()?.getTodo()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun inputTodo(item : Todo, responseHandler: (Unit) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.todoDao().insertTodo(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteTodo(item : Todo, responseHandler: (Unit) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.todoDao().deleteTodo(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateTodo(item : Todo, responseHandler: (Unit) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.todoDao().updateTodo(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun getUser(responseHandler: (List<User>) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.userDao().getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })

    }

    @SuppressLint("CheckResult")
    fun getUserEmail(email : String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.userDao().getDataEmail(email) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun getUserExist(email : String, name : String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.userDao().getDataUserExist(email, name) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun insetUser(item : User, responseHandler: (Unit) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { databaseConfig.userDao().insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }
}