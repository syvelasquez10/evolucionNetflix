// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.view.View;
import android.content.Context;
import android.view.View$OnClickListener;

final class a$5 implements View$OnClickListener
{
    final /* synthetic */ int Sa;
    final /* synthetic */ Context mV;
    
    a$5(final Context mv, final int sa) {
        this.mV = mv;
        this.Sa = sa;
    }
    
    public void onClick(final View view) {
        this.mV.startActivity(GooglePlayServicesUtil.c(this.mV, this.Sa));
    }
}
