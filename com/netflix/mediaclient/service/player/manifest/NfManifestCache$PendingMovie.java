// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import java.util.ArrayList;
import java.util.List;

class NfManifestCache$PendingMovie
{
    private List<NfManifestCachePlaybackInterface$ManifestCacheCallback> mCallbacks;
    private int mPriority;
    final /* synthetic */ NfManifestCache this$0;
    
    NfManifestCache$PendingMovie(final NfManifestCache this$0, final int n, final NfManifestCachePlaybackInterface$ManifestCacheCallback nfManifestCachePlaybackInterface$ManifestCacheCallback) {
        this.this$0 = this$0;
        this.updatePriority(n);
        this.addCallback(nfManifestCachePlaybackInterface$ManifestCacheCallback);
    }
    
    void addCallback(final NfManifestCachePlaybackInterface$ManifestCacheCallback nfManifestCachePlaybackInterface$ManifestCacheCallback) {
        if (this.mCallbacks == null) {
            this.mCallbacks = new ArrayList<NfManifestCachePlaybackInterface$ManifestCacheCallback>();
        }
        if (nfManifestCachePlaybackInterface$ManifestCacheCallback != null) {
            this.mCallbacks.add(nfManifestCachePlaybackInterface$ManifestCacheCallback);
        }
    }
    
    List<NfManifestCachePlaybackInterface$ManifestCacheCallback> getmCallbacks() {
        return this.mCallbacks;
    }
    
    int getmPriority() {
        return this.mPriority;
    }
    
    void updatePriority(final int mPriority) {
        this.mPriority = mPriority;
    }
}
