// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.Moments$LoadMomentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;
import android.net.Uri;

class nr$2 extends nr$a
{
    final /* synthetic */ int YC;
    final /* synthetic */ nr alD;
    final /* synthetic */ String alE;
    final /* synthetic */ Uri alF;
    final /* synthetic */ String alG;
    final /* synthetic */ String alH;
    
    nr$2(final nr alD, final int yc, final String alE, final Uri alF, final String alG, final String alH) {
        this.alD = alD;
        this.YC = yc;
        this.alE = alE;
        this.alF = alF;
        this.alG = alG;
        this.alH = alH;
        super((nr$1)null);
    }
    
    @Override
    protected void a(final e e) {
        e.a((BaseImplementation$b<Moments$LoadMomentsResult>)this, this.YC, this.alE, this.alF, this.alG, this.alH);
    }
}
