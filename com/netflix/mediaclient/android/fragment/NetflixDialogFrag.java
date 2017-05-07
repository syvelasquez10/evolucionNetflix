// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.app.DialogFragment;

public abstract class NetflixDialogFrag extends DialogFragment implements LoadingStatus, ManagerStatusListener
{
    private static final String TAG = "NetflixDialogFrag";
    private boolean isDestroyed;
    protected LoadingStatusCallback mLoadingStatusCallback;
    
    protected boolean isDestroyed() {
        return this.isDestroyed;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable("NetflixDialogFrag", 2)) {
            Log.v("NetflixDialogFrag", "Creating frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        if (Log.isLoggable("NetflixDialogFrag", 2)) {
            Log.v("NetflixDialogFrag", "Destroying frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
        this.isDestroyed = true;
    }
    
    protected void onLoaded(final int n) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(n);
        }
    }
    
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
    }
    
    public void setLoadingStatusCallback(final LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(0);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
}
