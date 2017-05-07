// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Context;

abstract class SystemMediaRouteProvider extends MediaRouteProvider
{
    protected SystemMediaRouteProvider(final Context context) {
        super(context, new MediaRouteProvider$ProviderMetadata(new ComponentName("android", SystemMediaRouteProvider.class.getName())));
    }
    
    public static SystemMediaRouteProvider obtain(final Context context, final SystemMediaRouteProvider$SyncCallback systemMediaRouteProvider$SyncCallback) {
        if (Build$VERSION.SDK_INT >= 18) {
            return new SystemMediaRouteProvider$JellybeanMr2Impl(context, systemMediaRouteProvider$SyncCallback);
        }
        if (Build$VERSION.SDK_INT >= 17) {
            return new SystemMediaRouteProvider$JellybeanMr1Impl(context, systemMediaRouteProvider$SyncCallback);
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new SystemMediaRouteProvider$JellybeanImpl(context, systemMediaRouteProvider$SyncCallback);
        }
        return new SystemMediaRouteProvider$LegacyImpl(context);
    }
    
    public void onSyncRouteAdded(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onSyncRouteChanged(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onSyncRouteRemoved(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
    
    public void onSyncRouteSelected(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
    }
}
