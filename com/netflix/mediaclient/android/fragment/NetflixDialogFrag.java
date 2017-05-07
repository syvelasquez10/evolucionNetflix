// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import android.content.DialogInterface;
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
    
    public boolean isLoadingData() {
        return false;
    }
    
    public void onActivityCreated(final Bundle bundle) {
        final boolean showsDialog = this.getShowsDialog();
        if (this.getDialog() == null) {
            Log.w("NetflixDialogFrag", "Dialog is null upon activity creation! Setting shows dialog to false.");
            this.setShowsDialog(false);
        }
        super.onActivityCreated(bundle);
        this.setShowsDialog(showsDialog);
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
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        Log.d("NetflixDialogFrag", "NetflixDialogFrag is dismissed");
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
    
    public void setLoadingStatusCallback(final LoadingStatusCallback mLoadingStatusCallback) {
        if (!this.isLoadingData() && mLoadingStatusCallback != null) {
            mLoadingStatusCallback.onDataLoaded(CommonStatus.OK);
            return;
        }
        this.mLoadingStatusCallback = mLoadingStatusCallback;
    }
    
    public interface DialogCanceledListener
    {
        void onDialogCanceled(final NetflixDialogFrag p0);
    }
    
    public interface DialogCanceledListenerProvider
    {
        DialogCanceledListener getDialogCanceledListener();
    }
}
