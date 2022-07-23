package android.bignerdranch.playandroid.bottomnlottie.HomePage.SystemFragment;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SystemAdapter extends RecyclerView.Adapter<SystemAdapter.ViewHolder> {
    Context mContext;
    List<String> mList;


    public SystemAdapter(Context context, List<String> list2, SystemBean bean) {
        this.mList = list2;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.system_fragment_son_layout,parent,false);
        ViewHolder holder =new ViewHolder(view);
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"YangRegular.ttf");
        holder.systemTextView.setTypeface(typeface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.systemTextView.setText(mList.get(position));
        if(position == 0){
            holder.systemImageView.setImageResource(R.drawable.badanmu);
        }
        else if(position == 1) {
            holder.systemImageView.setImageResource(R.drawable.hetao);
        } else if(position == 2){
            holder.systemImageView.setImageResource(R.drawable.bigenguo);
        }else if(position == 3){
            holder.systemImageView.setImageResource(R.drawable.binggan);
        }
        else if(position == 4){
            holder.systemImageView.setImageResource(R.drawable.buding);
        }else if(position == 5){
            holder.systemImageView.setImageResource(R.drawable.candou);
        }else if(position == 6){
            holder.systemImageView.setImageResource(R.drawable.dangao);
        }else if(position == 7){
            holder.systemImageView.setImageResource(R.drawable.danhuangsu);
        }else if(position == 8){
            holder.systemImageView.setImageResource(R.drawable.hetao);
        }else if(position == 9){
            holder.systemImageView.setImageResource(R.drawable.hongzao);
        }else if(position == 10){
            holder.systemImageView.setImageResource(R.drawable.huasheng);
        }else if(position == 11){
            holder.systemImageView.setImageResource(R.drawable.kaixinguo);
        }else if(position == 12){
            holder.systemImageView.setImageResource(R.drawable.manyuemei);
        }else if(position == 13){
            holder.systemImageView.setImageResource(R.drawable.putaogan);
        }else if(position == 14){
            holder.systemImageView.setImageResource(R.drawable.qiaokeli);
        }else if(position == 15){
            holder.systemImageView.setImageResource(R.drawable.songzi);
        }else if(position == 16){
            holder.systemImageView.setImageResource(R.drawable.tangguo);
        }else if(position == 17){
            holder.systemImageView.setImageResource(R.drawable.xiaweiyiguo);
        }else if(position == 18){
            holder.systemImageView.setImageResource(R.drawable.xiguazi);
        }else if(position == 20){
            holder.systemImageView.setImageResource(R.drawable.yaoguo);
        }else if(position == 21){
            holder.systemImageView.setImageResource(R.drawable.dangao);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView systemImageView;
        TextView systemTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            systemImageView = itemView.findViewById(R.id.system_imageView);
            systemTextView = itemView.findViewById(R.id.system_textView);
        }
    }
}
