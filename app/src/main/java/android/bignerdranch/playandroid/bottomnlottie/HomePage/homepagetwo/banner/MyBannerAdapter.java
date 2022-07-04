package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner;


import android.bignerdranch.playandroid.MainActivity;
import android.bignerdranch.playandroid.R;

import android.bignerdranch.playandroid.bottomnlottie.passage.Passage;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class MyBannerAdapter extends BannerAdapter<DataBeen,MyBannerAdapter.BannerViewHolder> {

    Context mContext;
    List<DataBeen> datas;
    Intent intent;

    public MyBannerAdapter(List<DataBeen> datas, Context applicationContext) {
        super(datas);
        this.mContext = applicationContext;
        this.datas = datas;
    }


    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.banner_item_view,parent,false);
        BannerViewHolder holder = new BannerViewHolder(view);
        intent = new Intent(mContext, Passage.class);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url",datas.get(holder.getLayoutPosition()-1).getUrl());
                mContext.startActivity(intent);

            }
        });
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("url",datas.get(holder.getLayoutPosition()-1).getUrl());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindView(BannerViewHolder holder, DataBeen data, int position, int size) {

        Glide.with(holder.itemView).load(data.getImageRes()).placeholder(R.drawable.ic_launcher_background).into(holder.mImageView);
        holder.mTextView.setText(data.textRes);
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "华康少女字体.ttf");
        holder.mTextView.setTypeface(typeface);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
