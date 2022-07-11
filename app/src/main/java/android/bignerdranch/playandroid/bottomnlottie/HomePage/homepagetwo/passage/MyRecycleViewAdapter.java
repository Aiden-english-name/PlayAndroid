package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage;

import android.app.Activity;
import android.bignerdranch.playandroid.MainActivity;
import android.bignerdranch.playandroid.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private static final String TAG = "hello";
    boolean isPressed = true;
    Context mContext;

    public MyRecycleViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_passage,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.lottieAnimationView.setAnimation(R.raw.collect_black);
        viewHolder.lottieAnimationView.setProgress((float) 0.22);



        viewHolder.lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isPressed){
                    viewHolder.lottieAnimationView.setAnimation(R.raw.collect_black);
                    viewHolder.lottieAnimationView.setProgress((float) 0.25);
                    isPressed = true;
                }else {
                    viewHolder.lottieAnimationView.setAnimation(R.raw.collect_yellow);
                    viewHolder.lottieAnimationView.playAnimation();
                    isPressed = false;
                }

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"YangRegular.ttf");
        holder.mAuthor.setTypeface(typeface);
        holder.mSuperChapterName.setTypeface(typeface);
        holder.mTitle.setTypeface(typeface);
        holder.mChapterName.setTypeface(typeface);
        holder.mNiceShareDate.setTypeface(typeface);
        sendRequestWithOkhttp(holder,position);


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mAuthor;
        private TextView mSuperChapterName;
        private TextView mTitle;
        private TextView mChapterName;
        private TextView mNiceShareDate;
        private LottieAnimationView lottieAnimationView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.author);
            mSuperChapterName = itemView.findViewById(R.id.superChapterName);
            mTitle = itemView.findViewById(R.id.title);
            mChapterName = itemView.findViewById(R.id.chapterName);
            mNiceShareDate = itemView.findViewById(R.id.niceShareDate);
            lottieAnimationView = itemView.findViewById(R.id.lottie);
            mView = itemView;
        }
    }

    private void sendRequestWithOkhttp(ViewHolder viewHolder, int position){
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.wanandroid.com/article/top/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseDate = response.body().string();
                dealDateWithGson(responseDate);

                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.mAuthor.setText("作者:"+topPassageBean.getData().get(position).getAuthor());
                        viewHolder.mSuperChapterName.setText(topPassageBean.getData().get(position).getChapterName());
                        viewHolder.mTitle.setText(topPassageBean.getData().get(position).getTitle());
                        viewHolder.mChapterName.setText(topPassageBean.getData().get(position).getChapterName());
                        viewHolder.mNiceShareDate.setText(topPassageBean.getData().get(position).getNiceShareDate());
                    }
                });
                Log.d(TAG, responseDate);
            }
        });
    }
    TopPassageBean topPassageBean;
    private void dealDateWithGson(String date){
        Gson gson = new Gson();
        topPassageBean= gson.fromJson(date,TopPassageBean.class);
        for (int i = 0;i<topPassageBean.getData().size();i++){
            Log.d(TAG, topPassageBean.getData().get(i).printlnDate());
        }

    }
}
