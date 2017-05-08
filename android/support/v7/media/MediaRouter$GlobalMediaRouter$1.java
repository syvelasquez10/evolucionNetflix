// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;

class MediaRouter$GlobalMediaRouter$1 implements MediaSessionCompat$OnActiveChangeListener
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    MediaRouter$GlobalMediaRouter$1(final MediaRouter$GlobalMediaRouter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onActiveChanged() {
        if (this.this$0.mRccMediaSession != null) {
            if (!this.this$0.mRccMediaSession.isActive()) {
                this.this$0.removeRemoteControlClient(this.this$0.mRccMediaSession.getRemoteControlClient());
                return;
            }
            this.this$0.addRemoteControlClient(this.this$0.mRccMediaSession.getRemoteControlClient());
        }
    }
}
