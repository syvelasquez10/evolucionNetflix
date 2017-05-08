// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.media.VolumeProviderCompat;

class MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 extends VolumeProviderCompat
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1;
    
    MediaRouter$GlobalMediaRouter$MediaSessionRecord$1(final MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1, final int n, final int n2, final int n3) {
        this.this$1 = this$1;
        super(n, n2, n3);
    }
    
    @Override
    public void onAdjustVolume(final int n) {
        this.this$1.this$0.mCallbackHandler.post((Runnable)new MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$2(this, n));
    }
    
    @Override
    public void onSetVolumeTo(final int n) {
        this.this$1.this$0.mCallbackHandler.post((Runnable)new MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$1(this, n));
    }
}
