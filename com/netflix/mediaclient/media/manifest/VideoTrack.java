// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.manifest;

import java.util.ArrayList;
import java.util.List;

public class VideoTrack
{
    public String new_track_id;
    public List<Stream> streams;
    public String track_id;
    public int type;
    
    public VideoTrack() {
        this.streams = new ArrayList<Stream>();
    }
}
