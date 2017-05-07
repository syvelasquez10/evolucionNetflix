// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.common.internal.n;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.fitness.data.Session;

public class ViewSessionIntentBuilder
{
    private String Sj;
    private Session Sk;
    private boolean Sl;
    private final Context mContext;
    
    public ViewSessionIntentBuilder(final Context mContext) {
        this.Sl = false;
        this.mContext = mContext;
    }
    
    private Intent i(final Intent intent) {
        boolean b = false;
        if (this.Sj != null) {
            final Intent setPackage = new Intent(intent).setPackage(this.Sj);
            if (this.mContext.getPackageManager().resolveActivity(setPackage, 0) != null) {
                b = true;
            }
            if (b) {
                return setPackage;
            }
        }
        return intent;
    }
    
    public Intent build() {
        n.a(this.Sk != null, (Object)"Session must be set");
        final Intent intent = new Intent("vnd.google.fitness.VIEW");
        intent.setType(FitnessIntents.getSessionMimeType(this.Sk.getActivity()));
        c.a(this.Sk, intent, "vnd.google.fitness.session");
        if (!this.Sl) {
            this.Sj = this.Sk.getAppPackageName();
        }
        return this.i(intent);
    }
    
    public ViewSessionIntentBuilder setPreferredApplication(final String sj) {
        this.Sj = sj;
        this.Sl = true;
        return this;
    }
    
    public ViewSessionIntentBuilder setSession(final Session sk) {
        this.Sk = sk;
        return this;
    }
}
