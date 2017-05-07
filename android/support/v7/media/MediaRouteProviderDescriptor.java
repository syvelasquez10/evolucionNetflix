// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;

public final class MediaRouteProviderDescriptor
{
    private final Bundle mBundle;
    private List<MediaRouteDescriptor> mRoutes;
    
    private MediaRouteProviderDescriptor(final Bundle mBundle, final List<MediaRouteDescriptor> mRoutes) {
        this.mBundle = mBundle;
        this.mRoutes = mRoutes;
    }
    
    private void ensureRoutes() {
        if (this.mRoutes == null) {
            final ArrayList parcelableArrayList = this.mBundle.getParcelableArrayList("routes");
            if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
                this.mRoutes = Collections.emptyList();
            }
            else {
                final int size = parcelableArrayList.size();
                this.mRoutes = new ArrayList<MediaRouteDescriptor>(size);
                for (int i = 0; i < size; ++i) {
                    this.mRoutes.add(MediaRouteDescriptor.fromBundle(parcelableArrayList.get(i)));
                }
            }
        }
    }
    
    public static MediaRouteProviderDescriptor fromBundle(final Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteProviderDescriptor(bundle, null);
        }
        return null;
    }
    
    public List<MediaRouteDescriptor> getRoutes() {
        this.ensureRoutes();
        return this.mRoutes;
    }
    
    public boolean isValid() {
        this.ensureRoutes();
        for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
            final MediaRouteDescriptor mediaRouteDescriptor = this.mRoutes.get(i);
            if (mediaRouteDescriptor == null || !mediaRouteDescriptor.isValid()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaRouteProviderDescriptor{ ");
        sb.append("routes=").append(Arrays.toString(this.getRoutes().toArray()));
        sb.append(", isValid=").append(this.isValid());
        sb.append(" }");
        return sb.toString();
    }
}
