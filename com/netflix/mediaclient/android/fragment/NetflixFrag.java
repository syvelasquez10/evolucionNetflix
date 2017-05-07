// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.app.Fragment;

public abstract class NetflixFrag extends Fragment implements LoadingStatus, ManagerStatusListener
{
    private static final String TAG = "NetflixFrag";
    private boolean isDestroyed;
    protected LoadingStatusCallback mLoadingStatusCallback;
    
    public NetflixActivity getNetflixActivity() {
        return (NetflixActivity)this.getActivity();
    }
    
    protected boolean isDestroyed() {
        return this.isDestroyed;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable("NetflixFrag", 2)) {
            Log.v("NetflixFrag", "Creating frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        if (Log.isLoggable("NetflixFrag", 2)) {
            Log.v("NetflixFrag", "Destroying frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
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
