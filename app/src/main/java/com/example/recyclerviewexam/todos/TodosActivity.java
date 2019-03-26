package com.example.recyclerviewexam.todos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.todos.models.Todo;
import com.example.recyclerviewexam.todos.models.User;
import com.example.recyclerviewexam.todos.ui.TodoListFragment;
import com.example.recyclerviewexam.todos.ui.UserListFragment;

public class TodosActivity extends AppCompatActivity
        implements UserListFragment.OnUserClickListener,
        TodoListFragment.OnTodoClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UserListFragment())
                    .commit();
        }
    }

    @Override
    public void onUserClick(User user) {
        TodoListFragment fragment = TodoListFragment.newInstance(user.getId());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTodoClick(Todo todo) {

    }
}
