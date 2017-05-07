// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Arrays;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import android.content.IntentFilter;
import java.util.ArrayList;
import android.os.Bundle;

public final class MediaRouteDescriptor$Builder
{
    private final Bundle mBundle;
    private ArrayList<IntentFilter> mControlFilters;
    
    public MediaRouteDescriptor$Builder(final MediaRouteDescriptor mediaRouteDescriptor) {
        if (mediaRouteDescriptor == null) {
            throw new IllegalArgumentException("descriptor must not be null");
        }
        this.mBundle = new Bundle(mediaRouteDescriptor.mBundle);
        mediaRouteDescriptor.ensureControlFilters();
        if (!mediaRouteDescriptor.mControlFilters.isEmpty()) {
            this.mControlFilters = new ArrayList<IntentFilter>(mediaRouteDescriptor.mControlFilters);
        }
    }
    
    public MediaRouteDescriptor$Builder(final String id, final String name) {
        this.mBundle = new Bundle();
        this.setId(id);
        this.setName(name);
    }
    
    public MediaRouteDescriptor$Builder addControlFilter(final IntentFilter intentFilter) {
        if (intentFilter == null) {
            throw new IllegalArgumentException("filter must not be null");
        }
        if (this.mControlFilters == null) {
            this.mControlFilters = new ArrayList<IntentFilter>();
        }
        if (!this.mControlFilters.contains(intentFilter)) {
            this.mControlFilters.add(intentFilter);
        }
        return this;
    }
    
    public MediaRouteDescriptor$Builder addControlFilters(final Collection<IntentFilter> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("filters must not be null");
        }
        if (!collection.isEmpty()) {
            final Iterator<IntentFilter> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.addControlFilter(iterator.next());
            }
        }
        return this;
    }
    
    public MediaRouteDescriptor build() {
        if (this.mControlFilters != null) {
            this.mBundle.putParcelableArrayList("controlFilters", (ArrayList)this.mControlFilters);
        }
        return new MediaRouteDescriptor(this.mBundle, this.mControlFilters, null);
    }
    
    public MediaRouteDescriptor$Builder setConnecting(final boolean b) {
        this.mBundle.putBoolean("connecting", b);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setDescription(final String s) {
        this.mBundle.putString("status", s);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setEnabled(final boolean b) {
        this.mBundle.putBoolean("enabled", b);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setId(final String s) {
        this.mBundle.putString("id", s);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setName(final String s) {
        this.mBundle.putString("name", s);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setPlaybackStream(final int n) {
        this.mBundle.putInt("playbackStream", n);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setPlaybackType(final int n) {
        this.mBundle.putInt("playbackType", n);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setPresentationDisplayId(final int n) {
        this.mBundle.putInt("presentationDisplayId", n);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setVolume(final int n) {
        this.mBundle.putInt("volume", n);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setVolumeHandling(final int n) {
        this.mBundle.putInt("volumeHandling", n);
        return this;
    }
    
    public MediaRouteDescriptor$Builder setVolumeMax(final int n) {
        this.mBundle.putInt("volumeMax", n);
        return this;
    }
}
