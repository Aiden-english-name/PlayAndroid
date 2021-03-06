package android.bignerdranch.playandroid.bottomnlottie.search;

import android.app.ActionBar;
import android.bignerdranch.playandroid.R;
import android.bignerdranch.playandroid.bottomnlottie.search.bean.ItemBean;
import android.bignerdranch.playandroid.bottomnlottie.search.search_result.SearchPassage;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchFragment extends Fragment implements android.bignerdranch.playandroid.bottomnlottie.search.ResponseListener {

    private static final String TAG = "hello";
    android.bignerdranch.playandroid.bottomnlottie.search.OftenUsedWebsiteBean mOftenUsedWebsiteBean;
    private HotDateBean mHotDateBean;
    List<String> yuan_ma = new ArrayList<>();
    List<String> guan_fang = new ArrayList<>();
    List<String> cang_ku = new ArrayList<>();
    List<String> bo_ke = new ArrayList<>();
    List<String> ji_shu_zhan = new ArrayList<>();
    List<String> gong_ju = new ArrayList<>();
    List<String> mian_shi = new ArrayList<>();
    List<String> git = new ArrayList<>();
    List<String> kotlin = new ArrayList<>();
    List<String> xiang_mu = new ArrayList<>();
    List<String> she_ji = new ArrayList<>();
    List<String> jian_li = new ArrayList<>();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 1) {
                RecyclerView hotRecyclerView = mView.findViewById(R.id.hot_vocabulary);
                MyHotVocabularyAdapter myHotVocabularyAdapter = new MyHotVocabularyAdapter();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                hotRecyclerView.setLayoutManager(gridLayoutManager);
                hotRecyclerView.setAdapter(myHotVocabularyAdapter);
            }
            if(message.what == 2){
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        yuan_ma.add(mOftenUsedWebsiteBean.getData().get(j).getName());

                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        guan_fang.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }

                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        cang_ku.add(mOftenUsedWebsiteBean.getData().get(j).getName());

                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        bo_ke.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }

                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("?????????")){
                        ji_shu_zhan.add(mOftenUsedWebsiteBean.getData().get(j).getName());

                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        gong_ju.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }

                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        mian_shi.add(mOftenUsedWebsiteBean.getData().get(j).getName());

                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("Git")){
                        git.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }

                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("Kotlin")){
                        kotlin.add(mOftenUsedWebsiteBean.getData().get(j).getName());

                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        xiang_mu.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }

                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        she_ji.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }
                for (int j = 0 ;j < mOftenUsedWebsiteBean.getData().size() ;j++){
                    if(mOftenUsedWebsiteBean.getData().get(j).getCategory().equals("??????")){
                        jian_li.add(mOftenUsedWebsiteBean.getData().get(j).getName());
                        Log.d(TAG, mOftenUsedWebsiteBean.getData().get(j).getName());
                    }

                }
                RecyclerView recyclerView = getActivity().findViewById(R.id.recycle_parent);
                MyAdapter2 myAdapter = new MyAdapter2();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(myAdapter);
            }
            return false;
        }
    });
    private View mView;

    //????????????
    private MyAdapter mMyAdapter;
    boolean isCreated = false;
    List<String> list = new ArrayList<>();
    private List<String> mDate  = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView mDeleteAll;
    private TextView mHistorySearch;
    private View mView1;

    //?????????count?????????list???size???count??????????????????????????????

    @Override
    public void response(Response response) {
        try {
            String responseDate = response.body().string();
            mOftenUsedWebsiteBean = new Gson().fromJson(responseDate, OftenUsedWebsiteBean.class);

            Message message = new Message();
            message.what = 2;
            mHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail() {
        Log.d(TAG, "????????????");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        SendRequestWithOkhttpUtl.sendRequestWithOkhttp(this,"https://www.wanandroid.com/friend/json");
        mView = inflater.inflate(R.layout.fragment_second, container, false);
        ImageView delete = mView.findViewById(R.id.imageView4);
        EditText search = mView.findViewById(R.id.search_editText);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() != 0){
                    delete.setVisibility(View.VISIBLE);
                }else {
                    delete.setVisibility(View.INVISIBLE);
                }
            }
        });

        ImageView back = mView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        //?????????????????????dialog
        mDeleteAll = mView.findViewById(R.id.delete);
        mHistorySearch = mView.findViewById(R.id.history_search);
        mView1 = mView.findViewById(R.id.view);

        mDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDialog myDialog = new MyDialog(getActivity());
                myDialog.setsCancel("??????", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        LitePal.deleteAll(ItemBean.class);
                        list.clear();
                        updateDate();
                        myDialog.dismiss();

                    }
                }).setsConfirm("??????", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                }).show();
            }

        });


        //??????????????????????????????
        //????????????????????????????????????????????????
        if(!isCreated){
            isCreated = true;
            LitePal.getDatabase();
            //recycleView
            mRecyclerView = mView.findViewById(R.id.history_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            mMyAdapter = new MyAdapter(mDate,getActivity().getApplicationContext());
            mRecyclerView.setAdapter(mMyAdapter);
            updateDate();
        }

        //??????????????????????????????????????????
        List<ItemBean> beans= LitePal.findAll(ItemBean.class);
        for(ItemBean a:beans){
            list.add(a.getItem());
        }

        EditText editText = mView.findViewById(R.id.search_editText);

        //????????????
        TextView searchTextView = mView.findViewById(R.id.search_button);
        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //????????????????????????????????????????????????????????????????????????????????????????????????

                String content = editText.getText().toString();
                Intent intent = new Intent(getActivity(), SearchPassage.class);
                intent.putExtra("key",editText.getText().toString());

                getActivity().startActivity(intent);


                //??????,?????????
                if(content.equals("")) return;
                else{



                    //?????????????????????????????????????????????
                    List<ItemBean> itemBean = LitePal.findAll(ItemBean.class);

                    //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    boolean flag = false;
                    Log.d(TAG, itemBean.size()+"itemBean.size()");
                    for(ItemBean a:itemBean){
                        if(editText.getText().toString().equals(a.getItem())){
                            flag = true;
                            break;//????????????????????????????????????????????????????????????????????????
                        }
                    }

                    //count????????????????????????????????????count???????????????????????????count??????????????????sp??????
                    //??????????????????
                    if(flag){
                        //???????????????????????????????????????
                        Toast.makeText(getActivity(),"????????????????????????",Toast.LENGTH_SHORT).show();

                    }else {
//                        Log.d(TAG,"list.size() = "+list.size()+"");
//                        if(list.size()<10){
//                            //???????????????????????????????????????count??????10???????????????
//                            ItemBean item = new ItemBean();
//                            item.setItem(content);
//                            item.save();
//                            list.add(content);
//
//                        }else{
//                            //???????????????????????????????????????count??????10???????????????id count%10
//                            ItemBean item = new ItemBean();
//                            item.setItem(content);
//                            item.update(list.size()%10);
//                            item.save();
//                            list.add(content);
//
//                        }

                        ItemBean item = new ItemBean();
                        item.setItem(content);
                        item.save();
                        list.add(content);
                    }
                    mDeleteAll.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mHistorySearch.setVisibility(View.VISIBLE);
                    view.setVisibility(View.VISIBLE);
                    updateDate();

                }
            }
        });



        updateDate();

        sendRequestWithOkhttp();

        return mView;
    }




    public void updateDate(){


        mDate.clear();
        //?????????????????????list??????????????????????????????????????????10??????????????????
        //???count???????????????10?????????10,????????????????????????list???????????????????????????10?????????
        //??????????????????????????????????????????
        if(list.size()<10){
            //????????????????????????????????????
            //???????????????????????????
            for(int i = 0;i<list.size();i++){
                mDate.add(list.get(i));
            }

        }else {
            mDate.clear();
            for(int i = list.size()-10;i<list.size();i++){
                mDate.add(list.get(i));
            }

        }


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMyAdapter.notifyDataSetChanged();
            }
        });
        if(mDate.size() == 0){
            mDeleteAll.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mHistorySearch.setVisibility(View.GONE);
            mView1.setVisibility(View.GONE);
        }else {
            mDeleteAll.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mHistorySearch.setVisibility(View.VISIBLE);
            mView1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


    }

    //??????????????????
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        List<String> mStringList;
        Context mContext;

        public MyAdapter(List<String> list, Context applicationContext) {
            this.mContext = applicationContext;
            this.mStringList  = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_view, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextView.setText(mStringList.get(position));
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.hot_vocabulary_textview);
            }
        }

    }

    //????????????
    class MyHotVocabularyAdapter extends RecyclerView.Adapter<MyHotVocabularyAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_view_hot, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextView.setText(mHotDateBean.getData().get(position).getName());
        }

        @Override
        public int getItemCount() {
            return 9;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.hot_vocabulary_textview);
            }
        }

    }

    private void sendRequestWithOkhttp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.wanandroid.com//hotkey/json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseDate = response.body().string();
                mHotDateBean = new Gson().fromJson(responseDate, HotDateBean.class);
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);

            }
        });
    }

    //??????
    class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_first,parent,false);


            int columnWidth;
            columnWidth = (int) (parent.getMeasuredWidth()/1.5f);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(columnWidth,RecyclerView.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(position == 0){

                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(yuan_ma);
                holder.topRecyclerView.setAdapter(myTopAdapter);
                holder.mTextViewTop.setText("??????");
                holder.mTextViewBottom.setText("??????");

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(guan_fang);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);

            }else if(position == 1){
                holder.mTextViewTop.setText("??????");
                holder.mTextViewBottom.setText("??????");
                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(cang_ku);
                holder.topRecyclerView.setAdapter(myTopAdapter);

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(bo_ke);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);
            }
            else if(position == 2){

                holder.mTextViewTop.setText("?????????");
                holder.mTextViewBottom.setText("??????");
                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(ji_shu_zhan);
                holder.topRecyclerView.setAdapter(myTopAdapter);

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(gong_ju);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);
            }
            else if(position == 3){
                holder.mTextViewTop.setText("??????");
                holder.mTextViewBottom.setText("Git");
                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(mian_shi);
                holder.topRecyclerView.setAdapter(myTopAdapter);

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(git);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);
            }
            else if(position == 4){
                holder.mTextViewTop.setText("Kotlin");
                holder.mTextViewBottom.setText("??????");
                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(kotlin);
                holder.topRecyclerView.setAdapter(myTopAdapter);

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(xiang_mu);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);
            }
            else if(position == 5){
                holder.mTextViewTop.setText("??????");
                holder.mTextViewBottom.setText("??????");
                holder.topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                MyTopAdapter myTopAdapter = new MyTopAdapter(she_ji);
                holder.topRecyclerView.setAdapter(myTopAdapter);

                MyBottomAdapter bottomAdapter = new MyBottomAdapter(jian_li);
                holder.bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                holder.bottomRecyclerView.setAdapter(bottomAdapter);
            }

        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            RecyclerView topRecyclerView;
            RecyclerView bottomRecyclerView;
            TextView mTextViewTop;
            TextView mTextViewBottom;

            public ViewHolder(@NonNull View itemView) {


                super(itemView);
                mTextViewTop = itemView.findViewById(R.id.top_title);
                mTextViewBottom = itemView.findViewById(R.id.bottom_title);
                topRecyclerView = itemView.findViewById(R.id.top);
                bottomRecyclerView = itemView.findViewById(R.id.bottom);
            }
        }
    }





    class MyBottomAdapter extends RecyclerView.Adapter<MyBottomAdapter.ViewHolder>{
        List<String> content = new ArrayList<>();

        public MyBottomAdapter(List<String> content){
            this.content = content;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.often_used_list,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextViewContent.setText(content.get(position));
            holder.mTextViewPosition.setText(position+1+"");
        }

        @Override
        public int getItemCount() {
            return content.size();
        }
        private class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextViewContent ;
            TextView mTextViewPosition ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextViewContent = itemView.findViewById(R.id.textView);
                mTextViewPosition = itemView.findViewById(R.id.textView2);
            }
        }
    }

    class MyTopAdapter extends RecyclerView.Adapter<MyTopAdapter.ViewHolder>{
        List<String> content = new ArrayList<>();

        public MyTopAdapter(List<String> content){
            this.content = content;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.often_used_list,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.mTextViewContent.setText(content.get(position));
            holder.mTextViewPosition.setText(position+1+"");
        }


        @Override
        public int getItemCount() {

            return content.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextViewContent ;
            TextView mTextViewPosition ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextViewContent = itemView.findViewById(R.id.textView);
                mTextViewPosition = itemView.findViewById(R.id.textView2);
            }
        }
    }
}