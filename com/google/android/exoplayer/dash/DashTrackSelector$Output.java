// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;

public interface DashTrackSelector$Output
{
    void adaptiveTrack(final MediaPresentationDescription p0, final int p1, final int p2, final int[] p3);
    
    void fixedTrack(final MediaPresentationDescription p0, final int p1, final int p2, final int p3);
}
