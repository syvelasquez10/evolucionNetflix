// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Collection;
import android.content.IntentSender;
import android.view.Display;
import android.net.Uri;
import android.os.Bundle;
import android.content.IntentFilter;
import android.content.ComponentName;
import java.util.ArrayList;
import java.util.List;

public final class MediaRouter$ProviderInfo
{
    private MediaRouteProviderDescriptor mDescriptor;
    private final MediaRouteProvider$ProviderMetadata mMetadata;
    private final MediaRouteProvider mProviderInstance;
    private final List<MediaRouter$RouteInfo> mRoutes;
    
    MediaRouter$ProviderInfo(final MediaRouteProvider mProviderInstance) {
        this.mRoutes = new ArrayList<MediaRouter$RouteInfo>();
        this.mProviderInstance = mProviderInstance;
        this.mMetadata = mProviderInstance.getMetadata();
    }
    
    int findRouteByDescriptorId(final String s) {
        for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
            if (this.mRoutes.get(i).mDescriptorId.equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public ComponentName getComponentName() {
        return this.mMetadata.getComponentName();
    }
    
    public String getPackageName() {
        return this.mMetadata.getPackageName();
    }
    
    public MediaRouteProvider getProviderInstance() {
        MediaRouter.checkCallingThread();
        return this.mProviderInstance;
    }
    
    @Override
    public String toString() {
        return "MediaRouter.RouteProviderInfo{ packageName=" + this.getPackageName() + " }";
    }
    
    boolean updateDescriptor(final MediaRouteProviderDescriptor mDescriptor) {
        if (this.mDescriptor != mDescriptor) {
            this.mDescriptor = mDescriptor;
            return true;
        }
        return false;
    }
}
