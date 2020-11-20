package com.example.submission8.view.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission8.R
import com.example.submission8.database.model.Todo
import com.example.submission8.database.model.User
import kotlinx.android.synthetic.main.row_todo.view.*

class TodoAdapter (val data : List<Todo>?, val itemClick : OnclickListener) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item!!)
    }

    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun bind(item: Todo) {
            view.et_adapter_title.setText(item?.title)

            view.btn_adapter_edit.setOnClickListener{
                itemClick.edit(item)
            }

            view.btn_adpter_delete.setOnClickListener{
                itemClick.delete(item)
            }

            view.setOnClickListener{
                itemClick.detail(item)
            }
        }

    }


    interface OnclickListener{
        fun edit(item : Todo?)
        fun delete(item : Todo?)
        fun detail(item : Todo?)

    }
}
