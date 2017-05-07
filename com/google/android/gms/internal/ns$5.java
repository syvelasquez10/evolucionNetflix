// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;

class ns$5 extends ns$a
{
    final /* synthetic */ ns alM;
    final /* synthetic */ String[] alO;
    
    ns$5(final ns alM, final String[] alO) {
        this.alM = alM;
        this.alO = alO;
        super((ns$1)null);
    }
    
    @Override
    protected void a(final e e) {
        e.d((BaseImplementation$b<People$LoadPeopleResult>)this, this.alO);
    }
}
