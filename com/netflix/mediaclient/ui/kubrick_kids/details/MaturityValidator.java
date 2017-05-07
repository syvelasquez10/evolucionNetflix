// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.view.View$OnClickListener;
import android.support.design.widget.Snackbar;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class MaturityValidator
{
    private NetflixActivity activity;
    private IHandleBackPress handleBackPress;
    private VideoDetails videoDetails;
    
    MaturityValidator(final IHandleBackPress handleBackPress, final NetflixActivity activity, final VideoDetails videoDetails) {
        this.handleBackPress = handleBackPress;
        this.activity = activity;
        this.videoDetails = videoDetails;
    }
    
    public boolean isRestricted() {
        int n;
        if (this.videoDetails.getMaturityLevel() > this.activity.getServiceManager().getCurrentProfile().getMaturityLevel()) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            Snackbar.make(this.activity.findViewById(16908290), 2131165706, -2).setAction("BACK", (View$OnClickListener)new MaturityValidator$1(this)).show();
            return true;
        }
        return false;
    }
}
