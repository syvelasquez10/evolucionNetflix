// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

final class e$a extends a
{
    private final BaseImplementation$b<Status> alk;
    final /* synthetic */ e all;
    
    public e$a(final e all, final BaseImplementation$b<Status> alk) {
        this.all = all;
        this.alk = alk;
    }
    
    @Override
    public void aB(final Status status) {
        this.all.a(new e$d(this.alk, status));
    }
}
