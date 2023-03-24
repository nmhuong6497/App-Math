package com.example.appmath2023;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmath2023.databinding.LayoutItemHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    List<String> historyList;
    public HistoryAdapter() {
        historyList = new ArrayList<>();
    }

    public void setHistoryList(String caculation) {
        historyList.add(0, caculation);
        notifyDataSetChanged();
    }

    public List<String> getHistoryList() {
        return historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.binding.textViewHistory.setText(historyList.get(position));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        LayoutItemHistoryBinding binding;
        String s;

        public HistoryViewHolder(@NonNull LayoutItemHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
