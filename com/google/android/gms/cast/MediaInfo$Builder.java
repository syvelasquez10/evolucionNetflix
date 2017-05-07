// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import org.json.JSONObject;
import android.text.TextUtils;

public class MediaInfo$Builder
{
    private final MediaInfo Fm;
    
    public MediaInfo$Builder(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Content ID cannot be empty");
        }
        this.Fm = new MediaInfo(s);
    }
    
    public MediaInfo build() {
        this.Fm.fw();
        return this.Fm;
    }
    
    public MediaInfo$Builder setContentType(final String contentType) {
        this.Fm.setContentType(contentType);
        return this;
    }
    
    public MediaInfo$Builder setCustomData(final JSONObject customData) {
        this.Fm.setCustomData(customData);
        return this;
    }
    
    public MediaInfo$Builder setMediaTracks(final List<MediaTrack> list) {
        this.Fm.c(list);
        return this;
    }
    
    public MediaInfo$Builder setMetadata(final MediaMetadata mediaMetadata) {
        this.Fm.a(mediaMetadata);
        return this;
    }
    
    public MediaInfo$Builder setStreamDuration(final long n) {
        this.Fm.m(n);
        return this;
    }
    
    public MediaInfo$Builder setStreamType(final int streamType) {
        this.Fm.setStreamType(streamType);
        return this;
    }
    
    public MediaInfo$Builder setTextTrackStyle(final TextTrackStyle textTrackStyle) {
        this.Fm.setTextTrackStyle(textTrackStyle);
        return this;
    }
}
