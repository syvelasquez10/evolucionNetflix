// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

public final class SystemMediaRouteProvider$JellybeanImpl$SystemRouteController extends MediaRouteProvider$RouteController
{
    private final Object mRouteObj;
    final /* synthetic */ SystemMediaRouteProvider$JellybeanImpl this$0;
    
    public SystemMediaRouteProvider$JellybeanImpl$SystemRouteController(final SystemMediaRouteProvider$JellybeanImpl this$0, final Object mRouteObj) {
        this.this$0 = this$0;
        this.mRouteObj = mRouteObj;
    }
    
    @Override
    public void onSetVolume(final int n) {
        MediaRouterJellybean$RouteInfo.requestSetVolume(this.mRouteObj, n);
    }
    
    @Override
    public void onUpdateVolume(final int n) {
        MediaRouterJellybean$RouteInfo.requestUpdateVolume(this.mRouteObj, n);
    }
}
