package android.bignerdranch.playandroid.bottomnlottie.search;

import okhttp3.Response;

public interface ResponseListener {
    void response(Response response);

    void fail();
}
