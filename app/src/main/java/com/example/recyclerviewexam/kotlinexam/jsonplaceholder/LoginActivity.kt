package com.example.recyclerviewexam.kotlinexam.jsonplaceholder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerviewexam.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val _api = JsonPlaceHolderApi.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val adapter = UserRecyclerAdapter()
        recyclerView.adapter = adapter

        _api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                toast(t.localizedMessage)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body().let {
                    adapter.items = it!!
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}

class UserRecyclerAdapter(var items: List<User> = ArrayList()) : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): UserViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = items[position].name
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(android.R.id.text1)
    }
}
