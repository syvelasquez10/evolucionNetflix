// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;

public interface DashTrackSelector
{
    void selectTracks(final MediaPresentationDescription p0, final int p1, final DashTrackSelector$Output p2);
}
