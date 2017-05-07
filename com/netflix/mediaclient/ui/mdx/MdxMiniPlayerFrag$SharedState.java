// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;

class MdxMiniPlayerFrag$SharedState
{
    boolean controlsEnabled;
    float currUserRating;
    boolean isEpisodeReady;
    boolean isVideoUnshared;
    boolean isVolumeEnabled;
    int mostRecentVolume;
    boolean shouldShowSelf;
    
    public void reset() {
        Log.v("MdxMiniPlayerFrag", "resetting shared state");
        this.shouldShowSelf = false;
        this.controlsEnabled = false;
        this.isEpisodeReady = false;
        this.isVideoUnshared = false;
        this.isVolumeEnabled = false;
        this.currUserRating = -1.0f;
    }
}
