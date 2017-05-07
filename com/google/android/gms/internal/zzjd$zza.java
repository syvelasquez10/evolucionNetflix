// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import android.content.MutableContextWrapper;

@zzgr
public class zzjd$zza extends MutableContextWrapper
{
    private Activity zzJn;
    private Context zzKC;
    private Context zzqZ;
    
    public zzjd$zza(final Context baseContext) {
        super(baseContext);
        this.setBaseContext(baseContext);
    }
    
    public Object getSystemService(final String s) {
        return this.zzKC.getSystemService(s);
    }
    
    public void setBaseContext(final Context zzKC) {
        this.zzqZ = zzKC.getApplicationContext();
        Activity zzJn;
        if (zzKC instanceof Activity) {
            zzJn = (Activity)zzKC;
        }
        else {
            zzJn = null;
        }
        this.zzJn = zzJn;
        this.zzKC = zzKC;
        super.setBaseContext(this.zzqZ);
    }
    
    public void startActivity(final Intent intent) {
        if (this.zzJn != null && !zzmx.isAtLeastL()) {
            this.zzJn.startActivity(intent);
            return;
        }
        intent.setFlags(268435456);
        this.zzqZ.startActivity(intent);
    }
    
    public Activity zzgZ() {
        return this.zzJn;
    }
    
    public Context zzha() {
        return this.zzKC;
    }
}
