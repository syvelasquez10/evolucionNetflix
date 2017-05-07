// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Arrays;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collection;
import android.support.annotation.NonNull;
import java.util.ArrayList;

public final class MediaRouteSelector$Builder
{
    private ArrayList<String> mControlCategories;
    
    public MediaRouteSelector$Builder() {
    }
    
    public MediaRouteSelector$Builder(@NonNull final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        mediaRouteSelector.ensureControlCategories();
        if (!mediaRouteSelector.mControlCategories.isEmpty()) {
            this.mControlCategories = new ArrayList<String>(mediaRouteSelector.mControlCategories);
        }
    }
    
    @NonNull
    public MediaRouteSelector$Builder addControlCategories(@NonNull final Collection<String> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("categories must not be null");
        }
        if (!collection.isEmpty()) {
            final Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.addControlCategory(iterator.next());
            }
        }
        return this;
    }
    
    @NonNull
    public MediaRouteSelector$Builder addControlCategory(@NonNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException("category must not be null");
        }
        if (this.mControlCategories == null) {
            this.mControlCategories = new ArrayList<String>();
        }
        if (!this.mControlCategories.contains(s)) {
            this.mControlCategories.add(s);
        }
        return this;
    }
    
    @NonNull
    public MediaRouteSelector$Builder addSelector(@NonNull final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        this.addControlCategories(mediaRouteSelector.getControlCategories());
        return this;
    }
    
    @NonNull
    public MediaRouteSelector build() {
        if (this.mControlCategories == null) {
            return MediaRouteSelector.EMPTY;
        }
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("controlCategories", (ArrayList)this.mControlCategories);
        return new MediaRouteSelector(bundle, this.mControlCategories, null);
    }
}
