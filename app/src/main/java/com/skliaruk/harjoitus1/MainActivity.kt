package com.skliaruk.harjoitus1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : RecyclerAdapter
    private lateinit var todo: ArrayList<TodoModel>
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        createRequest()

    }

    @SuppressLint("SetTextI18n")
    private fun createRequest() {



        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/todos"
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var todos = Gson().fromJson(response, Array<TodoModel>::class.java)
                var todo = todos
                adapter = RecyclerAdapter(todo)
                recyclerView.adapter = adapter

                for (t in todos)
                {
                    Log.d("Todo", t.id.toString() + ", " + t.title)
                }
                Log.d("Response", "Response is: ${response.substring(0, 500)}")
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${response.substring(0, 500)}"

            },
            Response.ErrorListener { Log.d("Error", "Error") })
        //textView.text = "That didn't work!"
        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}
