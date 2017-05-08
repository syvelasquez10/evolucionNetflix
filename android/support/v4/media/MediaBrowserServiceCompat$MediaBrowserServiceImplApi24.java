// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.List;
import android.content.Context;
import android.os.Bundle;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi24 extends MediaBrowserServiceCompat$MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi24$ServiceCompatProxy
{
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    
    MediaBrowserServiceCompat$MediaBrowserServiceImplApi24(final MediaBrowserServiceCompat this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    public Bundle getBrowserRootHints() {
        return MediaBrowserServiceCompatApi24.getBrowserRootHints(this.mServiceObj);
    }
    
    @Override
    public void notifyChildrenChanged(final String s, final Bundle bundle) {
        if (bundle == null) {
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, s);
            return;
        }
        MediaBrowserServiceCompatApi24.notifyChildrenChanged(this.mServiceObj, s, bundle);
    }
    
    @Override
    public void onCreate() {
        MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi24.createService((Context)this.this$0, this));
    }
    
    @Override
    public void onLoadChildren(final String s, final MediaBrowserServiceCompatApi24$ResultWrapper mediaBrowserServiceCompatApi24$ResultWrapper, final Bundle bundle) {
        this.this$0.onLoadChildren(s, new MediaBrowserServiceCompat$MediaBrowserServiceImplApi24$1(this, s, mediaBrowserServiceCompatApi24$ResultWrapper), bundle);
    }
}
