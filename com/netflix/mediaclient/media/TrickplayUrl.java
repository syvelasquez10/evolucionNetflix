// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public final class TrickplayUrl implements Comparable<TrickplayUrl>
{
    protected static final String TAG = "TrickplayUrl";
    private int aspectX;
    private int aspectY;
    private final String downloadableId;
    private final long downloadableSize;
    private int height;
    private String[] url;
    private int width;
    
    public TrickplayUrl(final JSONObject jsonObject) {
        int i = 0;
        this.downloadableId = JsonUtils.getString(jsonObject, "downloadable_id", (String)null);
        this.downloadableSize = JsonUtils.getLong(jsonObject, "size", -1L);
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
    
    public String getDownloadableId() {
        return this.downloadableId;
    }
    
    public long getDownloadableSize() {
        return this.downloadableSize;
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
    
    public boolean hasAtLeastOneUrl() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.downloadableId != null) {
            b2 = b;
            if (this.url != null) {
                b2 = b;
                if (this.url.length > 0) {
                    b2 = b;
                    if (StringUtils.isNotEmpty(this.url[0])) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public String toString() {
        return "TrickplayUrl: width=" + this.width + ", height=" + this.height + " aspect=" + this.aspectX / this.aspectY + ", url:" + this.url;
    }
}
