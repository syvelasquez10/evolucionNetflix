// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;

class KongInteractivePostPlayManager$7 implements MediaPlayerWrapper$PlaybackEventsListener
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    
    KongInteractivePostPlayManager$7(final KongInteractivePostPlayManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPlaybackFinished() {
        if (Log.isLoggable()) {
            Log.d("KongInteractivePostPlayManager", "Media player completed (or failed)");
        }
    }
}
