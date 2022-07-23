package android.bignerdranch.playandroid.bottomnlottie.search.bean;

import org.litepal.crud.LitePalSupport;

public class ItemBean extends LitePalSupport {
    String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
