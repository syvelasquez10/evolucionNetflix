// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

class MediaRouterJellybeanMr1 extends MediaRouterJellybean
{
    private static final String TAG = "MediaRouterJellybeanMr1";
    
    public static Object createCallback(final MediaRouterJellybeanMr1$Callback mediaRouterJellybeanMr1$Callback) {
        return new MediaRouterJellybeanMr1$CallbackProxy(mediaRouterJellybeanMr1$Callback);
    }
}
