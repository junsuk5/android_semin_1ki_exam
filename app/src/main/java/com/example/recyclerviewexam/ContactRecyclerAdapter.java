package com.example.recyclerviewexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewexam.models.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder> {

    // 인터페이스 ---------------------------------------
    public interface MyOnContactClickListener {
        void onContactSelected(View view, Contact contact, int position);
    }

    private MyOnContactClickListener mListener;

    public void setMyOnContactClickListener(MyOnContactClickListener listener) {
        mListener = listener;
    }
    // 인터페이스 ---------------------------------------

    private Map<Contact, Boolean> mCheckedMap = new HashMap<>();
    private List<Contact> mItems = new ArrayList<>();

    private List<Contact> mCheckedContactList = new ArrayList<>();

    public boolean removeItems() {
        boolean result = mItems.removeAll(mCheckedContactList);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    public void setItems(List<Contact> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_contact, viewGroup, false);
        final ContactViewHolder viewHolder = new ContactViewHolder(view);

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Contact contact = mItems.get(viewHolder.getAdapterPosition());
                mCheckedMap.put(contact, isChecked);

                if (isChecked) {
                    mCheckedContactList.add(contact);
                } else {
                    mCheckedContactList.remove(contact);
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Contact contact = mItems.get(viewHolder.getAdapterPosition());
                    mListener.onContactSelected(v, contact,
                            viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        Contact contact = mItems.get(i);

        contactViewHolder.pictureImageView.setImageResource(R.mipmap.ic_launcher);
        contactViewHolder.nameTextView.setText(contact.getName());

        boolean isChecked = mCheckedMap.get(contact) == null
                ? false
                : mCheckedMap.get(contact);
        contactViewHolder.checkBox.setChecked(isChecked);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // ViewHolder
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView pictureImageView;
        TextView nameTextView;
        CheckBox checkBox;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureImageView = itemView.findViewById(R.id.picture_image_view);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
