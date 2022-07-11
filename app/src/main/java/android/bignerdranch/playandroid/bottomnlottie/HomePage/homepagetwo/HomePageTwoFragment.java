package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo;

import android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage.MyRecycleViewAdapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.playandroid.R;

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