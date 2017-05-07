// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.n;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;

class d$c implements Runnable
{
    private final int JD;
    private final ConnectionResult JE;
    final /* synthetic */ d JF;
    
    public d$c(final d jf, final int jd, final ConnectionResult je) {
        this.JF = jf;
        this.JD = jd;
        this.JE = je;
    }
    
    @Override
    public void run() {
        if (this.JE.hasResolution()) {
            try {
                this.JE.startResolutionForResult(this.JF.getActivity(), (this.JF.getActivity().getSupportFragmentManager().getFragments().indexOf(this.JF) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.JF.gv();
                return;
            }
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.JE.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.JE.getErrorCode(), this.JF.getActivity(), this.JF, 2, (DialogInterface$OnCancelListener)this.JF);
            return;
        }
        this.JF.b(this.JD, this.JE);
    }
}
