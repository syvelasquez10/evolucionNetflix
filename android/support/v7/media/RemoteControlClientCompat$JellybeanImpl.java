// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Context;

class RemoteControlClientCompat$JellybeanImpl extends RemoteControlClientCompat
{
    private boolean mRegistered;
    private final Object mRouterObj;
    private final Object mUserRouteCategoryObj;
    private final Object mUserRouteObj;
    
    public RemoteControlClientCompat$JellybeanImpl(final Context context, final Object o) {
        super(context, o);
        this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
        this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, "", false);
        this.mUserRouteObj = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
    }
    
    @Override
    public void setPlaybackInfo(final RemoteControlClientCompat$PlaybackInfo remoteControlClientCompat$PlaybackInfo) {
        MediaRouterJellybean$UserRouteInfo.setVolume(this.mUserRouteObj, remoteControlClientCompat$PlaybackInfo.volume);
        MediaRouterJellybean$UserRouteInfo.setVolumeMax(this.mUserRouteObj, remoteControlClientCompat$PlaybackInfo.volumeMax);
        MediaRouterJellybean$UserRouteInfo.setVolumeHandling(this.mUserRouteObj, remoteControlClientCompat$PlaybackInfo.volumeHandling);
        MediaRouterJellybean$UserRouteInfo.setPlaybackStream(this.mUserRouteObj, remoteControlClientCompat$PlaybackInfo.playbackStream);
        MediaRouterJellybean$UserRouteInfo.setPlaybackType(this.mUserRouteObj, remoteControlClientCompat$PlaybackInfo.playbackType);
        if (!this.mRegistered) {
            this.mRegistered = true;
            MediaRouterJellybean$UserRouteInfo.setVolumeCallback(this.mUserRouteObj, MediaRouterJellybean.createVolumeCallback(new RemoteControlClientCompat$JellybeanImpl$VolumeCallbackWrapper(this)));
            MediaRouterJellybean$UserRouteInfo.setRemoteControlClient(this.mUserRouteObj, this.mRcc);
        }
    }
}
