// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.annotation.TargetApi;

@TargetApi(17)
final class MediaRouterJellybeanMr1
{
    public static Object createCallback(final MediaRouterJellybeanMr1$Callback mediaRouterJellybeanMr1$Callback) {
        return new MediaRouterJellybeanMr1$CallbackProxy(mediaRouterJellybeanMr1$Callback);
    }
}
