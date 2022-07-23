package android.bignerdranch.playandroid.bottomnlottie.search.search_result;

import android.bignerdranch.playandroid.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class MySearchedPassageAdapter extends RecyclerView.Adapter<MySearchedPassageAdapter.ViewHolder> {

    Context context;
    List<PassageBean.DataBean.DatasBean> beans;
    boolean isPressed = true;
    String key;
    MySearchedPassageAdapter mMySearchedPassageAdapter;

    //刷新数据和适配器视图
    public void setData(List<PassageBean.DataBean.DatasBean> beans,String key){
        this.beans=beans;
        this.key =key;
    }



    public MySearchedPassageAdapter(Context context,List<PassageBean.DataBean.DatasBean> beans,String key) {
        this.context = context;
        this.beans = beans;
        this.key = key;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.passage_rl,parent,false);
        ViewHolder holder = new ViewHolder(view);

        holder.lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPressed){
                    holder.lottie.setAnimation(R.raw.collect_black);
                    holder.lottie.setProgress((float) 0.25);
                    isPressed = true;
                }else {
                    holder.lottie.setAnimation(R.raw.collect_yellow);
                    holder.lottie.playAnimation();
                    isPressed = false;
                }
            }
        });
        holder.triangle.setImageResource(R.drawable.sanjiaoxing_3);
        holder.category.setImageResource(R.drawable.fenlei);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(beans.get(position).getAuthor().equals("")){
            holder.authorOr.setText(beans.get(position).getShareUser());
            holder.niceShareTime.setText(beans.get(position).getNiceShareDate());
        }else {
            holder.authorOr.setText(beans.get(position).getAuthor());
            holder.niceShareTime.setText(beans.get(position).getNiceDate());
        }

        holder.chapterName.setText(beans.get(position).getChapterName());
        holder.superChapterName.setText(beans.get(position).getSuperChapterName());

        String title = dealWithDate(beans.get(position).getTitle());
        holder.title.setText(title);
        holder.lottie.setAnimation(R.raw.collect_black);
        holder.lottie.setProgress((float) 0.22);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"YangRegular.ttf");
        holder.authorOr.setTypeface(typeface);
        holder.superChapterName.setTypeface(typeface);
        holder.title.setTypeface(typeface);
        holder.chapterName.setTypeface(typeface);
        holder.niceShareTime.setTypeface(typeface);
    }

    private String dealWithDate(String title) {
        String titleDeal = title.replace("<em class='highlight'>"+key+"</em>",key);
        return titleDeal;
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView authorOr;
        TextView chapterName;
        TextView title;
        TextView superChapterName;
        TextView niceShareTime;
        LottieAnimationView lottie;
        ImageView category;
        ImageView triangle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            authorOr = itemView.findViewById(R.id.author);
            chapterName = itemView.findViewById(R.id.chapterName);
            title = itemView.findViewById(R.id.title);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            niceShareTime = itemView.findViewById(R.id.niceShareDate);
            lottie = itemView.findViewById(R.id.lottie);
            category = itemView.findViewById(R.id.categery);
            triangle = itemView.findViewById(R.id.triangle);
        }
    }
}
