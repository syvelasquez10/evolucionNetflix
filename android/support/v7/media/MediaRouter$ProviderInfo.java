// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Collection;
import android.content.ContentResolver;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.os.Bundle;
import android.content.IntentFilter;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.content.ComponentName;
import java.util.ArrayList;
import android.content.res.Resources;

public final class MediaRouter$ProviderInfo
{
    private MediaRouteProviderDescriptor mDescriptor;
    private final MediaRouteProvider$ProviderMetadata mMetadata;
    private final MediaRouteProvider mProviderInstance;
    private Resources mResources;
    private boolean mResourcesNotAvailable;
    private final ArrayList<MediaRouter$RouteInfo> mRoutes;
    
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
    
    Resources getResources() {
        if (this.mResources == null && !this.mResourcesNotAvailable) {
            final String packageName = this.getPackageName();
            final Context providerContext = MediaRouter.sGlobal.getProviderContext(packageName);
            if (providerContext != null) {
                this.mResources = providerContext.getResources();
            }
            else {
                Log.w("MediaRouter", "Unable to obtain resources for route provider package: " + packageName);
                this.mResourcesNotAvailable = true;
            }
        }
        return this.mResources;
    }
    
    public List<MediaRouter$RouteInfo> getRoutes() {
        MediaRouter.checkCallingThread();
        return this.mRoutes;
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
