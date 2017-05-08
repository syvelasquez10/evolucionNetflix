// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.manifest;

import java.util.ArrayList;
import java.util.List;

public class Stream
{
    public int bitrate;
    public String downloadable_id;
    public boolean isDrm;
    public String new_stream_id;
    public Long size;
    public String trackType;
    public int type;
    public List<Url> urls;
    
    public Stream() {
        this.urls = new ArrayList<Url>();
    }
}
