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
    private final Fragment zzadh;
    private final int zzadi;
    
    public zzh(final Activity mActivity, final Intent mIntent, final int zzadi) {
        this.mActivity = mActivity;
        this.zzadh = null;
        this.mIntent = mIntent;
        this.zzadi = zzadi;
    }
    
    public zzh(final Fragment zzadh, final Intent mIntent, final int zzadi) {
        this.mActivity = null;
        this.zzadh = zzadh;
        this.mIntent = mIntent;
        this.zzadi = zzadi;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null && this.zzadh != null) {
                this.zzadh.startActivityForResult(this.mIntent, this.zzadi);
            }
            else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.zzadi);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
