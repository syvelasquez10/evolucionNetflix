// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

public class du implements DialogInterface$OnClickListener
{
    private final Activity gs;
    private final Intent mIntent;
    private final int oZ;
    
    public du(final Activity gs, final Intent mIntent, final int oz) {
        this.gs = gs;
        this.mIntent = mIntent;
        this.oZ = oz;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            if (this.mIntent != null) {
                this.gs.startActivityForResult(this.mIntent, this.oZ);
            }
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
