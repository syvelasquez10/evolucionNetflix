// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;

class ns$1 extends ns$a
{
    final /* synthetic */ String alE;
    final /* synthetic */ int alL;
    final /* synthetic */ ns alM;
    
    ns$1(final ns alM, final int alL, final String alE) {
        this.alM = alM;
        this.alL = alL;
        this.alE = alE;
        super((ns$1)null);
    }
    
    @Override
    protected void a(final e e) {
        this.a(e.a((BaseImplementation$b<People$LoadPeopleResult>)this, this.alL, this.alE));
    }
}
