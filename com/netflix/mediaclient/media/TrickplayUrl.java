// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public final class TrickplayUrl implements Comparable<TrickplayUrl>
{
    protected static final String TAG = "TrickplayUrl";
    private int aspectX;
    private int aspectY;
    private int height;
    private String[] url;
    private int width;
    
    public TrickplayUrl(final JSONObject jsonObject) {
        int i = 0;
        this.width = JsonUtils.getInt(jsonObject, "width", 0);
        this.height = JsonUtils.getInt(jsonObject, "height", 0);
        this.aspectX = JsonUtils.getInt(jsonObject, "aspectX", 0);
        this.aspectY = JsonUtils.getInt(jsonObject, "aspectY", 0);
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "urls");
        if (jsonArray != null) {
            this.url = new String[jsonArray.length()];
            while (i < jsonArray.length()) {
                this.url[i] = jsonArray.getString(i);
                ++i;
            }
        }
    }
    
    @Override
    public int compareTo(final TrickplayUrl trickplayUrl) {
        if (this == trickplayUrl) {
            return 0;
        }
        return 1;
    }
    
    public float getAspect() {
        if (this.aspectY != 0) {
            return this.aspectX / this.aspectY;
        }
        return 0.0f;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String[] getUrl() {
        return this.url;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public String toString() {
        return "TrickplayUrl: width=" + this.width + ", height=" + this.height + " aspect=" + this.aspectX / this.aspectY + ", url:" + this.url;
    }
}
