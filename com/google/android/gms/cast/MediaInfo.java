// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.jz;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;
import com.google.android.gms.internal.ik;
import android.text.TextUtils;
import org.json.JSONObject;
import java.util.List;

public final class MediaInfo
{
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    private final String Fe;
    private int Ff;
    private String Fg;
    private MediaMetadata Fh;
    private long Fi;
    private List<MediaTrack> Fj;
    private TextTrackStyle Fk;
    private JSONObject Fl;
    
    MediaInfo(final String fe) {
        if (TextUtils.isEmpty((CharSequence)fe)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        this.Fe = fe;
        this.Ff = -1;
    }
    
    MediaInfo(final JSONObject jsonObject) {
        int i = 0;
        this.Fe = jsonObject.getString("contentId");
        final String string = jsonObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.Ff = 0;
        }
        else if ("BUFFERED".equals(string)) {
            this.Ff = 1;
        }
        else if ("LIVE".equals(string)) {
            this.Ff = 2;
        }
        else {
            this.Ff = -1;
        }
        this.Fg = jsonObject.getString("contentType");
        if (jsonObject.has("metadata")) {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("metadata");
            (this.Fh = new MediaMetadata(jsonObject2.getInt("metadataType"))).c(jsonObject2);
        }
        this.Fi = ik.b(jsonObject.optDouble("duration", 0.0));
        if (jsonObject.has("tracks")) {
            this.Fj = new ArrayList<MediaTrack>();
            for (JSONArray jsonArray = jsonObject.getJSONArray("tracks"); i < jsonArray.length(); ++i) {
                this.Fj.add(new MediaTrack(jsonArray.getJSONObject(i)));
            }
        }
        else {
            this.Fj = null;
        }
        if (jsonObject.has("textTrackStyle")) {
            final JSONObject jsonObject3 = jsonObject.getJSONObject("textTrackStyle");
            final TextTrackStyle fk = new TextTrackStyle();
            fk.c(jsonObject3);
            this.Fk = fk;
        }
        else {
            this.Fk = null;
        }
        this.Fl = jsonObject.optJSONObject("customData");
    }
    
    void a(final MediaMetadata fh) {
        this.Fh = fh;
    }
    
    public JSONObject bL() {
        JSONObject jsonObject;
        while (true) {
            jsonObject = new JSONObject();
            while (true) {
                Label_0223: {
                    Label_0217: {
                        try {
                            jsonObject.put("contentId", (Object)this.Fe);
                            switch (this.Ff) {
                                default: {
                                    final String s = "NONE";
                                    jsonObject.put("streamType", (Object)s);
                                    if (this.Fg != null) {
                                        jsonObject.put("contentType", (Object)this.Fg);
                                    }
                                    if (this.Fh != null) {
                                        jsonObject.put("metadata", (Object)this.Fh.bL());
                                    }
                                    jsonObject.put("duration", ik.o(this.Fi));
                                    if (this.Fj != null) {
                                        final JSONArray jsonArray = new JSONArray();
                                        final Iterator<MediaTrack> iterator = this.Fj.iterator();
                                        while (iterator.hasNext()) {
                                            jsonArray.put((Object)iterator.next().bL());
                                        }
                                        jsonObject.put("tracks", (Object)jsonArray);
                                    }
                                    if (this.Fk != null) {
                                        jsonObject.put("textTrackStyle", (Object)this.Fk.bL());
                                    }
                                    if (this.Fl != null) {
                                        jsonObject.put("customData", (Object)this.Fl);
                                        return jsonObject;
                                    }
                                    break;
                                }
                                case 1: {
                                    break Label_0217;
                                }
                                case 2: {
                                    break Label_0223;
                                }
                            }
                        }
                        catch (JSONException ex) {}
                        break;
                    }
                    final String s = "BUFFERED";
                    continue;
                }
                final String s = "LIVE";
                continue;
            }
        }
        return jsonObject;
    }
    
    void c(final List<MediaTrack> fj) {
        this.Fj = fj;
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
                if (this.Fl == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                int n2;
                if (mediaInfo.Fl == null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                b3 = b2;
                if (n == n2) {
                    if (this.Fl != null && mediaInfo.Fl != null) {
                        b3 = b2;
                        if (!jz.d(this.Fl, mediaInfo.Fl)) {
                            return b3;
                        }
                    }
                    return ik.a(this.Fe, mediaInfo.Fe) && this.Ff == mediaInfo.Ff && ik.a(this.Fg, mediaInfo.Fg) && ik.a(this.Fh, mediaInfo.Fh) && this.Fi == mediaInfo.Fi && b;
                }
            }
        }
        return b3;
    }
    
    void fw() {
        if (TextUtils.isEmpty((CharSequence)this.Fe)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        if (TextUtils.isEmpty((CharSequence)this.Fg)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        if (this.Ff == -1) {
            throw new IllegalArgumentException("a valid stream type must be specified");
        }
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
    
    public List<MediaTrack> getMediaTracks() {
        return this.Fj;
    }
    
    public MediaMetadata getMetadata() {
        return this.Fh;
    }
    
    public long getStreamDuration() {
        return this.Fi;
    }
    
    public int getStreamType() {
        return this.Ff;
    }
    
    public TextTrackStyle getTextTrackStyle() {
        return this.Fk;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Fe, this.Ff, this.Fg, this.Fh, this.Fi, String.valueOf(this.Fl));
    }
    
    void m(final long fi) {
        if (fi < 0L) {
            throw new IllegalArgumentException("Stream duration cannot be negative");
        }
        this.Fi = fi;
    }
    
    void setContentType(final String fg) {
        if (TextUtils.isEmpty((CharSequence)fg)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        this.Fg = fg;
    }
    
    void setCustomData(final JSONObject fl) {
        this.Fl = fl;
    }
    
    void setStreamType(final int ff) {
        if (ff < -1 || ff > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.Ff = ff;
    }
    
    public void setTextTrackStyle(final TextTrackStyle fk) {
        this.Fk = fk;
    }
}
