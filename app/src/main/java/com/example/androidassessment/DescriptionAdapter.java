package com.example.androidassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.DescriptionViewHolder>
{

    private List<Description> descriptionList;
    private OnDescriptionListener _onDescriptionListener;

    DescriptionAdapter(Context context, List<Description> descriptionList, OnDescriptionListener onDescriptionListener)
    {
        this.descriptionList = descriptionList;
        this._onDescriptionListener = onDescriptionListener;
    }

    @NonNull
    @Override
    public DescriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.description_item, parent, false);

        return new DescriptionViewHolder(view, _onDescriptionListener);
    }

    @Override
    public void onBindViewHolder(DescriptionViewHolder holder, int position) {
        TextView description;
        Description currentItem = descriptionList.get(position);

        description = holder.description;

        description.setText(currentItem.description);

    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }

    void setDescriptionList(List<Description> descriptionList){
        this.descriptionList = descriptionList;
    }

    static class DescriptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description;
        OnDescriptionListener onDescriptionListener;

        DescriptionViewHolder(View view, OnDescriptionListener onDescriptionListener)
        {
            super(view);
            this.onDescriptionListener = onDescriptionListener;
            description = view.findViewById(R.id.description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDescriptionListener.onDescriptionClick(getAdapterPosition());
        }
    }

    public interface OnDescriptionListener {
        public void onDescriptionClick(int position);
    }
}
