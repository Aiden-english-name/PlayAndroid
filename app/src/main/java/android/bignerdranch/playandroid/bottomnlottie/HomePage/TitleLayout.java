package android.bignerdranch.playandroid.bottomnlottie.HomePage;

import android.bignerdranch.playandroid.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class TitleLayout extends LinearLayout {
    ImageView headPortrait;
    ImageView search;
    Context mContext;
    private View mView;

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(android.bignerdranch.playandroid.R.layout.title,null);
        headPortrait = mView.findViewById(R.id.head_portrait);
        search = mView.findViewById(R.id.search);
        mContext = context;
    }

    public void setHeadPortrait(int Res){
        Glide.with(mView).load(Res).into(headPortrait);
    }
    public void setImage(int Res){
        Glide.with(mView).load(Res).into(search);
    }

}
