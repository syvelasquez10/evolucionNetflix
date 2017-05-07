// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Messenger;
import android.os.IBinder;
import java.util.List;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.Intent;

final class RegisteredMediaRouteProvider$Controller extends MediaRouteProvider$RouteController
{
    private RegisteredMediaRouteProvider$Connection mConnection;
    private int mControllerId;
    private int mPendingSetVolume;
    private int mPendingUpdateVolumeDelta;
    private final String mRouteId;
    private boolean mSelected;
    final /* synthetic */ RegisteredMediaRouteProvider this$0;
    
    public RegisteredMediaRouteProvider$Controller(final RegisteredMediaRouteProvider this$0, final String mRouteId) {
        this.this$0 = this$0;
        this.mPendingSetVolume = -1;
        this.mRouteId = mRouteId;
    }
    
    public void attachConnection(final RegisteredMediaRouteProvider$Connection mConnection) {
        this.mConnection = mConnection;
        this.mControllerId = mConnection.createRouteController(this.mRouteId);
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
    public boolean onControlRequest(final Intent intent, final MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback) {
        return this.mConnection != null && this.mConnection.sendControlRequest(this.mControllerId, intent, mediaRouter$ControlRequestCallback);
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
        this.mSelected = false;
        if (this.mConnection != null) {
            this.mConnection.unselectRoute(this.mControllerId);
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
