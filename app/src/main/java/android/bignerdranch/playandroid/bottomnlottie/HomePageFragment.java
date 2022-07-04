package android.bignerdranch.playandroid.bottomnlottie;


import android.bignerdranch.playandroid.MainActivity;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.HomePageTwoFragment;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.BannerData;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.DataBeen;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.MyBannerAdapter;
import android.bignerdranch.playandroid.net.OkHttpCallbackListener;
import android.bignerdranch.playandroid.net.OkHttpUtil;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.playandroid.R;

import com.airbnb.lottie.L;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {

    View view;

    //顶部导航栏
    final private String[] tabs = {"首页","体系","导航"};
    private List<View> mTabFragmentList = new ArrayList<>();
    boolean tab_created = false;

    //Banner
    List<DataBeen> mDataBeans =new ArrayList<>();
    List<BannerData.DataBean> bannerDates;
    private Banner mBanner;
    private static final String TAG = "Hello";
    MainActivity mMainActivity;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            mBanner = mBannerView.findViewById(R.id.banner);
            DataBeen dataBeen1 = new DataBeen(bannerDates.get(0).getImagePath(),bannerDates.get(0).getTitle(),bannerDates.get(0).getUrl());
            DataBeen dataBeen2 = new DataBeen(bannerDates.get(1).getImagePath(),bannerDates.get(1).getTitle(),bannerDates.get(1).getUrl());
            DataBeen dataBeen3 = new DataBeen(bannerDates.get(2).getImagePath(),bannerDates.get(2).getTitle(),bannerDates.get(2).getUrl());

            mDataBeans.add(dataBeen1);
            mDataBeans.add(dataBeen2);
            mDataBeans.add(dataBeen3);

            mBanner.addBannerLifecycleObserver(HomePageFragment.this)
                    .setAdapter(new MyBannerAdapter(mDataBeans,getContext()))
                    .setIndicator(new CircleIndicator(getContext()))
                    .setBannerRound2(100)
                    .setBannerGalleryMZ(50)
                    .setPageTransformer(new ZoomOutPageTransformer())
                    .start();

            return true;
        }
    });
    private View mBannerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_page,null);

        //顶部导航栏
        if (!tab_created) {
            createTabLayout();
            tab_created = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gainBannerData();
        return view;
    }

    //顶部导航栏
    private void createTabLayout() {
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        for(int i = 0;i<tabs.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
        }

        mBannerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_page_two,null);
        mTabFragmentList.add(mBannerView);
        mTabFragmentList.add(View.inflate(getContext(),R.layout.fragment_system,null));
        mTabFragmentList.add(View.inflate(getContext(),R.layout.fragment_navigation,null));

        //banner


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabFragmentList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = mTabFragmentList.get(position%mTabFragmentList.size());
                ((ViewPager)container).addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = mTabFragmentList.get(position%mTabFragmentList.size());
                ((ViewPager)container).removeView(view);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return tabs[position%mTabFragmentList.size()];
            }
        });

        tabLayout.setupWithViewPager(viewPager,false);
    }

    //banner
    private void gainBannerData() {

        String address = "https://www.wanandroid.com/banner/json";
        OkHttpUtil.sendRequestWithOkHttp(address, new OkHttpCallbackListener() {
            @Override
            public void finish(String response) {
                Gson gson = new Gson();
                BannerData dates = gson.fromJson(response,BannerData.class);
                bannerDates = dates.getData();
                Message msg = new Message();
                msg.obj = response;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Log.d(TAG, "连接失败");
            }
        });

    }

}