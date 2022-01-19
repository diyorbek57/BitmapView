package com.b12.lesson.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.b12.lesson.R;
import com.b12.lesson.fragments.WifiFragment;

import java.util.List;

public class WifiScanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WifiFragment.device> wifiList;
    private Context context;

    public WifiScanAdapter(List<WifiFragment.device> wifiList, Context context) {
        this.wifiList = wifiList;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_wifi, viewGroup, false);

        VideoViewHolder holder = new VideoViewHolder(itemView);
        itemView.setTag(holder);

        return holder;

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        WifiFragment.device device=wifiList.get(position);
        String name=device.getName().toString();

        ((VideoViewHolder) holder).vName.setText(name);


        ((VideoViewHolder) holder).vImage.setImageResource(R.drawable.ic_baseline_wifi_24);
        ((VideoViewHolder) holder).context = context;
        ((VideoViewHolder) holder).position = position;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        int itemCount = wifiList.size();

        return itemCount;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected  Context context;
        protected int position;


        public VideoViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.tv_wifi_name);
            vImage = (ImageView)  v.findViewById(R.id.iv_wifi_right_arrow);

        }
    }

}
