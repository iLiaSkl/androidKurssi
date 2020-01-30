package com.skliaruk.harjoitus1

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val todos: Array<TodoModel>): RecyclerView.Adapter<RecyclerAdapter.TodoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.TodoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return TodoHolder(inflatedView)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: RecyclerAdapter.TodoHolder, position: Int) {
        val item = todos[position]
        holder.bindItem(item)

    }

    class TodoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var todo: TodoModel? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindItem(todo: TodoModel){
            this.todo = todo
            view.textViewId.text = todo.id.toString()
            view.textViewTitle.text = todo.title
        }
        override fun onClick(v: View?) {
            Log.d("RecyclerView" , "CLICK!")
        }
    }

}