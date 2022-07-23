package android.bignerdranch.playandroid.bottomnlottie.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.bignerdranch.playandroid.R;
import android.os.Bundle;
import android.view.WindowManager;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //防止输入法导致布局的形变
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        creaateFragment();
    }

    private void creaateFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_contain, searchFragment).commit();


    }
}