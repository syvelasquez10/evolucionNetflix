// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

public interface MediaRouterJellybean$Callback
{
    void onRouteAdded(final Object p0);
    
    void onRouteChanged(final Object p0);
    
    void onRouteGrouped(final Object p0, final Object p1, final int p2);
    
    void onRouteRemoved(final Object p0);
    
    void onRouteSelected(final int p0, final Object p1);
    
    void onRouteUngrouped(final Object p0, final Object p1);
    
    void onRouteUnselected(final int p0, final Object p1);
    
    void onRouteVolumeChanged(final Object p0);
}
