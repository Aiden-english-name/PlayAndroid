package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.banner;

import java.util.ArrayList;
import java.util.List;

public class DataBeen {
    private static final List<DataBeen> testData = new ArrayList<>();
    public String imageRes;
    public String textRes;
    public String url;

    public String getImageRes() {
        return imageRes;
    }

    public String getUrl(){
        return url;

    }


    public DataBeen(String imageRes,String textRes,String url) {
        this.imageRes = imageRes;
        this.textRes = textRes;
        this.url = url;
    }

}
