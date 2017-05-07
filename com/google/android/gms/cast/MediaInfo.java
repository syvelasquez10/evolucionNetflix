// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.fe;
import org.json.JSONException;
import com.google.android.gms.internal.dh;
import android.text.TextUtils;
import org.json.JSONObject;

public final class MediaInfo
{
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    private final String kH;
    private int kI;
    private String kJ;
    private MediaMetadata kK;
    private long kL;
    private JSONObject kM;
    
    MediaInfo(final String kh) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)kh)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        this.kH = kh;
        this.kI = -1;
    }
    
    MediaInfo(final JSONObject jsonObject) throws JSONException {
        this.kH = jsonObject.getString("contentId");
        final String string = jsonObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.kI = 0;
        }
        else if ("BUFFERED".equals(string)) {
            this.kI = 1;
        }
        else if ("LIVE".equals(string)) {
            this.kI = 2;
        }
        else {
            this.kI = -1;
        }
        this.kJ = jsonObject.getString("contentType");
        if (jsonObject.has("metadata")) {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("metadata");
            (this.kK = new MediaMetadata(jsonObject2.getInt("metadataType"))).b(jsonObject2);
        }
        this.kL = dh.b(jsonObject.optDouble("duration", 0.0));
        this.kM = jsonObject.optJSONObject("customData");
    }
    
    void a(final MediaMetadata kk) {
        this.kK = kk;
    }
    
    void a(final JSONObject km) {
        this.kM = km;
    }
    
    void aO() throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)this.kH)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        if (TextUtils.isEmpty((CharSequence)this.kJ)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        if (this.kI == -1) {
            throw new IllegalArgumentException("a valid stream type must be specified");
        }
    }
    
    public JSONObject aP() {
        JSONObject jsonObject = null;
    Label_0140:
        while (true) {
            jsonObject = new JSONObject();
            while (true) {
                Label_0142: {
                    try {
                        jsonObject.put("contentId", (Object)this.kH);
                        switch (this.kI) {
                            case 2: {
                                final String s = "LIVE";
                                jsonObject.put("streamType", (Object)s);
                                if (this.kJ != null) {
                                    jsonObject.put("contentType", (Object)this.kJ);
                                }
                                if (this.kK != null) {
                                    jsonObject.put("metadata", (Object)this.kK.aP());
                                }
                                jsonObject.put("duration", dh.h(this.kL));
                                if (this.kM != null) {
                                    jsonObject.put("customData", (Object)this.kM);
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
                if (this.kM == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                int n2;
                if (mediaInfo.kM == null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                b3 = b2;
                if (n == n2) {
                    if (this.kM != null && mediaInfo.kM != null) {
                        b3 = b2;
                        if (!fe.d(this.kM, mediaInfo.kM)) {
                            return b3;
                        }
                    }
                    return dh.a(this.kH, mediaInfo.kH) && this.kI == mediaInfo.kI && dh.a(this.kJ, mediaInfo.kJ) && dh.a(this.kK, mediaInfo.kK) && this.kL == mediaInfo.kL && b;
                }
            }
        }
        return b3;
    }
    
    void f(final long kl) throws IllegalArgumentException {
        if (kl < 0L) {
            throw new IllegalArgumentException("Stream duration cannot be negative");
        }
        this.kL = kl;
    }
    
    public String getContentId() {
        return this.kH;
    }
    
    public String getContentType() {
        return this.kJ;
    }
    
    public JSONObject getCustomData() {
        return this.kM;
    }
    
    public MediaMetadata getMetadata() {
        return this.kK;
    }
    
    public long getStreamDuration() {
        return this.kL;
    }
    
    public int getStreamType() {
        return this.kI;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.kH, this.kI, this.kJ, this.kK, this.kL, String.valueOf(this.kM));
    }
    
    void setContentType(final String kj) throws IllegalArgumentException {
        if (TextUtils.isEmpty((CharSequence)kj)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        this.kJ = kj;
    }
    
    void setStreamType(final int ki) throws IllegalArgumentException {
        if (ki < -1 || ki > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.kI = ki;
    }
    
    public static class Builder
    {
        private final MediaInfo kN;
        
        public Builder(final String s) throws IllegalArgumentException {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("Content ID cannot be empty");
            }
            this.kN = new MediaInfo(s);
        }
        
        public MediaInfo build() throws IllegalArgumentException {
            this.kN.aO();
            return this.kN;
        }
        
        public Builder setContentType(final String contentType) throws IllegalArgumentException {
            this.kN.setContentType(contentType);
            return this;
        }
        
        public Builder setCustomData(final JSONObject jsonObject) {
            this.kN.a(jsonObject);
            return this;
        }
        
        public Builder setMetadata(final MediaMetadata mediaMetadata) {
            this.kN.a(mediaMetadata);
            return this;
        }
        
        public Builder setStreamDuration(final long n) throws IllegalArgumentException {
            this.kN.f(n);
            return this;
        }
        
        public Builder setStreamType(final int streamType) throws IllegalArgumentException {
            this.kN.setStreamType(streamType);
            return this;
        }
    }
}
