package android.bignerdranch.playandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bignerdranch.playandroid.bottomnlottie.CategoryFragment;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.BannerData;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.DataBeen;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.MyBannerAdapter;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage.MyRecycleViewAdapter;
import android.bignerdranch.playandroid.bottomnlottie.HomePageFragment;
import android.bignerdranch.playandroid.bottomnlottie.MineFragment;
import android.bignerdranch.playandroid.net.OkHttpCallbackListener;
import android.bignerdranch.playandroid.net.OkHttpUtil;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //底部导航动画
    public LottieAnimationView tabHomePage;
    LottieAnimationView tabCategory;
    LottieAnimationView tabMy;
    public TextView homePage;
    TextView category;
    TextView my;
    public ConstraintLayout homePageLayout;
    ConstraintLayout categoryLayout;
    ConstraintLayout myLayout;

    //切换碎片
    public Fragment homePageFragment;
    Fragment categoryFragment;
    Fragment mineFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        //底部导航动画
        tabHomePage = findViewById(R.id.tab_home_page);
        tabCategory = findViewById(R.id.tab_category);
        tabMy = findViewById(R.id.tab_my);
        homePage = findViewById(R.id.home_page);
        category = findViewById(R.id.category);
        my = findViewById(R.id.my);
        homePageLayout = findViewById(R.id.home_page_layout);
        categoryLayout = findViewById(R.id.category_layout);
        myLayout = findViewById(R.id.my_layout);
        homePageLayout.setOnClickListener(this);
        categoryLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
        tabHomePage.setOnClickListener(this);
        tabCategory.setOnClickListener(this);
        tabMy.setOnClickListener(this);
        homePage.setOnClickListener(this);
        category.setOnClickListener(this);
        my.setOnClickListener(this);
        tabHomePage.setAnimation(R.raw.home_pressed);
        tabCategory.setAnimation(R.raw.category_pressed);
        tabMy.setAnimation(R.raw.mine_pressed);
        tabHomePage.setSpeed(1.4f);
        tabCategory.setSpeed(1.4f);
        tabMy.setSpeed(1.4f);
        setSelected(R.id.home_page,"#FFC13B");
        setUnSelected(1);

        //切换碎片
        homePageFragment = new HomePageFragment();
        categoryFragment = new CategoryFragment();
        mineFragment = new MineFragment();
        changeFragment(homePageFragment);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //底部导航动画 + 切换碎片
            case R.id.home_page:
            case R.id.home_page_layout:
            case R.id.tab_home_page:{
                setSelected(R.id.home_page,"#FFC13B");
                setUnSelected(1);
                changeFragment(homePageFragment);

            }break;

            case R.id.category:
            case R.id.category_layout:
            case R.id.tab_category:{
                setSelected(R.id.category,"#8A8BF2");
                setUnSelected(2);
                changeFragment(categoryFragment);
            }break;

            case R.id.my:
            case R.id.my_layout:
            case R.id.tab_my:{
                setSelected(R.id.my,"#00A0FF");
                setUnSelected(3);
                changeFragment(mineFragment);
            }break;



        }
    }

    //底部导航动画
    public void setSelected(int id,String colorRes){
        switch (id){
            case R.id.home_page:
            case R.id.home_page_layout:
            case R.id.tab_home_page:{
                tabHomePage.playAnimation();
                homePage.setTextColor(android.graphics.Color.parseColor(colorRes));
            }break;

            case R.id.category:
            case R.id.category_layout:
            case R.id.tab_category:{
                tabCategory.playAnimation();
                category.setTextColor(android.graphics.Color.parseColor(colorRes));
            }break;

            case R.id.my:
            case R.id.my_layout:
            case R.id.tab_my:{
                tabMy.playAnimation();
                my.setTextColor(android.graphics.Color.parseColor(colorRes));
            }break;

        }
    }

    public void setUnSelected(int id){
        switch (id){
            case 1:{
                category.setTextColor(Color.BLACK);
                my.setTextColor(Color.BLACK);

                tabCategory.cancelAnimation();
                tabMy.cancelAnimation();
                tabCategory.setProgress(0);
                tabMy.setProgress(0);

            }break;
            case 2:{
                homePage.setTextColor(Color.BLACK);
                my.setTextColor(Color.BLACK);
                tabHomePage.cancelAnimation();
                tabHomePage.setProgress(0);
                tabMy.cancelAnimation();
                tabMy.setProgress(0);
            }break;
            case 3:{

                homePage.setTextColor(Color.BLACK);
                category.setTextColor(Color.BLACK);
                tabHomePage.cancelAnimation();
                tabHomePage.setProgress(0);
                tabCategory.cancelAnimation();
                tabCategory.setProgress(0);

            }break;
        }

    }

    //切换碎片
    public void changeFragment(Fragment fragment){
        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack("");
        transaction.commit();
    }

    //创建toolBar menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}