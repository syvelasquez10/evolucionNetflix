// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;

class ns$2 extends ns$a
{
    final /* synthetic */ String alE;
    final /* synthetic */ ns alM;
    
    ns$2(final ns alM, final String alE) {
        this.alM = alM;
        this.alE = alE;
        super((ns$1)null);
    }
    
    @Override
    protected void a(final e e) {
        this.a(e.r((BaseImplementation$b<People$LoadPeopleResult>)this, this.alE));
    }
}
