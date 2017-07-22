package com.gemastik.toilet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gemastik.toilet.R;
import com.gemastik.toilet.object.DataRiwayat;

import java.util.ArrayList;

/**
 * Created by anjas on 30/03/17.
 */

public class RiwayatListAdapter extends RecyclerView.Adapter<RiwayatListHolder> {
    private Context context;
    private ArrayList<DataRiwayat> items;

    public RiwayatListAdapter(Context context, ArrayList<DataRiwayat> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RiwayatListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, null);
        RiwayatListHolder view = new RiwayatListHolder(layoutView);
        return view;
    }

    @Override
    public void onBindViewHolder(RiwayatListHolder holder, int position) {
        holder.level.setText("Dehydration Level "+items.get(position).getUrin());
        holder.location.setText(items.get(position).getLocation());
        holder.time.setText(items.get(position).getTime());
        holder.glukosa.setText("Glucose "+ (items.get(position).getUrin() +125) + " (mg/gl)");
        switch (items.get(position).getUrin()){
            case 1:
                holder.image.setImageResource(R.drawable.level_1);
                break;
            case 2:
                holder.image.setImageResource(R.drawable.level_2);
                break;
            case 3:
                holder.image.setImageResource(R.drawable.level_3);
                break;
            case 4:
                holder.image.setImageResource(R.drawable.level_4);
                break;
            case 5:
                holder.image.setImageResource(R.drawable.level_5);
                break;
            case 6:
                holder.image.setImageResource(R.drawable.level_6);
                break;
            default:
                holder.image.setImageResource(R.drawable.level_1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
