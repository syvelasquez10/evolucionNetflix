// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.app.Fragment;

public abstract class NetflixFrag extends Fragment implements LoadingStatus, ManagerStatusListener
{
    private static final String TAG = "NetflixFrag";
    private boolean isDestroyed;
    protected LoadingStatus$LoadingStatusCallback mLoadingStatusCallback;
    
    public NetflixActivity getNetflixActivity() {
        return (NetflixActivity)this.getActivity();
    }
    
    public boolean isActivityValid() {
        final Activity activity = this.getActivity();
        return activity != null && !activity.isFinishing();
    }
    
    protected boolean isDestroyed() {
        return this.isDestroyed;
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (Log.isLoggable()) {
            Log.v("NetflixFrag", "Frag attached to activity: " + this.getClass().getSimpleName());
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Log.isLoggable()) {
            Log.v("NetflixFrag", "Creating frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        if (Log.isLoggable()) {
            Log.v("NetflixFrag", "Destroying frag: " + this.getClass().getSimpleName() + ", hash: " + this.hashCode());
        }
        this.isDestroyed = true;
    }
    
    public void onDetach() {
        super.onDetach();
        if (Log.isLoggable()) {
            Log.v("NetflixFrag", "Frag detached from activity: " + this.getClass().getSimpleName());
        }
    }
    
    protected void onLoaded(final Status status) {
        if (this.mLoadingStatusCallback != null) {
            this.mLoadingStatusCallback.onDataLoaded(status);
        }
    }
    
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
    
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
}
