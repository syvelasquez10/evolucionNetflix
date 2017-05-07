// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

final class MediaRouter$CallbackRecord
{
    public final MediaRouter$Callback mCallback;
    public int mFlags;
    public final MediaRouter mRouter;
    public MediaRouteSelector mSelector;
    
    public MediaRouter$CallbackRecord(final MediaRouter mRouter, final MediaRouter$Callback mCallback) {
        this.mRouter = mRouter;
        this.mCallback = mCallback;
        this.mSelector = MediaRouteSelector.EMPTY;
    }
    
    public boolean filterRouteEvent(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        return (this.mFlags & 0x2) != 0x0 || mediaRouter$RouteInfo.matchesSelector(this.mSelector);
    }
}
