package android.bignerdranch.playandroid.bottomnlottie.HomePage.HomeFragment;


import android.bignerdranch.playandroid.bottomnlottie.HomePage.Homepage;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.PoetBeen;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.SystemFragment.SystemAdapter;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.SystemFragment.SystemBean;

import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.BannerData;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.DataBeen;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner.MyBannerAdapter;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage.MyRecycleViewAdapter;
import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage.PassageBean;
import android.bignerdranch.playandroid.bottomnlottie.search.SearchActivity;
import android.bignerdranch.playandroid.net.OkHttpCallbackListener;
import android.bignerdranch.playandroid.net.OkHttpUtil;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.playandroid.R;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
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
    private static final String TAG = "HomePageFragment";

    //下拉刷新
    SmartRefreshLayout refreshLayout;
    private TextView mTextView;
    private ConstraintLayout classicsHeader;
    private ClassicsFooter classicsFooter;

    //上拉加载
    private PassageBean mPassageBean;
    private int page = 0;
    private int itemCount = 0;
    private int formerCount = 0;
    private List<PassageBean.DataBean.DatasBean> mDatesBeans = new ArrayList<>();
    private List<PassageBean.DataBean.DatasBean> mLimitedBeans = new ArrayList<>();


    boolean isBannerIndicatorCreated = false;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what ==1){
                mBanner = mBannerView.findViewById(R.id.banner);
                DataBeen dataBeen1 = new DataBeen(bannerDates.get(0).getImagePath(),bannerDates.get(0).getTitle(),bannerDates.get(0).getUrl());
                DataBeen dataBeen2 = new DataBeen(bannerDates.get(1).getImagePath(),bannerDates.get(1).getTitle(),bannerDates.get(1).getUrl());
                DataBeen dataBeen3 = new DataBeen(bannerDates.get(2).getImagePath(),bannerDates.get(2).getTitle(),bannerDates.get(2).getUrl());

                mDataBeans.add(dataBeen1);
                mDataBeans.add(dataBeen2);
                mDataBeans.add(dataBeen3);

                if(!isBannerIndicatorCreated){
                    mBanner.addBannerLifecycleObserver(HomePageFragment.this)
                            .setAdapter(new MyBannerAdapter(mDataBeans,getContext()))
                            .setIndicator(new CircleIndicator(getContext()))
                            .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
                            .setBannerRound2(100)
                            .setBannerGalleryMZ(50)
                            .setPageTransformer(new ZoomOutPageTransformer())
                            .start();
                    isBannerIndicatorCreated = true;
                }


            }


            if(message.what == 2){
                mTextView.setText(message.obj.toString());
            }
            if(message.what == 3){
                initRecycleView();
            }
            if(message.what == 4){
                isGainDate = true;
            }if(message.what == 5){
                if(isGainDate == true&&isCreateSystem == false){
                    createTwoClass();
                    isCreateSystem = true;
                }

            }

            

            return true;
        }
    });

    boolean isGainDate = false;
    boolean isCreateSystem = false;

    List<String> mList2 = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SystemBean mBean;

    private void createTwoClass() {

        for(int i = 0;i<mList.size();i++){
            if(mList.get(i).length()==4) {
                mList2.add(mList.get(i));

            }

        }
        RecyclerView recyclerView = mSystemView.findViewById(R.id.system_recyclerview);
        SystemAdapter adapter = new SystemAdapter(getContext(),mList2,mBean);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        LayoutAnimationController controller = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(),R.anim.system_son));
        recyclerView.setLayoutAnimation(controller);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }

    MyAdapter myAdapter = new MyAdapter();
    private RecyclerView mRecyclerView;
    private LottieAnimationView mLottieAnimationView;
    private List<String> mList = new ArrayList<>();


    private void initRecycleView() {

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        if (page == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(myAdapter);
        }
        myAdapter.notifyItemRangeChanged(count,myAdapter.getItemCount());

    }
    private View mBannerView;
    private View mSystemView;

    public int i =0;
    public void jumpPassage(){
        NestedScrollView nestedScrollView = mBannerView.findViewById(R.id.nestedScrollView);
        ((Homepage)getActivity()).tabHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Homepage)getActivity()).setSelected(R.id.home_page,"#FFC13B");
                ((Homepage)getActivity()).setUnSelected(1);
                ((Homepage)getActivity()).changeFragment(((Homepage)getActivity()).homePageFragment);
                refreshLayout.autoRefresh();
                nestedScrollView.fling(0);
                nestedScrollView.smoothScrollTo(0,0);
            }
        });

        ((Homepage)getActivity()).tabHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Homepage)getActivity()).setSelected(R.id.home_page,"#FFC13B");
                ((Homepage)getActivity()).setUnSelected(1);
                ((Homepage)getActivity()).changeFragment(((Homepage)getActivity()).homePageFragment);
                refreshLayout.autoRefresh();
                nestedScrollView.fling(0);
                nestedScrollView.smoothScrollTo(0,0);
            }
        });
        ((Homepage)getActivity()).homePageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Homepage)getActivity()).setSelected(R.id.home_page,"#FFC13B");
                ((Homepage)getActivity()).setUnSelected(1);
                ((Homepage)getActivity()).changeFragment(((Homepage)getActivity()).homePageFragment);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_page,null);


        //顶部导航栏
        if (!tab_created) {
            createTabLayout();
            tab_created = true;
        }

        //搜索框
        createToolBar();

        createTopPassage();


        //上拉加载

        initSmartRefreshLayout();


        sendRequestWithOkhttp();


        jumpPassage();

        //搜索页
        jumpSearch();
    }

    private void jumpSearch() {
        CardView cardView = view.findViewById(R.id.toolbar_card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSmartRefreshLayout() {
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        mLottieAnimationView = view.findViewById(R.id.loading);
        mLottieAnimationView.setAnimation(R.raw.loading);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mLottieAnimationView.playAnimation();
                refreshLayout.finishLoadMore(1750);

                page++;
                sendRequestWithOkhttp();
            }
        });
    }

    private void sendRequestWithOkhttp() {

        String url = "https://www.wanandroid.com/article/list/" + page + "/json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String date = response.body().string();
                try {
                    Thread.sleep(1000  );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                parseDateWithGson(date);
            }
        });
    }

    int count = 0;
    public void parseDateWithGson(String date) {
        Gson gson = new Gson();
        formerCount = itemCount;
        mPassageBean = gson.fromJson(date, PassageBean.class);
        int nowCount = mPassageBean.getData().getDatas().size();
        itemCount += mPassageBean.getData().getDatas().size();
        for (int i = 0; i < nowCount; i++) {
            mDatesBeans.add(mPassageBean.getData().getDatas().get(i));
        }
        for (i = count;i<count+10;i++){
            mLimitedBeans.add(mDatesBeans.get(i));
        }
        count += 10;

        Message message = new Message();
        message.what = 3;
        handler.sendMessage(message);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        boolean isPressed = true;

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.passage, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.title.setMaxEms(17);
            viewHolder.mImageView1.setImageResource(R.drawable.fenlei);
            viewHolder.mImageView2.setImageResource(R.drawable.sanjiaoxing_3);
            viewHolder.lottieAnimationView.setAnimation(R.raw.collect_black);
            viewHolder.lottieAnimationView.setProgress((float) 0.22);


            viewHolder.lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!isPressed) {
                        viewHolder.lottieAnimationView.setAnimation(R.raw.collect_black);
                        viewHolder.lottieAnimationView.setProgress((float) 0.25);
                        isPressed = true;
                    } else {
                        viewHolder.lottieAnimationView.setAnimation(R.raw.collect_yellow);
                        viewHolder.lottieAnimationView.playAnimation();
                        isPressed = false;
                    }

                }
            });

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "YangRegular.ttf");
            holder.title.setTypeface(typeface);
            holder.title.setText(mLimitedBeans.get(position).getTitle());
            holder.superCategory.setText(mLimitedBeans.get(position).getSuperChapterName());
            holder.category.setText(mLimitedBeans.get(position).getChapterName());
            if (mLimitedBeans.get(position).getAuthor().equals("")) {
                holder.date.setText(mLimitedBeans.get(position).getNiceShareDate());
                holder.author.setText("分享人：" + mLimitedBeans.get(position).getShareUser());
            } else {
                holder.date.setText(mLimitedBeans.get(position).getNiceDate());
                holder.author.setText("作者：" + mLimitedBeans.get(position).getAuthor());
            }

        }

        @Override
        public int getItemCount() {

            return count;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView author;
            TextView superCategory;
            TextView category;
            TextView date;
            TextView title;
            ImageView mImageView1;
            ImageView mImageView2;
            LottieAnimationView lottieAnimationView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                author = itemView.findViewById(R.id.author);
                superCategory = itemView.findViewById(R.id.superChapterName);
                category = itemView.findViewById(R.id.chapterName);
                date = itemView.findViewById(R.id.niceShareDate);
                title = itemView.findViewById(R.id.title);
                lottieAnimationView = itemView.findViewById(R.id.lottie);
                mImageView1 = itemView.findViewById(R.id.categery);
                mImageView2 = itemView.findViewById(R.id.sanjiaoxing);
            }
        }
    }

    private void createToolBar() {


        LinearLayout linearLayout = view.findViewById(R.id.my_title);
        ImageView headPortrait = linearLayout.findViewById(R.id.head_portrait);
        ImageView search =linearLayout.findViewById(R.id.search);
        Glide.with(linearLayout).load(R.drawable.research)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(search);
        Glide.with(linearLayout)
                .load(R.drawable.head_portrait)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(headPortrait);
        Toolbar mToolbar = linearLayout.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gainBannerData();
        createSmartRefreshLayout();

        //创建二级分类
        OkHttpUtil.sendRequestWithOkHttp("https://www.wanandroid.com/tree/json", new OkHttpCallbackListener() {
            @Override
            public void finish(String response) {

                mBean = new Gson().fromJson(response,SystemBean.class);

                for(int i = 0; i < mBean.getData().size(); i++){

                    mList.add(mBean.getData().get(i).getName());

                }
                Message message = new Message();
                message.what = 4;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return view;
    }


    private void createTopPassage() {
        mRecyclerView = mBannerView.findViewById(R.id.recycle_view_top_passage);
        MyRecycleViewAdapter myRecycleViewAdapter = new MyRecycleViewAdapter(getContext());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(myRecycleViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    //顶部导航栏
    private void createTabLayout() {
        mTabLayout = view.findViewById(R.id.tabLayout);
        mViewPager = view.findViewById(R.id.view_pager);
        for(int i = 0;i<tabs.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(tabs[i]));
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 1&&isGainDate == true){
                    Message message = new Message();
                    message.what = 5;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBannerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_page_two,null);
        mTabFragmentList.add(mBannerView);


        mSystemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_system,null);

        mTabFragmentList.add(mSystemView);
        mTabFragmentList.add(View.inflate(getContext(),R.layout.fragment_navigation,null));




        mViewPager.setAdapter(new PagerAdapter() {
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

                return tabs[position%
                        mTabFragmentList.size()];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager,false);
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
                msg.what = 1;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();

            }
        });

    }


    //下拉刷新

    private void createSmartRefreshLayout() {

        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        refreshLayout = linearLayout.findViewById(R.id.refreshLayout);
        ConstraintLayout constraintLayout = refreshLayout.findViewById(R.id.header);
        classicsHeader = constraintLayout.findViewById(R.id.header);
        classicsFooter = constraintLayout.findViewById(R.id.footer);
        LottieAnimationView lottieAnimationView = constraintLayout.findViewById(R.id.lottieAnimationView);
        mTextView = constraintLayout.findViewById(R.id.text_view);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"盐叶之庭.ttf");
        mTextView.setTypeface(typeface);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                lottieAnimationView.setAnimation(R.raw.book);
                sendRequestWithOkhttp("https://v1.hitokoto.cn/");
                lottieAnimationView.playAnimation();

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        lottieAnimationView.cancelAnimation();
                        lottieAnimationView.setProgress(0);
                        mTextView.setText("");
                    }
                };
                handler.postDelayed(runnable,3000);
                refreshLayout.finishRefresh(3000);

            }


        });
        refreshLayout.finishRefresh();
    }

    private void sendRequestWithOkhttp(String date){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(date)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String responseDate = response.body().string();

                            showResponse(responseDate);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }).start();
    }
    PoetBeen poetBeen = new PoetBeen();

    private void showResponse(String responseDate) {

        Gson gson = new Gson();
        poetBeen = gson.fromJson(responseDate,PoetBeen.class);
        Message message = new Message();
        message.obj = poetBeen.getHitokoto();
        message.what = 2;
        handler.sendMessage(message);

    }



}