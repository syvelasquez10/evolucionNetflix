// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

final class RegisteredMediaRouteProvider$Controller extends MediaRouteProvider$RouteController
{
    private RegisteredMediaRouteProvider$Connection mConnection;
    private int mControllerId;
    private int mPendingSetVolume;
    private int mPendingUpdateVolumeDelta;
    private final String mRouteGroupId;
    private final String mRouteId;
    private boolean mSelected;
    final /* synthetic */ RegisteredMediaRouteProvider this$0;
    
    public RegisteredMediaRouteProvider$Controller(final RegisteredMediaRouteProvider this$0, final String mRouteId, final String mRouteGroupId) {
        this.this$0 = this$0;
        this.mPendingSetVolume = -1;
        this.mRouteId = mRouteId;
        this.mRouteGroupId = mRouteGroupId;
    }
    
    public void attachConnection(final RegisteredMediaRouteProvider$Connection mConnection) {
        this.mConnection = mConnection;
        this.mControllerId = mConnection.createRouteController(this.mRouteId, this.mRouteGroupId);
        if (this.mSelected) {
            mConnection.selectRoute(this.mControllerId);
            if (this.mPendingSetVolume >= 0) {
                mConnection.setVolume(this.mControllerId, this.mPendingSetVolume);
                this.mPendingSetVolume = -1;
            }
            if (this.mPendingUpdateVolumeDelta != 0) {
                mConnection.updateVolume(this.mControllerId, this.mPendingUpdateVolumeDelta);
                this.mPendingUpdateVolumeDelta = 0;
            }
        }
    }
    
    public void detachConnection() {
        if (this.mConnection != null) {
            this.mConnection.releaseRouteController(this.mControllerId);
            this.mConnection = null;
            this.mControllerId = 0;
        }
    }
    
    @Override
    public void onRelease() {
        this.this$0.onControllerReleased(this);
    }
    
    @Override
    public void onSelect() {
        this.mSelected = true;
        if (this.mConnection != null) {
            this.mConnection.selectRoute(this.mControllerId);
        }
    }
    
    @Override
    public void onSetVolume(final int mPendingSetVolume) {
        if (this.mConnection != null) {
            this.mConnection.setVolume(this.mControllerId, mPendingSetVolume);
            return;
        }
        this.mPendingSetVolume = mPendingSetVolume;
        this.mPendingUpdateVolumeDelta = 0;
    }
    
    @Override
    public void onUnselect() {
        this.onUnselect(0);
    }
    
    @Override
    public void onUnselect(final int n) {
        this.mSelected = false;
        if (this.mConnection != null) {
            this.mConnection.unselectRoute(this.mControllerId, n);
        }
    }
    
    @Override
    public void onUpdateVolume(final int n) {
        if (this.mConnection != null) {
            this.mConnection.updateVolume(this.mControllerId, n);
            return;
        }
        this.mPendingUpdateVolumeDelta += n;
    }
}
