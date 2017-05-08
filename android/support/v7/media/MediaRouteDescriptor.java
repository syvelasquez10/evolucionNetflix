// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Arrays;
import android.text.TextUtils;
import android.content.IntentSender;
import android.net.Uri;
import java.util.Collections;
import android.content.IntentFilter;
import java.util.List;
import android.os.Bundle;

public final class MediaRouteDescriptor
{
    final Bundle mBundle;
    List<IntentFilter> mControlFilters;
    
    MediaRouteDescriptor(final Bundle mBundle, final List<IntentFilter> mControlFilters) {
        this.mBundle = mBundle;
        this.mControlFilters = mControlFilters;
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
    
    public boolean canDisconnectAndKeepPlaying() {
        return this.mBundle.getBoolean("canDisconnect", false);
    }
    
    void ensureControlFilters() {
        if (this.mControlFilters == null) {
            this.mControlFilters = (List<IntentFilter>)this.mBundle.getParcelableArrayList("controlFilters");
            if (this.mControlFilters == null) {
                this.mControlFilters = Collections.emptyList();
            }
        }
    }
    
    public int getConnectionState() {
        return this.mBundle.getInt("connectionState", 0);
    }
    
    public List<IntentFilter> getControlFilters() {
        this.ensureControlFilters();
        return this.mControlFilters;
    }
    
    public String getDescription() {
        return this.mBundle.getString("status");
    }
    
    public int getDeviceType() {
        return this.mBundle.getInt("deviceType");
    }
    
    public Bundle getExtras() {
        return this.mBundle.getBundle("extras");
    }
    
    public List<String> getGroupMemberIds() {
        return (List<String>)this.mBundle.getStringArrayList("groupMemberIds");
    }
    
    public Uri getIconUri() {
        final String string = this.mBundle.getString("iconUri");
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }
    
    public String getId() {
        return this.mBundle.getString("id");
    }
    
    public int getMaxClientVersion() {
        return this.mBundle.getInt("maxClientVersion", Integer.MAX_VALUE);
    }
    
    public int getMinClientVersion() {
        return this.mBundle.getInt("minClientVersion", 1);
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
    
    public IntentSender getSettingsActivity() {
        return (IntentSender)this.mBundle.getParcelable("settingsIntent");
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
    
    @Deprecated
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
        sb.append(", groupMemberIds=").append(this.getGroupMemberIds());
        sb.append(", name=").append(this.getName());
        sb.append(", description=").append(this.getDescription());
        sb.append(", iconUri=").append(this.getIconUri());
        sb.append(", isEnabled=").append(this.isEnabled());
        sb.append(", isConnecting=").append(this.isConnecting());
        sb.append(", connectionState=").append(this.getConnectionState());
        sb.append(", controlFilters=").append(Arrays.toString(this.getControlFilters().toArray()));
        sb.append(", playbackType=").append(this.getPlaybackType());
        sb.append(", playbackStream=").append(this.getPlaybackStream());
        sb.append(", deviceType=").append(this.getDeviceType());
        sb.append(", volume=").append(this.getVolume());
        sb.append(", volumeMax=").append(this.getVolumeMax());
        sb.append(", volumeHandling=").append(this.getVolumeHandling());
        sb.append(", presentationDisplayId=").append(this.getPresentationDisplayId());
        sb.append(", extras=").append(this.getExtras());
        sb.append(", isValid=").append(this.isValid());
        sb.append(", minClientVersion=").append(this.getMinClientVersion());
        sb.append(", maxClientVersion=").append(this.getMaxClientVersion());
        sb.append(" }");
        return sb.toString();
    }
}
