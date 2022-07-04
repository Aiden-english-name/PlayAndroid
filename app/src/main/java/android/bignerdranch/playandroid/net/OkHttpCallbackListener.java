package android.bignerdranch.playandroid.net;

public interface OkHttpCallbackListener {
    void finish(String response);
    void onError(Exception e);
}
