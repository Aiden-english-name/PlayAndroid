package android.bignerdranch.playandroid.bottomnlottie.search.search_result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bignerdranch.playandroid.R;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

public class SearchPassage extends AppCompatActivity implements View.OnClickListener{
    EditText searchET;
    TextView loadingTv;
    private LottieAnimationView mLottieNormal;
    private LottieAnimationView mLottieRefresh;
    ImageView back;

    View mLayout;
    TextView search;
    boolean isLoadingLaterRefresh = false;

    String key;
    private static final String TAG = "SearchPassage";

    int pageTotal = 0;
    int currentPage ;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            return false;
        }
    });
    private MySearchedPassageAdapter mAdapter;
    private RecyclerView mMRecyclerView;
    private RefreshLayout mRefreshLayout;
    private AnimationDrawable mAnim;
    private ImageView mMonkeyAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searched_passage);
        currentPage = 0;

        initSmartRefreshLayout();
        initView();
        getDateFromLastActivity();
        postKeyGetPassage();

    }
    private void initSmartRefreshLayout() {
        mRefreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        //??????????????????????????????????????????????????????
        //?????????????????????
        mMonkeyAnim = findViewById(R.id.monkey_im);
        mAnim = new AnimationDrawable();
        mAnim.addFrame(getResources().getDrawable(R.drawable.drawable_loading1),80);
        mAnim.addFrame(getResources().getDrawable(R.drawable.drawable_loading2),80);
        mAnim.addFrame(getResources().getDrawable(R.drawable.drawable_loading3),80);
        mAnim.addFrame(getResources().getDrawable(R.drawable.drawable_loading4),80);
        mAnim.setOneShot(false);
        mMonkeyAnim.setImageDrawable(mAnim);
        mAnim.start();


        mLottieNormal = findViewById(R.id.normal_loading_lottie);
        mLottieRefresh = findViewById(R.id.refresh_lottie);

        mLottieRefresh.setAnimation(R.raw.refresh);
        mLottieNormal.setAnimation(R.raw.loadingaaaaaa);

        //????????????????????????????????????????????????
        mRefreshLayout.setDisableContentWhenRefresh(false);
        mRefreshLayout.setDisableContentWhenLoading(false);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.d(TAG, "onRefresh: ");
                mRefreshLayout.finishRefresh(2000);
                mLottieRefresh.playAnimation();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLottieRefresh.cancelAnimation();
                    }
                },2000);
            }

        });


        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                Log.d(TAG, "onLoadMore: ");
                mLottieNormal.playAnimation();
                mLottieNormal.setRepeatCount(2);
                refreshlayout.finishLoadMore(2000/*,false*/);//??????false??????????????????
                currentPage++;

                if(pageTotal>=currentPage){
                    postKeyGetPassage();
                }else {
                    mLottieNormal.setVisibility(View.GONE);
                    loadingTv.setText("?????????????????????????????????");
                    isLoadingLaterRefresh = true;
                }


            }
        });
    }

    private void createRecycleView() {
        mMRecyclerView = findViewById(R.id.recycleView);
        mAdapter = new MySearchedPassageAdapter(getApplicationContext(),beans,key);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.layout_item_set);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mMRecyclerView.setLayoutAnimation(controller);

        mMRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //????????????????????????????????????????????????.
        mAnim.stop();
        mMonkeyAnim.setVisibility(View.GONE);
        mMRecyclerView.setVisibility(View.VISIBLE);
        mMRecyclerView.setAdapter(mAdapter);

    }


    List<PassageBean.DataBean.DatasBean> beans;
    List<PassageBean.DataBean.DatasBean> newBeans;
    private void postKeyGetPassage() {

        //???????????????????????????
        if(currentPage == 0){
            SendRequestWthOkhttp.postWithOkhttp("https://www.wanandroid.com/article/query/0/json", key, new OkhttpListener() {
                @Override
                public void onResponse(String responseDate) {
                    beans = new Gson().fromJson(responseDate,PassageBean.class).getData().getDatas();
                    pageTotal =  new Gson().fromJson(responseDate,PassageBean.class).getData().getPageCount();
                    //?????????????????????????????????-->????????????

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            createRecycleView();
                        }
                    },2000);

                }

                @Override
                public void onFail() {
                    Log.d(TAG, "onFail: ");
                }
            });
        }else if(currentPage != 0 && currentPage<=pageTotal){
            String newUrl = "https://www.wanandroid.com/article/query/"+currentPage+"/json";
            SendRequestWthOkhttp.postWithOkhttp(newUrl, key, new OkhttpListener() {
                @Override
                public void onResponse(String responseDate) {
                    //?????????????????????????????????
                    dealWithResult(responseDate);
                }

                @Override
                public void onFail() {
                    Log.d(TAG, "onFail: ");
                }
            });

        }

    }


    private void dealWithResult(String responseDate) {

        if(newBeans!=null) newBeans.clear();
        newBeans = new Gson().fromJson(responseDate,PassageBean.class).getData().getDatas();
        for(int i =0;i<newBeans.size();i++){
            beans.add(newBeans.get(i));
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.setData(beans,key);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getDateFromLastActivity() {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        Log.d(TAG, key);
        searchET.setText(key);
    }

    private void initView() {

        loadingTv = findViewById(R.id.loading_tv);
        mLayout = findViewById(R.id.include);
        search = mLayout.findViewById(R.id.search_button);
        back = mLayout.findViewById(R.id.back);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        searchET = findViewById(R.id.search_editText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_button:{
                search();
            }break;
            case R.id.back:{
                SearchPassage.this.finish();
            }break;
        }
    }

    private void search(){
        String newKey = searchET.getText().toString();

        //??????????????????????????????????????????
        if(newKey.equals(key)){
            mMRecyclerView.scrollToPosition(0);
            mMonkeyAnim.setVisibility(View.VISIBLE);
            mMRecyclerView.setVisibility(View.GONE);
            mAnim.start();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAnim.stop();
                    mMonkeyAnim.setVisibility(View.GONE);
                    mMRecyclerView.setVisibility(View.VISIBLE);

                }
            },200);
            return;
        }else {
            beans.clear();
            key = newKey;
            currentPage = 0;
            pageTotal = 0;
            mMonkeyAnim.setVisibility(View.VISIBLE);
            mMRecyclerView.setVisibility(View.GONE);
            mAnim.start();

            if(isLoadingLaterRefresh){
                isLoadingLaterRefresh = false;
                mLottieNormal.setVisibility(View.VISIBLE);
                loadingTv.setText("???????????????...");
            }
            postKeyGetPassage();
        }

    }

    @Override
    public void finish() {
        super.finish();

    }
}