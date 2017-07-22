package com.gemastik.toilet.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemastik.toilet.R;
import com.gemastik.toilet.DetailActivity;

/**
 * Created by anjas on 30/03/17.
 */

public class RiwayatListHolder extends RecyclerView.ViewHolder {
    TextView time, location, level, glukosa;
    ImageView image;
    public RiwayatListHolder(View itemView) {
        super(itemView);
        time = (TextView)itemView.findViewById(R.id.data_time);
        level = (TextView)itemView.findViewById(R.id.data_level);
        location = (TextView)itemView.findViewById(R.id.data_location);
        image = (ImageView)itemView.findViewById(R.id.data_image);
        glukosa = (TextView)itemView.findViewById(R.id.glukosa_level);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), DetailActivity.class);
//                intent.putExtra("name", "Bobo");
//                intent.putExtra("level", "2");
//                intent.putExtra("location", "Lab FTTH");
//                intent.putExtra("recomendation", "Recomendation");
//                intent.putExtra("time", "29 Mar 2017, 22:22");
//                v.getContext().startActivity(intent);
//            }
//        });
    }
}
