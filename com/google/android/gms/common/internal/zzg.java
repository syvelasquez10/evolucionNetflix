// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

public class zzg implements DialogInterface$OnClickListener
{
    private final Activity mActivity;
    private final Intent mIntent;
    private final Fragment zzZW;
    private final int zzZX;
    
    public zzg(final Activity mActivity, final Intent mIntent, final int zzZX) {
        this.mActivity = mActivity;
        this.zzZW = null;
        this.mIntent = mIntent;
        this.zzZX = zzZX;
    }
    
    public zzg(final Fragment zzZW, final Intent mIntent, final int zzZX) {
        this.mActivity = null;
        this.zzZW = zzZW;
        this.mIntent = mIntent;
        this.zzZX = zzZX;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null && this.zzZW != null) {
                this.zzZW.startActivityForResult(this.mIntent, this.zzZX);
            }
            else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.zzZX);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
