// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

final class MediaRouter$GlobalMediaRouter$RemoteControlClientRecord implements RemoteControlClientCompat$VolumeCallback
{
    private boolean mDisconnected;
    private final RemoteControlClientCompat mRccCompat;
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    public MediaRouter$GlobalMediaRouter$RemoteControlClientRecord(final MediaRouter$GlobalMediaRouter this$0, final Object o) {
        this.this$0 = this$0;
        (this.mRccCompat = RemoteControlClientCompat.obtain(this$0.mApplicationContext, o)).setVolumeCallback(this);
        this.updatePlaybackInfo();
    }
    
    public void disconnect() {
        this.mDisconnected = true;
        this.mRccCompat.setVolumeCallback(null);
    }
    
    public Object getRemoteControlClient() {
        return this.mRccCompat.getRemoteControlClient();
    }
    
    @Override
    public void onVolumeSetRequest(final int n) {
        if (!this.mDisconnected && this.this$0.mSelectedRoute != null) {
            this.this$0.mSelectedRoute.requestSetVolume(n);
        }
    }
    
    @Override
    public void onVolumeUpdateRequest(final int n) {
        if (!this.mDisconnected && this.this$0.mSelectedRoute != null) {
            this.this$0.mSelectedRoute.requestUpdateVolume(n);
        }
    }
    
    public void updatePlaybackInfo() {
        this.mRccCompat.setPlaybackInfo(this.this$0.mPlaybackInfo);
    }
}
