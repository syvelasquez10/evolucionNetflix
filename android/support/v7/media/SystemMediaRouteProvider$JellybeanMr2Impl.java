// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Context;

class SystemMediaRouteProvider$JellybeanMr2Impl extends SystemMediaRouteProvider$JellybeanMr1Impl
{
    public SystemMediaRouteProvider$JellybeanMr2Impl(final Context context, final SystemMediaRouteProvider$SyncCallback systemMediaRouteProvider$SyncCallback) {
        super(context, systemMediaRouteProvider$SyncCallback);
    }
    
    @Override
    protected Object getDefaultRoute() {
        return MediaRouterJellybeanMr2.getDefaultRoute(this.mRouterObj);
    }
    
    @Override
    protected boolean isConnecting(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord) {
        return MediaRouterJellybeanMr2$RouteInfo.isConnecting(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj);
    }
    
    @Override
    protected void onBuildSystemRouteDescriptor(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, final MediaRouteDescriptor$Builder mediaRouteDescriptor$Builder) {
        super.onBuildSystemRouteDescriptor(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, mediaRouteDescriptor$Builder);
        final CharSequence description = MediaRouterJellybeanMr2$RouteInfo.getDescription(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj);
        if (description != null) {
            mediaRouteDescriptor$Builder.setDescription(description.toString());
        }
    }
    
    @Override
    protected void selectRoute(final Object o) {
        MediaRouterJellybean.selectRoute(this.mRouterObj, 8388611, o);
    }
    
    @Override
    protected void updateCallback() {
        boolean b = true;
        if (this.mCallbackRegistered) {
            MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
        }
        this.mCallbackRegistered = true;
        final Object mRouterObj = this.mRouterObj;
        final int mRouteTypes = this.mRouteTypes;
        final Object mCallbackObj = this.mCallbackObj;
        if (!this.mActiveScan) {
            b = false;
        }
        MediaRouterJellybeanMr2.addCallback(mRouterObj, mRouteTypes, mCallbackObj, (b ? 1 : 0) | 0x2);
    }
    
    @Override
    protected void updateUserRouteProperties(final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord systemMediaRouteProvider$JellybeanImpl$UserRouteRecord) {
        super.updateUserRouteProperties(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord);
        MediaRouterJellybeanMr2$UserRouteInfo.setDescription(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getDescription());
    }
}
