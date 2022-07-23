package android.bignerdranch.playandroid.bottomnlottie.search.search_result;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendRequestWthOkhttp {

        public  static void postWithOkhttp(String url,String key,OkhttpListener listener){
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("k",key).build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    listener.onFail();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String responseDate = response.body().string();
                    listener.onResponse(responseDate);
                }
            });
        }


}
