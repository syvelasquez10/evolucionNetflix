// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface$OnClickListener;

public class fd implements DialogInterface$OnClickListener
{
    private final int CV;
    private final Intent mIntent;
    private final Activity nS;
    
    public fd(final Activity ns, final Intent mIntent, final int cv) {
        this.nS = ns;
        this.mIntent = mIntent;
        this.CV = cv;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null) {
                this.nS.startActivityForResult(this.mIntent, this.CV);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
