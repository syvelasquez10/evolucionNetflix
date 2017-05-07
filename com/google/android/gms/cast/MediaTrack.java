// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.Locale;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack
{
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    private long Dj;
    private int FD;
    private int FE;
    private String Fc;
    private String Fe;
    private String Fg;
    private JSONObject Fl;
    private String mName;
    
    MediaTrack(final long dj, final int fd) throws IllegalArgumentException {
        this.clear();
        this.Dj = dj;
        if (fd <= 0 || fd > 3) {
            throw new IllegalArgumentException("invalid type " + fd);
        }
        this.FD = fd;
    }
    
    MediaTrack(final JSONObject jsonObject) throws JSONException {
        this.c(jsonObject);
    }
    
    private void c(final JSONObject jsonObject) throws JSONException {
        this.clear();
        this.Dj = jsonObject.getLong("trackId");
        final String string = jsonObject.getString("type");
        if ("TEXT".equals(string)) {
            this.FD = 1;
        }
        else if ("AUDIO".equals(string)) {
            this.FD = 2;
        }
        else {
            if (!"VIDEO".equals(string)) {
                throw new JSONException("invalid type: " + string);
            }
            this.FD = 3;
        }
        this.Fe = jsonObject.optString("trackContentId", (String)null);
        this.Fg = jsonObject.optString("trackContentType", (String)null);
        this.mName = jsonObject.optString("name", (String)null);
        this.Fc = jsonObject.optString("language", (String)null);
        if (jsonObject.has("subtype")) {
            final String string2 = jsonObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.FE = 1;
            }
            else if ("CAPTIONS".equals(string2)) {
                this.FE = 2;
            }
            else if ("DESCRIPTIONS".equals(string2)) {
                this.FE = 3;
            }
            else if ("CHAPTERS".equals(string2)) {
                this.FE = 4;
            }
            else {
                if (!"METADATA".equals(string2)) {
                    throw new JSONException("invalid subtype: " + string2);
                }
                this.FE = 5;
            }
        }
        else {
            this.FE = 0;
        }
        this.Fl = jsonObject.optJSONObject("customData");
    }
    
    private void clear() {
        this.Dj = 0L;
        this.FD = 0;
        this.Fe = null;
        this.mName = null;
        this.Fc = null;
        this.FE = -1;
        this.Fl = null;
    }
    
    void aa(final int fe) throws IllegalArgumentException {
        if (fe <= -1 || fe > 5) {
            throw new IllegalArgumentException("invalid subtype " + fe);
        }
        if (fe != 0 && this.FD != 1) {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
        this.FE = fe;
    }
    
    public JSONObject bL() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("trackId", this.Dj);
            switch (this.FD) {
                case 1: {
                    jsonObject.put("type", (Object)"TEXT");
                    break;
                }
                case 2: {
                    jsonObject.put("type", (Object)"AUDIO");
                    break;
                }
                case 3: {
                    jsonObject.put("type", (Object)"VIDEO");
                    break;
                }
            }
            if (this.Fe != null) {
                jsonObject.put("trackContentId", (Object)this.Fe);
            }
            if (this.Fg != null) {
                jsonObject.put("trackContentType", (Object)this.Fg);
            }
            if (this.mName != null) {
                jsonObject.put("name", (Object)this.mName);
            }
            if (!TextUtils.isEmpty((CharSequence)this.Fc)) {
                jsonObject.put("language", (Object)this.Fc);
            }
            switch (this.FE) {
                case 1: {
                    jsonObject.put("subtype", (Object)"SUBTITLES");
                    break;
                }
                case 2: {
                    jsonObject.put("subtype", (Object)"CAPTIONS");
                    break;
                }
                case 3: {
                    jsonObject.put("subtype", (Object)"DESCRIPTIONS");
                    break;
                }
                case 4: {
                    jsonObject.put("subtype", (Object)"CHAPTERS");
                    break;
                }
                case 5: {
                    jsonObject.put("subtype", (Object)"METADATA");
                    break;
                }
            }
            if (this.Fl != null) {
                jsonObject.put("customData", (Object)this.Fl);
                return jsonObject;
            }
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
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
            if (o instanceof MediaTrack) {
                final MediaTrack mediaTrack = (MediaTrack)o;
                int n;
                if (this.Fl == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                int n2;
                if (mediaTrack.Fl == null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                b3 = b2;
                if (n == n2) {
                    if (this.Fl != null && mediaTrack.Fl != null) {
                        b3 = b2;
                        if (!jz.d(this.Fl, mediaTrack.Fl)) {
                            return b3;
                        }
                    }
                    return this.Dj == mediaTrack.Dj && this.FD == mediaTrack.FD && ik.a(this.Fe, mediaTrack.Fe) && ik.a(this.Fg, mediaTrack.Fg) && ik.a(this.mName, mediaTrack.mName) && ik.a(this.Fc, mediaTrack.Fc) && this.FE == mediaTrack.FE && b;
                }
            }
        }
        return b3;
    }
    
    public String getContentId() {
        return this.Fe;
    }
    
    public String getContentType() {
        return this.Fg;
    }
    
    public JSONObject getCustomData() {
        return this.Fl;
    }
    
    public long getId() {
        return this.Dj;
    }
    
    public String getLanguage() {
        return this.Fc;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public int getSubtype() {
        return this.FE;
    }
    
    public int getType() {
        return this.FD;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Dj, this.FD, this.Fe, this.Fg, this.mName, this.Fc, this.FE, this.Fl);
    }
    
    public void setContentId(final String fe) {
        this.Fe = fe;
    }
    
    public void setContentType(final String fg) {
        this.Fg = fg;
    }
    
    void setCustomData(final JSONObject fl) {
        this.Fl = fl;
    }
    
    void setLanguage(final String fc) {
        this.Fc = fc;
    }
    
    void setName(final String mName) {
        this.mName = mName;
    }
    
    public static class Builder
    {
        private final MediaTrack FF;
        
        public Builder(final long n, final int n2) throws IllegalArgumentException {
            this.FF = new MediaTrack(n, n2);
        }
        
        public MediaTrack build() {
            return this.FF;
        }
        
        public Builder setContentId(final String contentId) {
            this.FF.setContentId(contentId);
            return this;
        }
        
        public Builder setContentType(final String contentType) {
            this.FF.setContentType(contentType);
            return this;
        }
        
        public Builder setCustomData(final JSONObject customData) {
            this.FF.setCustomData(customData);
            return this;
        }
        
        public Builder setLanguage(final String language) {
            this.FF.setLanguage(language);
            return this;
        }
        
        public Builder setLanguage(final Locale locale) {
            this.FF.setLanguage(ik.b(locale));
            return this;
        }
        
        public Builder setName(final String name) {
            this.FF.setName(name);
            return this;
        }
        
        public Builder setSubtype(final int n) throws IllegalArgumentException {
            this.FF.aa(n);
            return this;
        }
    }
}
