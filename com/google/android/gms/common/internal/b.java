// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.DialogInterface$OnClickListener;

public class b implements DialogInterface$OnClickListener
{
    private final Fragment Ll;
    private final int Lm;
    private final Intent mIntent;
    private final Activity nr;
    
    public b(final Activity nr, final Intent mIntent, final int lm) {
        this.nr = nr;
        this.Ll = null;
        this.mIntent = mIntent;
        this.Lm = lm;
    }
    
    public b(final Fragment ll, final Intent mIntent, final int lm) {
        this.nr = null;
        this.Ll = ll;
        this.mIntent = mIntent;
        this.Lm = lm;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null && this.Ll != null) {
                this.Ll.startActivityForResult(this.mIntent, this.Lm);
            }
            else if (this.mIntent != null) {
                this.nr.startActivityForResult(this.mIntent, this.Lm);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
