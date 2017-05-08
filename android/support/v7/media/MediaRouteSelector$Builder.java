// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public final class MediaRouteSelector$Builder
{
    private ArrayList<String> mControlCategories;
    
    public MediaRouteSelector$Builder() {
    }
    
    public MediaRouteSelector$Builder(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        mediaRouteSelector.ensureControlCategories();
        if (!mediaRouteSelector.mControlCategories.isEmpty()) {
            this.mControlCategories = new ArrayList<String>(mediaRouteSelector.mControlCategories);
        }
    }
    
    public MediaRouteSelector$Builder addControlCategories(final Collection<String> collection) {
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
    
    public MediaRouteSelector$Builder addControlCategory(final String s) {
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
    
    public MediaRouteSelector$Builder addSelector(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        this.addControlCategories(mediaRouteSelector.getControlCategories());
        return this;
    }
    
    public MediaRouteSelector build() {
        if (this.mControlCategories == null) {
            return MediaRouteSelector.EMPTY;
        }
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("controlCategories", (ArrayList)this.mControlCategories);
        return new MediaRouteSelector(bundle, this.mControlCategories);
    }
}
