// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Build$VERSION;

public abstract class VolumeProviderCompat
{
    private VolumeProviderCompat$Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;
    
    public VolumeProviderCompat(final int mControlType, final int mMaxVolume, final int mCurrentVolume) {
        this.mControlType = mControlType;
        this.mMaxVolume = mMaxVolume;
        this.mCurrentVolume = mCurrentVolume;
    }
    
    public Object getVolumeProvider() {
        if (this.mVolumeProviderObj != null || Build$VERSION.SDK_INT < 21) {
            return this.mVolumeProviderObj;
        }
        return this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new VolumeProviderCompat$1(this));
    }
    
    public void onAdjustVolume(final int n) {
    }
    
    public void onSetVolumeTo(final int n) {
    }
    
    public final void setCurrentVolume(final int mCurrentVolume) {
        this.mCurrentVolume = mCurrentVolume;
        final Object volumeProvider = this.getVolumeProvider();
        if (volumeProvider != null) {
            VolumeProviderCompatApi21.setCurrentVolume(volumeProvider, mCurrentVolume);
        }
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }
}
