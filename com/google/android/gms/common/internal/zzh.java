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

public class zzh implements DialogInterface$OnClickListener
{
    private final Activity mActivity;
    private final Intent mIntent;
    private final int zzaaY;
    private final Fragment zzafl;
    
    public zzh(final Activity mActivity, final Intent mIntent, final int zzaaY) {
        this.mActivity = mActivity;
        this.zzafl = null;
        this.mIntent = mIntent;
        this.zzaaY = zzaaY;
    }
    
    public zzh(final Fragment zzafl, final Intent mIntent, final int zzaaY) {
        this.mActivity = null;
        this.zzafl = zzafl;
        this.mIntent = mIntent;
        this.zzaaY = zzaaY;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null && this.zzafl != null) {
                this.zzafl.startActivityForResult(this.mIntent, this.zzaaY);
            }
            else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.zzaaY);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
