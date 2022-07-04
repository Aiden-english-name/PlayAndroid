package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo;

import android.bignerdranch.playandroid.MainActivity;
import android.bignerdranch.playandroid.R;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.BannerData;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.DataBeen;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.MyBannerAdapter;
import android.bignerdranch.playandroid.net.OkHttpCallbackListener;
import android.bignerdranch.playandroid.net.OkHttpUtil;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;


public class HomePageTwoFragment extends Fragment {

    private View mView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_page_two, container, false);

        return mView;
    }
}