// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.servicemgr.IMdxSharedState$MdxPlaybackState;
import java.util.HashMap;

final class MdxSharedState$1 extends HashMap<IMdxSharedState$MdxPlaybackState, String>
{
    MdxSharedState$1() {
        this.put(IMdxSharedState$MdxPlaybackState.Playing, "Playing");
        this.put(IMdxSharedState$MdxPlaybackState.Paused, "Paused");
        this.put(IMdxSharedState$MdxPlaybackState.Stopped, "Stopped");
        this.put(IMdxSharedState$MdxPlaybackState.Loading, "Loading");
        this.put(IMdxSharedState$MdxPlaybackState.Transitioning, "Transitioning");
    }
}
