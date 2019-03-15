package com.example.recyclerviewexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExamRecyclerAdapter extends RecyclerView.Adapter<ExamRecyclerAdapter.ContactViewHolder> {

    // 인터페이스 ---------------------------------------
    public interface MyOnClickListener {
        void onSelected(Class clazz);
    }

    private MyOnClickListener mListener;

    public void setMyOnClickListener(MyOnClickListener listener) {
        mListener = listener;
    }
    // 인터페이스 ---------------------------------------

    private List<Class> mItems = new ArrayList<>();

    public void setItems(List<Class> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_class, viewGroup, false);
        final ContactViewHolder viewHolder = new ContactViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Class aClass = mItems.get(viewHolder.getAdapterPosition());
                    mListener.onSelected(aClass);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        Class aClass = mItems.get(i);

        contactViewHolder.nameTextView.setText(aClass.getSimpleName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // ViewHolder
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
