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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.todos.api.TodoRetrofit;
import com.example.recyclerviewexam.todos.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListFragment extends Fragment {

    private OnUserClickListener mListener;
    private ProgressBar mProgressBar;

    public UserListFragment() {
    }

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        UserRecyclerAdapter adapter = new UserRecyclerAdapter(mListener);
        recyclerView.setAdapter(adapter);

        TodoRetrofit.create().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mProgressBar.setVisibility(View.GONE);
                adapter.setItems(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserClickListener) {
            mListener = (OnUserClickListener) context;
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

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    static class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {
        private final OnUserClickListener mListener;
        private List<User> mItems = new ArrayList<>();

        public UserRecyclerAdapter(OnUserClickListener listener) {
            mListener = listener;
        }

        public void setItems(List<User> items) {
            mItems = items;
        }


        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            UserViewHolder holder = new UserViewHolder(view);
            view.setOnClickListener(v -> {
                User user = mItems.get(holder.getAdapterPosition());
                mListener.onUserClick(user);
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
            User user = mItems.get(position);
            userViewHolder.usernameTextView.setText(user.getUsername());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        static class UserViewHolder extends RecyclerView.ViewHolder {
            public TextView usernameTextView;

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);

                usernameTextView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
