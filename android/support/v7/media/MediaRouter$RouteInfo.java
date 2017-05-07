// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Collection;
import java.util.List;
import android.view.Display;
import android.os.Bundle;
import android.content.IntentFilter;
import java.util.ArrayList;

public final class MediaRouter$RouteInfo
{
    private boolean mConnecting;
    private final ArrayList<IntentFilter> mControlFilters;
    private String mDescription;
    private MediaRouteDescriptor mDescriptor;
    private final String mDescriptorId;
    private boolean mEnabled;
    private Bundle mExtras;
    private String mName;
    private int mPlaybackStream;
    private int mPlaybackType;
    private Display mPresentationDisplay;
    private int mPresentationDisplayId;
    private final MediaRouter$ProviderInfo mProvider;
    private final String mUniqueId;
    private int mVolume;
    private int mVolumeHandling;
    private int mVolumeMax;
    
    MediaRouter$RouteInfo(final MediaRouter$ProviderInfo mProvider, final String mDescriptorId, final String mUniqueId) {
        this.mControlFilters = new ArrayList<IntentFilter>();
        this.mPresentationDisplayId = -1;
        this.mProvider = mProvider;
        this.mDescriptorId = mDescriptorId;
        this.mUniqueId = mUniqueId;
    }
    
    public String getDescription() {
        return this.mDescription;
    }
    
    String getDescriptorId() {
        return this.mDescriptorId;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public String getId() {
        return this.mUniqueId;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public int getPlaybackStream() {
        return this.mPlaybackStream;
    }
    
    public int getPlaybackType() {
        return this.mPlaybackType;
    }
    
    MediaRouteProvider getProviderInstance() {
        return this.mProvider.getProviderInstance();
    }
    
    public int getVolume() {
        return this.mVolume;
    }
    
    public int getVolumeHandling() {
        return this.mVolumeHandling;
    }
    
    public int getVolumeMax() {
        return this.mVolumeMax;
    }
    
    public boolean isSelected() {
        MediaRouter.checkCallingThread();
        return MediaRouter.sGlobal.getSelectedRoute() == this;
    }
    
    public boolean matchesSelector(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        MediaRouter.checkCallingThread();
        return mediaRouteSelector.matchesControlFilters(this.mControlFilters);
    }
    
    public void requestSetVolume(final int n) {
        MediaRouter.checkCallingThread();
        MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, n)));
    }
    
    public void requestUpdateVolume(final int n) {
        MediaRouter.checkCallingThread();
        if (n != 0) {
            MediaRouter.sGlobal.requestUpdateVolume(this, n);
        }
    }
    
    public void select() {
        MediaRouter.checkCallingThread();
        MediaRouter.sGlobal.selectRoute(this);
    }
    
    public boolean supportsControlCategory(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("category must not be null");
        }
        MediaRouter.checkCallingThread();
        for (int size = this.mControlFilters.size(), i = 0; i < size; ++i) {
            if (this.mControlFilters.get(i).hasCategory(s)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "MediaRouter.RouteInfo{ uniqueId=" + this.mUniqueId + ", name=" + this.mName + ", description=" + this.mDescription + ", enabled=" + this.mEnabled + ", connecting=" + this.mConnecting + ", playbackType=" + this.mPlaybackType + ", playbackStream=" + this.mPlaybackStream + ", volumeHandling=" + this.mVolumeHandling + ", volume=" + this.mVolume + ", volumeMax=" + this.mVolumeMax + ", presentationDisplayId=" + this.mPresentationDisplayId + ", extras=" + this.mExtras + ", providerPackageName=" + this.mProvider.getPackageName() + " }";
    }
    
    int updateDescriptor(final MediaRouteDescriptor mDescriptor) {
        final boolean b = false;
        boolean b2 = false;
        int n = b ? 1 : 0;
        if (this.mDescriptor != mDescriptor) {
            this.mDescriptor = mDescriptor;
            n = (b ? 1 : 0);
            if (mDescriptor != null) {
                if (!MediaRouter.equal(this.mName, mDescriptor.getName())) {
                    this.mName = mDescriptor.getName();
                    b2 = true;
                }
                boolean b3 = b2;
                if (!MediaRouter.equal(this.mDescription, mDescriptor.getDescription())) {
                    this.mDescription = mDescriptor.getDescription();
                    b3 = (b2 | true);
                }
                boolean b4 = b3;
                if (this.mEnabled != mDescriptor.isEnabled()) {
                    this.mEnabled = mDescriptor.isEnabled();
                    b4 = (b3 | true);
                }
                boolean b5 = b4;
                if (this.mConnecting != mDescriptor.isConnecting()) {
                    this.mConnecting = mDescriptor.isConnecting();
                    b5 = (b4 | true);
                }
                boolean b6 = b5;
                if (!this.mControlFilters.equals(mDescriptor.getControlFilters())) {
                    this.mControlFilters.clear();
                    this.mControlFilters.addAll(mDescriptor.getControlFilters());
                    b6 = (b5 | true);
                }
                boolean b7 = b6;
                if (this.mPlaybackType != mDescriptor.getPlaybackType()) {
                    this.mPlaybackType = mDescriptor.getPlaybackType();
                    b7 = (b6 | true);
                }
                boolean b8 = b7;
                if (this.mPlaybackStream != mDescriptor.getPlaybackStream()) {
                    this.mPlaybackStream = mDescriptor.getPlaybackStream();
                    b8 = (b7 | true);
                }
                int n2 = b8 ? 1 : 0;
                if (this.mVolumeHandling != mDescriptor.getVolumeHandling()) {
                    this.mVolumeHandling = mDescriptor.getVolumeHandling();
                    n2 = ((b8 ? 1 : 0) | 0x3);
                }
                int n3 = n2;
                if (this.mVolume != mDescriptor.getVolume()) {
                    this.mVolume = mDescriptor.getVolume();
                    n3 = (n2 | 0x3);
                }
                int n4 = n3;
                if (this.mVolumeMax != mDescriptor.getVolumeMax()) {
                    this.mVolumeMax = mDescriptor.getVolumeMax();
                    n4 = (n3 | 0x3);
                }
                int n5 = n4;
                if (this.mPresentationDisplayId != mDescriptor.getPresentationDisplayId()) {
                    this.mPresentationDisplayId = mDescriptor.getPresentationDisplayId();
                    this.mPresentationDisplay = null;
                    n5 = (n4 | 0x5);
                }
                n = n5;
                if (!MediaRouter.equal(this.mExtras, mDescriptor.getExtras())) {
                    this.mExtras = mDescriptor.getExtras();
                    n = (n5 | 0x1);
                }
            }
        }
        return n;
    }
}
