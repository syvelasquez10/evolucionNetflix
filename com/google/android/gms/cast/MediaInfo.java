// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.gp;
import org.json.JSONException;
import com.google.android.gms.internal.eo;
import android.text.TextUtils;
import org.json.JSONObject;

public final class MediaInfo
{
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    private final String yi;
    private int yj;
    private String yk;
    private MediaMetadata yl;
    private long ym;
    private JSONObject yn;
    
    MediaInfo(final String yi) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)yi)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        this.yi = yi;
        this.yj = -1;
    }
    
    MediaInfo(final JSONObject jsonObject) throws JSONException {
        this.yi = jsonObject.getString("contentId");
        final String string = jsonObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.yj = 0;
        }
        else if ("BUFFERED".equals(string)) {
            this.yj = 1;
        }
        else if ("LIVE".equals(string)) {
            this.yj = 2;
        }
        else {
            this.yj = -1;
        }
        this.yk = jsonObject.getString("contentType");
        if (jsonObject.has("metadata")) {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("metadata");
            (this.yl = new MediaMetadata(jsonObject2.getInt("metadataType"))).c(jsonObject2);
        }
        this.ym = eo.b(jsonObject.optDouble("duration", 0.0));
        this.yn = jsonObject.optJSONObject("customData");
    }
    
    void a(final MediaMetadata yl) {
        this.yl = yl;
    }
    
    void b(final JSONObject yn) {
        this.yn = yn;
    }
    
    void dA() throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)this.yi)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        if (TextUtils.isEmpty((CharSequence)this.yk)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        if (this.yj == -1) {
            throw new IllegalArgumentException("a valid stream type must be specified");
        }
    }
    
    public JSONObject dB() {
        JSONObject jsonObject = null;
    Label_0140:
        while (true) {
            jsonObject = new JSONObject();
            while (true) {
                Label_0142: {
                    try {
                        jsonObject.put("contentId", (Object)this.yi);
                        switch (this.yj) {
                            case 2: {
                                final String s = "LIVE";
                                jsonObject.put("streamType", (Object)s);
                                if (this.yk != null) {
                                    jsonObject.put("contentType", (Object)this.yk);
                                }
                                if (this.yl != null) {
                                    jsonObject.put("metadata", (Object)this.yl.dB());
                                }
                                jsonObject.put("duration", eo.m(this.ym));
                                if (this.yn != null) {
                                    jsonObject.put("customData", (Object)this.yn);
                                    return jsonObject;
                                }
                                break Label_0140;
                            }
                            case 1: {
                                break Label_0142;
                            }
                        }
                    }
                    catch (JSONException ex) {
                        return jsonObject;
                    }
                    final String s = "NONE";
                    continue;
                }
                final String s = "BUFFERED";
                continue;
            }
        }
        return jsonObject;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o instanceof MediaInfo) {
                final MediaInfo mediaInfo = (MediaInfo)o;
                int n;
                if (this.yn == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                int n2;
                if (mediaInfo.yn == null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                b3 = b2;
                if (n == n2) {
                    if (this.yn != null && mediaInfo.yn != null) {
                        b3 = b2;
                        if (!gp.d(this.yn, mediaInfo.yn)) {
                            return b3;
                        }
                    }
                    return eo.a(this.yi, mediaInfo.yi) && this.yj == mediaInfo.yj && eo.a(this.yk, mediaInfo.yk) && eo.a(this.yl, mediaInfo.yl) && this.ym == mediaInfo.ym && b;
                }
            }
        }
        return b3;
    }
    
    public String getContentId() {
        return this.yi;
    }
    
    public String getContentType() {
        return this.yk;
    }
    
    public JSONObject getCustomData() {
        return this.yn;
    }
    
    public MediaMetadata getMetadata() {
        return this.yl;
    }
    
    public long getStreamDuration() {
        return this.ym;
    }
    
    public int getStreamType() {
        return this.yj;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.yi, this.yj, this.yk, this.yl, this.ym, String.valueOf(this.yn));
    }
    
    void k(final long ym) throws IllegalArgumentException {
        if (ym < 0L) {
            throw new IllegalArgumentException("Stream duration cannot be negative");
        }
        this.ym = ym;
    }
    
    void setContentType(final String yk) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)yk)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        this.yk = yk;
    }
    
    void setStreamType(final int yj) throws IllegalArgumentException {
        if (yj < -1 || yj > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.yj = yj;
    }
    
    public static class Builder
    {
        private final MediaInfo yo;
        
        public Builder(final String s) throws IllegalArgumentException {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("Content ID cannot be empty");
            }
            this.yo = new MediaInfo(s);
        }
        
        public MediaInfo build() throws IllegalArgumentException {
            this.yo.dA();
            return this.yo;
        }
        
        public Builder setContentType(final String contentType) throws IllegalArgumentException {
            this.yo.setContentType(contentType);
            return this;
        }
        
        public Builder setCustomData(final JSONObject jsonObject) {
            this.yo.b(jsonObject);
            return this;
        }
        
        public Builder setMetadata(final MediaMetadata mediaMetadata) {
            this.yo.a(mediaMetadata);
            return this;
        }
        
        public Builder setStreamDuration(final long n) throws IllegalArgumentException {
            this.yo.k(n);
            return this;
        }
        
        public Builder setStreamType(final int streamType) throws IllegalArgumentException {
            this.yo.setStreamType(streamType);
            return this;
        }
    }
}
