package com.example.recyclerviewexam.eventbus;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.eventbus.dummy.DummyContent.DummyItem;
import com.example.recyclerviewexam.eventbus.events.ItemClickEvent;
import com.example.recyclerviewexam.eventbus.events.SecondEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MyItemRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 이벤트 버스 사용
                if (holder.getAdapterPosition() == 3) {
                    // 빨간색
                    EventBus.getDefault().post(new ItemClickEvent(Color.RED));
                } else if (holder.getAdapterPosition() == 0) {
                    // 노란색
                    EventBus.getDefault().post(new ItemClickEvent(Color.YELLOW));
                } else {
                    EventBus.getDefault().post(new SecondEvent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
