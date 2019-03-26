package com.example.recyclerviewexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 다용도 RecyclerAdapter
 * 만든이 : 오준석
 *
 * @param <T>  모델 클래스
 * @param <VH> 뷰홀더
 */
public abstract class JsRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> mItems = new ArrayList<>();

    public JsRecyclerAdapter(List<T> items) {
        this.mItems = items;
    }

    public void setItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        onBindViewHolder(vh, i, mItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    protected abstract void onBindViewHolder(@NonNull VH holder, int position, @NonNull T model);
}
