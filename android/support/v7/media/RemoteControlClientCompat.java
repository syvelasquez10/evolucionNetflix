// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.ref.WeakReference;
import android.os.Build$VERSION;
import android.content.Context;

abstract class RemoteControlClientCompat
{
    protected final Context mContext;
    protected final Object mRcc;
    protected VolumeCallback mVolumeCallback;
    
    protected RemoteControlClientCompat(final Context mContext, final Object mRcc) {
        this.mContext = mContext;
        this.mRcc = mRcc;
    }
    
    public static RemoteControlClientCompat obtain(final Context context, final Object o) {
        if (Build$VERSION.SDK_INT >= 16) {
            return new JellybeanImpl(context, o);
        }
        return new LegacyImpl(context, o);
    }
    
    public Object getRemoteControlClient() {
        return this.mRcc;
    }
    
    public void setPlaybackInfo(final PlaybackInfo playbackInfo) {
    }
    
    public void setVolumeCallback(final VolumeCallback mVolumeCallback) {
        this.mVolumeCallback = mVolumeCallback;
    }
    
    static class JellybeanImpl extends RemoteControlClientCompat
    {
        private boolean mRegistered;
        private final Object mRouterObj;
        private final Object mUserRouteCategoryObj;
        private final Object mUserRouteObj;
        
        public JellybeanImpl(final Context context, final Object o) {
            super(context, o);
            this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
            this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, "", false);
            this.mUserRouteObj = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
        }
        
        @Override
        public void setPlaybackInfo(final PlaybackInfo playbackInfo) {
            MediaRouterJellybean.UserRouteInfo.setVolume(this.mUserRouteObj, playbackInfo.volume);
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(this.mUserRouteObj, playbackInfo.volumeMax);
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(this.mUserRouteObj, playbackInfo.volumeHandling);
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(this.mUserRouteObj, playbackInfo.playbackStream);
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(this.mUserRouteObj, playbackInfo.playbackType);
            if (!this.mRegistered) {
                this.mRegistered = true;
                MediaRouterJellybean.UserRouteInfo.setVolumeCallback(this.mUserRouteObj, MediaRouterJellybean.createVolumeCallback((MediaRouterJellybean.VolumeCallback)new VolumeCallbackWrapper(this)));
                MediaRouterJellybean.UserRouteInfo.setRemoteControlClient(this.mUserRouteObj, this.mRcc);
            }
        }
        
        private static final class VolumeCallbackWrapper implements MediaRouterJellybean.VolumeCallback
        {
            private final WeakReference<JellybeanImpl> mImplWeak;
            
            public VolumeCallbackWrapper(final JellybeanImpl jellybeanImpl) {
                this.mImplWeak = new WeakReference<JellybeanImpl>(jellybeanImpl);
            }
            
            @Override
            public void onVolumeSetRequest(final Object o, final int n) {
                final JellybeanImpl jellybeanImpl = this.mImplWeak.get();
                if (jellybeanImpl != null && jellybeanImpl.mVolumeCallback != null) {
                    jellybeanImpl.mVolumeCallback.onVolumeSetRequest(n);
                }
            }
            
            @Override
            public void onVolumeUpdateRequest(final Object o, final int n) {
                final JellybeanImpl jellybeanImpl = this.mImplWeak.get();
                if (jellybeanImpl != null && jellybeanImpl.mVolumeCallback != null) {
                    jellybeanImpl.mVolumeCallback.onVolumeUpdateRequest(n);
                }
            }
        }
    }
    
    static class LegacyImpl extends RemoteControlClientCompat
    {
        public LegacyImpl(final Context context, final Object o) {
            super(context, o);
        }
    }
    
    public static final class PlaybackInfo
    {
        public int playbackStream;
        public int playbackType;
        public int volume;
        public int volumeHandling;
        public int volumeMax;
        
        public PlaybackInfo() {
            this.volumeHandling = 0;
            this.playbackStream = 3;
            this.playbackType = 1;
        }
    }
    
    public interface VolumeCallback
    {
        void onVolumeSetRequest(final int p0);
        
        void onVolumeUpdateRequest(final int p0);
    }
}
