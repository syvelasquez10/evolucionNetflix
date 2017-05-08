// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Context;

class SystemMediaRouteProvider$Api24Impl extends SystemMediaRouteProvider$JellybeanMr2Impl
{
    public SystemMediaRouteProvider$Api24Impl(final Context context, final SystemMediaRouteProvider$SyncCallback systemMediaRouteProvider$SyncCallback) {
        super(context, systemMediaRouteProvider$SyncCallback);
    }
    
    @Override
    protected void onBuildSystemRouteDescriptor(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, final MediaRouteDescriptor$Builder mediaRouteDescriptor$Builder) {
        super.onBuildSystemRouteDescriptor(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, mediaRouteDescriptor$Builder);
        mediaRouteDescriptor$Builder.setDeviceType(MediaRouterApi24$RouteInfo.getDeviceType(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
    }
}
