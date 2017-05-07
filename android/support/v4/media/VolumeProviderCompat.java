// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

public abstract class VolumeProviderCompat
{
    private VolumeProviderCompat$Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    
    public VolumeProviderCompat(final int mControlType, final int mMaxVolume, final int mCurrentVolume) {
        this.mControlType = mControlType;
        this.mMaxVolume = mMaxVolume;
        this.mCurrentVolume = mCurrentVolume;
    }
    
    public final void setCurrentVolume(final int n) {
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }
}
