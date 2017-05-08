// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Parcel;
import android.content.Context;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi23 extends MediaBrowserServiceCompat$MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23$ServiceCompatProxy
{
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplApi23(final MediaBrowserServiceCompat this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    public void onCreate() {
        MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi23.createService((Context)this.this$0, this));
    }
    
    @Override
    public void onLoadItem(final String s, final MediaBrowserServiceCompatApi21$ResultWrapper<Parcel> mediaBrowserServiceCompatApi21$ResultWrapper) {
        this.this$0.onLoadItem(s, new MediaBrowserServiceCompat$MediaBrowserServiceImplApi23$1(this, s, mediaBrowserServiceCompatApi21$ResultWrapper));
    }
}
