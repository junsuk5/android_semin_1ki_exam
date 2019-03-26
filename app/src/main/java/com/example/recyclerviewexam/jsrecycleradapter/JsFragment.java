package com.example.recyclerviewexam.jsrecycleradapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewexam.JsRecyclerAdapter;
import com.example.recyclerviewexam.R;

import java.util.ArrayList;
import java.util.List;

public class JsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_js, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<JsItem> items = new ArrayList<>();
        items.add(new JsItem("1"));
        items.add(new JsItem("2"));
        items.add(new JsItem("3"));
        items.add(new JsItem("4"));
        items.add(new JsItem("5"));
        items.add(new JsItem("6"));
        items.add(new JsItem("7"));
        items.add(new JsItem("8"));

        recyclerView.setAdapter(new JsRecyclerAdapter<JsItem, JsViewHolder>(items) {

            @NonNull
            @Override
            public JsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                return new JsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull JsViewHolder holder, int position, @NonNull JsItem model) {
                holder.title.setText(model.getTitle());
            }
        });
    }

    static class JsViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public JsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
        }
    }
}
