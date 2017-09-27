package com.example.elena.binarysearchtree;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 9/9/2017.
 */

class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<Double> data = new ArrayList<>();

    public void setData(List<Double> list){
        data =list;
        notifyDataSetChanged();
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.textView.setText(data.get(position)+"");
    }

    @Override
    public int getItemCount() {
        if(data == null || data.isEmpty())
            return 0;
        return data.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ImageView imageView;
        NumberViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_number);
            imageView = (ImageView) itemView.findViewById(R.id.image_remove);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    double value = data.get(position);
                    MainActivity.sTree.remove(value);
                    setData(MainActivity.sTree.getList());
                    MainActivity.refreshRecyclerView();
                }
            });
        }
    }
}
