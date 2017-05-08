// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.ref.WeakReference;

final class RemoteControlClientCompat$JellybeanImpl$VolumeCallbackWrapper implements MediaRouterJellybean$VolumeCallback
{
    private final WeakReference<RemoteControlClientCompat$JellybeanImpl> mImplWeak;
    
    public RemoteControlClientCompat$JellybeanImpl$VolumeCallbackWrapper(final RemoteControlClientCompat$JellybeanImpl remoteControlClientCompat$JellybeanImpl) {
        this.mImplWeak = new WeakReference<RemoteControlClientCompat$JellybeanImpl>(remoteControlClientCompat$JellybeanImpl);
    }
    
    @Override
    public void onVolumeSetRequest(final Object o, final int n) {
        final RemoteControlClientCompat$JellybeanImpl remoteControlClientCompat$JellybeanImpl = this.mImplWeak.get();
        if (remoteControlClientCompat$JellybeanImpl != null && remoteControlClientCompat$JellybeanImpl.mVolumeCallback != null) {
            remoteControlClientCompat$JellybeanImpl.mVolumeCallback.onVolumeSetRequest(n);
        }
    }
    
    @Override
    public void onVolumeUpdateRequest(final Object o, final int n) {
        final RemoteControlClientCompat$JellybeanImpl remoteControlClientCompat$JellybeanImpl = this.mImplWeak.get();
        if (remoteControlClientCompat$JellybeanImpl != null && remoteControlClientCompat$JellybeanImpl.mVolumeCallback != null) {
            remoteControlClientCompat$JellybeanImpl.mVolumeCallback.onVolumeUpdateRequest(n);
        }
    }
}
