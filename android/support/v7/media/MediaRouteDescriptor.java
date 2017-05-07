// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.text.TextUtils;
import java.util.Collections;
import android.content.IntentFilter;
import java.util.List;
import android.os.Bundle;

public final class MediaRouteDescriptor
{
    private static final String KEY_CONNECTING = "connecting";
    private static final String KEY_CONTROL_FILTERS = "controlFilters";
    private static final String KEY_DESCRIPTION = "status";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PLAYBACK_STREAM = "playbackStream";
    private static final String KEY_PLAYBACK_TYPE = "playbackType";
    private static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
    private static final String KEY_VOLUME = "volume";
    private static final String KEY_VOLUME_HANDLING = "volumeHandling";
    private static final String KEY_VOLUME_MAX = "volumeMax";
    private final Bundle mBundle;
    private List<IntentFilter> mControlFilters;
    
    private MediaRouteDescriptor(final Bundle mBundle, final List<IntentFilter> mControlFilters) {
        this.mBundle = mBundle;
        this.mControlFilters = mControlFilters;
    }
    
    private void ensureControlFilters() {
        if (this.mControlFilters == null) {
            this.mControlFilters = (List<IntentFilter>)this.mBundle.getParcelableArrayList("controlFilters");
            if (this.mControlFilters == null) {
                this.mControlFilters = Collections.emptyList();
            }
        }
    }
    
    public static MediaRouteDescriptor fromBundle(final Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteDescriptor(bundle, null);
        }
        return null;
    }
    
    public Bundle asBundle() {
        return this.mBundle;
    }
    
    public List<IntentFilter> getControlFilters() {
        this.ensureControlFilters();
        return this.mControlFilters;
    }
    
    public String getDescription() {
        return this.mBundle.getString("status");
    }
    
    public Bundle getExtras() {
        return this.mBundle.getBundle("extras");
    }
    
    public String getId() {
        return this.mBundle.getString("id");
    }
    
    public String getName() {
        return this.mBundle.getString("name");
    }
    
    public int getPlaybackStream() {
        return this.mBundle.getInt("playbackStream", -1);
    }
    
    public int getPlaybackType() {
        return this.mBundle.getInt("playbackType", 1);
    }
    
    public int getPresentationDisplayId() {
        return this.mBundle.getInt("presentationDisplayId", -1);
    }
    
    public int getVolume() {
        return this.mBundle.getInt("volume");
    }
    
    public int getVolumeHandling() {
        return this.mBundle.getInt("volumeHandling", 0);
    }
    
    public int getVolumeMax() {
        return this.mBundle.getInt("volumeMax");
    }
    
    public boolean isConnecting() {
        return this.mBundle.getBoolean("connecting", false);
    }
    
    public boolean isEnabled() {
        return this.mBundle.getBoolean("enabled", true);
    }
    
    public boolean isValid() {
        this.ensureControlFilters();
        return !TextUtils.isEmpty((CharSequence)this.getId()) && !TextUtils.isEmpty((CharSequence)this.getName()) && !this.mControlFilters.contains(null);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaRouteDescriptor{ ");
        sb.append("id=").append(this.getId());
        sb.append(", name=").append(this.getName());
        sb.append(", description=").append(this.getDescription());
        sb.append(", isEnabled=").append(this.isEnabled());
        sb.append(", isConnecting=").append(this.isConnecting());
        sb.append(", controlFilters=").append(Arrays.toString(this.getControlFilters().toArray()));
        sb.append(", playbackType=").append(this.getPlaybackType());
        sb.append(", playbackStream=").append(this.getPlaybackStream());
        sb.append(", volume=").append(this.getVolume());
        sb.append(", volumeMax=").append(this.getVolumeMax());
        sb.append(", volumeHandling=").append(this.getVolumeHandling());
        sb.append(", presentationDisplayId=").append(this.getPresentationDisplayId());
        sb.append(", extras=").append(this.getExtras());
        sb.append(", isValid=").append(this.isValid());
        sb.append(" }");
        return sb.toString();
    }
    
    public static final class Builder
    {
        private final Bundle mBundle;
        private ArrayList<IntentFilter> mControlFilters;
        
        public Builder(final MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor == null) {
                throw new IllegalArgumentException("descriptor must not be null");
            }
            this.mBundle = new Bundle(mediaRouteDescriptor.mBundle);
            mediaRouteDescriptor.ensureControlFilters();
            if (!mediaRouteDescriptor.mControlFilters.isEmpty()) {
                this.mControlFilters = new ArrayList<IntentFilter>(mediaRouteDescriptor.mControlFilters);
            }
        }
        
        public Builder(final String id, final String name) {
            this.mBundle = new Bundle();
            this.setId(id);
            this.setName(name);
        }
        
        public Builder addControlFilter(final IntentFilter intentFilter) {
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
        
        public Builder addControlFilters(final Collection<IntentFilter> collection) {
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
        
        public Builder setConnecting(final boolean b) {
            this.mBundle.putBoolean("connecting", b);
            return this;
        }
        
        public Builder setDescription(final String s) {
            this.mBundle.putString("status", s);
            return this;
        }
        
        public Builder setEnabled(final boolean b) {
            this.mBundle.putBoolean("enabled", b);
            return this;
        }
        
        public Builder setExtras(final Bundle bundle) {
            this.mBundle.putBundle("extras", bundle);
            return this;
        }
        
        public Builder setId(final String s) {
            this.mBundle.putString("id", s);
            return this;
        }
        
        public Builder setName(final String s) {
            this.mBundle.putString("name", s);
            return this;
        }
        
        public Builder setPlaybackStream(final int n) {
            this.mBundle.putInt("playbackStream", n);
            return this;
        }
        
        public Builder setPlaybackType(final int n) {
            this.mBundle.putInt("playbackType", n);
            return this;
        }
        
        public Builder setPresentationDisplayId(final int n) {
            this.mBundle.putInt("presentationDisplayId", n);
            return this;
        }
        
        public Builder setVolume(final int n) {
            this.mBundle.putInt("volume", n);
            return this;
        }
        
        public Builder setVolumeHandling(final int n) {
            this.mBundle.putInt("volumeHandling", n);
            return this;
        }
        
        public Builder setVolumeMax(final int n) {
            this.mBundle.putInt("volumeMax", n);
            return this;
        }
    }
}
