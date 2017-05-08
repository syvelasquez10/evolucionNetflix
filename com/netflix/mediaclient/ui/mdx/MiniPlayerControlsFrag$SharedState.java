// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;

class MiniPlayerControlsFrag$SharedState
{
    boolean controlsEnabled;
    boolean isEpisodeReady;
    boolean isVolumeEnabled;
    int mostRecentVolume;
    boolean shouldShowSelf;
    
    public void reset() {
        Log.d("MdxMiniPlayerFrag", "resetting shared state");
        this.shouldShowSelf = false;
        this.controlsEnabled = false;
        this.isEpisodeReady = false;
        this.isVolumeEnabled = false;
    }
}
