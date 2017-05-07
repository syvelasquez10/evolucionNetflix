// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Build$VERSION;
import android.content.Context;

abstract class RemoteControlClientCompat
{
    protected final Context mContext;
    protected final Object mRcc;
    protected RemoteControlClientCompat$VolumeCallback mVolumeCallback;
    
    protected RemoteControlClientCompat(final Context mContext, final Object mRcc) {
        this.mContext = mContext;
        this.mRcc = mRcc;
    }
    
    public static RemoteControlClientCompat obtain(final Context context, final Object o) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new RemoteControlClientCompat$JellybeanImpl(context, o);
        }
        return new RemoteControlClientCompat$LegacyImpl(context, o);
    }
    
    public Object getRemoteControlClient() {
        return this.mRcc;
    }
    
    public void setPlaybackInfo(final RemoteControlClientCompat$PlaybackInfo remoteControlClientCompat$PlaybackInfo) {
    }
    
    public void setVolumeCallback(final RemoteControlClientCompat$VolumeCallback mVolumeCallback) {
        this.mVolumeCallback = mVolumeCallback;
    }
}
