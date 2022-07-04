package android.bignerdranch.playandroid.net;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.util.Log;

import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    public static void sendRequestWithOkHttp(final String address,final OkHttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run(){

                try {
                    URL url = new URL(address);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("hello", responseData);
                    listener.finish(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("hello", "连接失败 ");
                }


        }}).start();
    }
}
