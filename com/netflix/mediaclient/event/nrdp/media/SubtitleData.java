// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.media.SubtitleUrl;

public class SubtitleData extends BaseMediaEvent
{
    private static final String ATTR_URLS = "urls";
    public static final String TAG = "nf_subtitleData";
    public static final String TYPE = "subtitledata";
    private int mPosition;
    private SubtitleUrl[] mSubtitleUrls;
    
    public SubtitleData(final JSONObject jsonObject) {
        super("subtitledata", jsonObject);
    }
    
    public SubtitleUrl pop() {
        if (this.mPosition < this.mSubtitleUrls.length) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitleData", "Return URL from position " + this.mPosition);
            }
            return this.mSubtitleUrls[this.mPosition++];
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitleData", "Return null, URL not found for position " + this.mPosition + ", when array is " + this.mSubtitleUrls.length);
        }
        return null;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        int i = 0;
        if (jsonObject != null) {
            if (!jsonObject.has("urls")) {
                Log.w("nf_subtitleData", "Array of URLs not found!");
                this.mSubtitleUrls = new SubtitleUrl[0];
                return;
            }
            final JSONArray jsonArray = jsonObject.getJSONArray("urls");
            if (Log.isLoggable()) {
                Log.d("nf_subtitleData", "Array of URLs found: " + jsonArray.length());
            }
            this.mSubtitleUrls = new SubtitleUrl[jsonArray.length()];
            while (i < jsonArray.length()) {
                this.mSubtitleUrls[i] = new SubtitleUrl(jsonArray.getJSONObject(i));
                ++i;
            }
        }
    }
    
    public void reset() {
        this.mPosition = 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubtitleData [\n");
        int n = 1;
        final SubtitleUrl[] mSubtitleUrls = this.mSubtitleUrls;
        for (int length = mSubtitleUrls.length, i = 0; i < length; ++i) {
            final SubtitleUrl subtitleUrl = mSubtitleUrls[i];
            if (n != 0) {
                n = 0;
            }
            else {
                sb.append(",\n");
            }
            sb.append(subtitleUrl);
        }
        sb.append("\n]");
        return sb.toString();
    }
}
