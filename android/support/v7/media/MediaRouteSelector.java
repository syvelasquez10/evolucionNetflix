// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Arrays;
import android.content.IntentFilter;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import android.os.Bundle;

public final class MediaRouteSelector
{
    public static final MediaRouteSelector EMPTY;
    private final Bundle mBundle;
    List<String> mControlCategories;
    
    static {
        EMPTY = new MediaRouteSelector(new Bundle(), null);
    }
    
    MediaRouteSelector(final Bundle mBundle, final List<String> mControlCategories) {
        this.mBundle = mBundle;
        this.mControlCategories = mControlCategories;
    }
    
    public static MediaRouteSelector fromBundle(final Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteSelector(bundle, null);
        }
        return null;
    }
    
    public Bundle asBundle() {
        return this.mBundle;
    }
    
    public boolean contains(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector != null) {
            this.ensureControlCategories();
            mediaRouteSelector.ensureControlCategories();
            return this.mControlCategories.containsAll(mediaRouteSelector.mControlCategories);
        }
        return false;
    }
    
    void ensureControlCategories() {
        if (this.mControlCategories == null) {
            this.mControlCategories = (List<String>)this.mBundle.getStringArrayList("controlCategories");
            if (this.mControlCategories == null || this.mControlCategories.isEmpty()) {
                this.mControlCategories = Collections.emptyList();
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof MediaRouteSelector) {
            final MediaRouteSelector mediaRouteSelector = (MediaRouteSelector)o;
            this.ensureControlCategories();
            mediaRouteSelector.ensureControlCategories();
            return this.mControlCategories.equals(mediaRouteSelector.mControlCategories);
        }
        return false;
    }
    
    public List<String> getControlCategories() {
        this.ensureControlCategories();
        return this.mControlCategories;
    }
    
    @Override
    public int hashCode() {
        this.ensureControlCategories();
        return this.mControlCategories.hashCode();
    }
    
    public boolean isEmpty() {
        this.ensureControlCategories();
        return this.mControlCategories.isEmpty();
    }
    
    public boolean isValid() {
        this.ensureControlCategories();
        return !this.mControlCategories.contains(null);
    }
    
    public boolean matchesControlFilters(final List<IntentFilter> list) {
        if (list != null) {
            this.ensureControlCategories();
            final int size = this.mControlCategories.size();
            if (size != 0) {
                for (int size2 = list.size(), i = 0; i < size2; ++i) {
                    final IntentFilter intentFilter = list.get(i);
                    if (intentFilter != null) {
                        for (int j = 0; j < size; ++j) {
                            if (intentFilter.hasCategory((String)this.mControlCategories.get(j))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaRouteSelector{ ");
        sb.append("controlCategories=").append(Arrays.toString(this.getControlCategories().toArray()));
        sb.append(" }");
        return sb.toString();
    }
}
