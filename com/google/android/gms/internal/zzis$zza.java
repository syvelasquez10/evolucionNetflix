// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import android.content.MutableContextWrapper;

@zzgk
public class zzis$zza extends MutableContextWrapper
{
    private Activity zzIs;
    private Context zzJw;
    private Context zzqO;
    
    public zzis$zza(final Context baseContext) {
        super(baseContext);
        this.setBaseContext(baseContext);
    }
    
    public Object getSystemService(final String s) {
        return this.zzJw.getSystemService(s);
    }
    
    public void setBaseContext(final Context zzJw) {
        this.zzqO = zzJw.getApplicationContext();
        Activity zzIs;
        if (zzJw instanceof Activity) {
            zzIs = (Activity)zzJw;
        }
        else {
            zzIs = null;
        }
        this.zzIs = zzIs;
        this.zzJw = zzJw;
        super.setBaseContext(this.zzqO);
    }
    
    public void startActivity(final Intent intent) {
        if (this.zzIs != null && !zzlv.isAtLeastL()) {
            this.zzIs.startActivity(intent);
            return;
        }
        intent.setFlags(268435456);
        this.zzqO.startActivity(intent);
    }
    
    public Activity zzgN() {
        return this.zzIs;
    }
    
    public Context zzgO() {
        return this.zzJw;
    }
}
