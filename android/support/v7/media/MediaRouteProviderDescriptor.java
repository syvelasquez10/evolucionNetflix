// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;

public final class MediaRouteProviderDescriptor
{
    private static final String KEY_ROUTES = "routes";
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
    
    public Bundle asBundle() {
        return this.mBundle;
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
    
    public static final class Builder
    {
        private final Bundle mBundle;
        private ArrayList<MediaRouteDescriptor> mRoutes;
        
        public Builder() {
            this.mBundle = new Bundle();
        }
        
        public Builder(final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (mediaRouteProviderDescriptor == null) {
                throw new IllegalArgumentException("descriptor must not be null");
            }
            this.mBundle = new Bundle(mediaRouteProviderDescriptor.mBundle);
            mediaRouteProviderDescriptor.ensureRoutes();
            if (!mediaRouteProviderDescriptor.mRoutes.isEmpty()) {
                this.mRoutes = new ArrayList<MediaRouteDescriptor>(mediaRouteProviderDescriptor.mRoutes);
            }
        }
        
        public Builder addRoute(final MediaRouteDescriptor mediaRouteDescriptor) {
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
        
        public Builder addRoutes(final Collection<MediaRouteDescriptor> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("routes must not be null");
            }
            if (!collection.isEmpty()) {
                final Iterator<MediaRouteDescriptor> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    this.addRoute(iterator.next());
                }
            }
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
}
