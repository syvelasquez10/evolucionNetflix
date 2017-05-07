// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import java.util.ArrayList;
import android.os.Bundle;

public final class MediaRouteProviderDescriptor$Builder
{
    private final Bundle mBundle;
    private ArrayList<MediaRouteDescriptor> mRoutes;
    
    public MediaRouteProviderDescriptor$Builder() {
        this.mBundle = new Bundle();
    }
    
    public MediaRouteProviderDescriptor$Builder addRoute(final MediaRouteDescriptor mediaRouteDescriptor) {
        if (mediaRouteDescriptor == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        if (this.mRoutes == null) {
            this.mRoutes = new ArrayList<MediaRouteDescriptor>();
        }
        else if (this.mRoutes.contains(mediaRouteDescriptor)) {
            throw new IllegalArgumentException("route descriptor already added");
        }
        this.mRoutes.add(mediaRouteDescriptor);
        return this;
    }
    
    public MediaRouteProviderDescriptor build() {
        if (this.mRoutes != null) {
            final int size = this.mRoutes.size();
            final ArrayList list = new ArrayList<Bundle>(size);
            for (int i = 0; i < size; ++i) {
                list.add(this.mRoutes.get(i).asBundle());
            }
            this.mBundle.putParcelableArrayList("routes", list);
        }
        return new MediaRouteProviderDescriptor(this.mBundle, this.mRoutes, null);
    }
}
