// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;
import android.content.Intent;
import android.net.Uri;
import android.os.ResultReceiver;
import android.os.Bundle;

class MediaSessionCompat$Callback$StubApi21 implements MediaSessionCompatApi21$Callback
{
    final /* synthetic */ MediaSessionCompat$Callback this$0;
    
    MediaSessionCompat$Callback$StubApi21(final MediaSessionCompat$Callback this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        this.this$0.onCommand(s, bundle, resultReceiver);
    }
    
    @Override
    public void onCustomAction(String s, Bundle bundle) {
        if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
            final Uri uri = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
            bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            this.this$0.onPlayFromUri(uri, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE")) {
            this.this$0.onPrepare();
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
            s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            this.this$0.onPrepareFromMediaId(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
            s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            this.this$0.onPrepareFromSearch(s, bundle);
            return;
        }
        if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
            final Uri uri2 = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
            bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
            this.this$0.onPrepareFromUri(uri2, bundle);
            return;
        }
        this.this$0.onCustomAction(s, bundle);
    }
    
    @Override
    public void onFastForward() {
        this.this$0.onFastForward();
    }
    
    @Override
    public boolean onMediaButtonEvent(final Intent intent) {
        return this.this$0.onMediaButtonEvent(intent);
    }
    
    @Override
    public void onPause() {
        this.this$0.onPause();
    }
    
    @Override
    public void onPlay() {
        this.this$0.onPlay();
    }
    
    @Override
    public void onPlayFromMediaId(final String s, final Bundle bundle) {
        this.this$0.onPlayFromMediaId(s, bundle);
    }
    
    @Override
    public void onPlayFromSearch(final String s, final Bundle bundle) {
        this.this$0.onPlayFromSearch(s, bundle);
    }
    
    @Override
    public void onRewind() {
        this.this$0.onRewind();
    }
    
    @Override
    public void onSeekTo(final long n) {
        this.this$0.onSeekTo(n);
    }
    
    @Override
    public void onSetRating(final Object o) {
        this.this$0.onSetRating(RatingCompat.fromRating(o));
    }
    
    @Override
    public void onSkipToNext() {
        this.this$0.onSkipToNext();
    }
    
    @Override
    public void onSkipToPrevious() {
        this.this$0.onSkipToPrevious();
    }
    
    @Override
    public void onSkipToQueueItem(final long n) {
        this.this$0.onSkipToQueueItem(n);
    }
    
    @Override
    public void onStop() {
        this.this$0.onStop();
    }
}
