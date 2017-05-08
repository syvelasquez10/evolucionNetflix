// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;

class BaseInteractiveMomentsManager$2 implements MediaPlayerWrapper$PlaybackEventsListener
{
    final /* synthetic */ BaseInteractiveMomentsManager this$0;
    final /* synthetic */ BaseInteractiveMomentsManager$PlaybackCompleteListener val$playbackCompleteListener;
    final /* synthetic */ String val$url;
    
    BaseInteractiveMomentsManager$2(final BaseInteractiveMomentsManager this$0, final String val$url, final BaseInteractiveMomentsManager$PlaybackCompleteListener val$playbackCompleteListener) {
        this.this$0 = this$0;
        this.val$url = val$url;
        this.val$playbackCompleteListener = val$playbackCompleteListener;
    }
    
    @Override
    public void onPlaybackError(final int n, final int n2) {
    }
    
    @Override
    public void onPlaybackFinished() {
        if (this.this$0.isActivityInvalid()) {
            return;
        }
        if (Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "Media player completed (or failed) - " + this.val$url);
        }
        if (this.this$0.urlToMediaPlayerMap != null && Log.isLoggable()) {
            Log.d("BaseInteractiveMomentsManager", "Media player size = " + this.this$0.urlToMediaPlayerMap.size());
        }
        if (this.val$playbackCompleteListener != null) {
            this.val$playbackCompleteListener.onComplete(this.val$url);
        }
        this.this$0.urlToMediaPlayerMap.remove(this.val$url);
    }
    
    @Override
    public void onPlaybackStarted() {
    }
    
    @Override
    public void onPlaybackSuccessfullyCompleted() {
    }
}
