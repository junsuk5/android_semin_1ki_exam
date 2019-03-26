package com.example.recyclerviewexam.todos.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.todos.api.TodoRetrofit;
import com.example.recyclerviewexam.todos.models.Todo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoListFragment extends Fragment {
    private static final String ARG_Todo_ID = "param1";
    private ProgressBar mProgressBar;

    private int mTodoId;

    private OnTodoClickListener mListener;

    public TodoListFragment() {
        // Required empty public constructor
    }

    public static TodoListFragment newInstance(int TodoId) {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_Todo_ID, TodoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTodoId = getArguments().getInt(ARG_Todo_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TodoListFragment.TodoRecyclerAdapter adapter = new TodoListFragment.TodoRecyclerAdapter(mListener);
        recyclerView.setAdapter(adapter);

        TodoRetrofit.create().getTodos(mTodoId).enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                mProgressBar.setVisibility(View.GONE);
                adapter.setItems(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTodoClickListener) {
            mListener = (OnTodoClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnTodoClickListener {
        void onTodoClick(Todo todo);
    }

    static class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoListFragment.TodoRecyclerAdapter.TodoViewHolder> {
        private final OnTodoClickListener mListener;
        private List<Todo> mItems = new ArrayList<>();

        public TodoRecyclerAdapter(OnTodoClickListener listener) {
            mListener = listener;
        }

        public void setItems(List<Todo> items) {
            mItems = items;
        }


        @NonNull
        @Override
        public TodoListFragment.TodoRecyclerAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_todo, viewGroup, false);
            TodoListFragment.TodoRecyclerAdapter.TodoViewHolder holder = new TodoListFragment.TodoRecyclerAdapter.TodoViewHolder(view);
            view.setOnClickListener(v -> {
                Todo Todo = mItems.get(holder.getAdapterPosition());
                mListener.onTodoClick(Todo);
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull TodoListFragment.TodoRecyclerAdapter.TodoViewHolder TodoViewHolder, int position) {
            Todo todo = mItems.get(position);
            TodoViewHolder.titleTextView.setText(todo.getTitle());
            TodoViewHolder.checkBox.setChecked(todo.isCompleted());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        static class TodoViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public CheckBox checkBox;

            public TodoViewHolder(@NonNull View itemView) {
                super(itemView);

                titleTextView = itemView.findViewById(R.id.title);
                checkBox = itemView.findViewById(R.id.check);
            }
        }
    }
}
