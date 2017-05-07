// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import java.util.Collections;
import android.util.Log;
import java.util.Iterator;
import java.util.Locale;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;

final class MediaRouter$GlobalMediaRouter$ProviderCallback extends MediaRouteProvider$Callback
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    private MediaRouter$GlobalMediaRouter$ProviderCallback(final MediaRouter$GlobalMediaRouter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDescriptorChanged(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        this.this$0.updateProviderDescriptor(mediaRouteProvider, mediaRouteProviderDescriptor);
    }
}
