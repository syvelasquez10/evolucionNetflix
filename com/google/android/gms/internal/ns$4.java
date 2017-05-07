// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;
import java.util.Collection;

class ns$4 extends ns$a
{
    final /* synthetic */ ns alM;
    final /* synthetic */ Collection alN;
    
    ns$4(final ns alM, final Collection alN) {
        this.alM = alM;
        this.alN = alN;
        super((ns$1)null);
    }
    
    @Override
    protected void a(final e e) {
        e.a((BaseImplementation$b<People$LoadPeopleResult>)this, this.alN);
    }
}
