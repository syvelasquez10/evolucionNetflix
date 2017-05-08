// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import com.crittercism.app.Transaction;

public final class bc extends Transaction
{
    public bc() {
        dw.b("Creating no-op transaction");
    }
    
    @Override
    public final void a() {
        dw.b("No-op transaction. Ignoring Transaction.start() call.", new IllegalStateException("No-op transaction"));
    }
    
    @Override
    public final void a(final int n) {
        dw.b("No-op transaction. Ignoring Transaction.setValue(double) call.", new IllegalStateException("No-op transaction"));
    }
    
    @Override
    public final int a_() {
        dw.b("No-op transaction. Ignoring Transaction.getValue() call.", new IllegalStateException("No-op transaction"));
        return -1;
    }
    
    @Override
    public final void b() {
        dw.b("No-op transaction. Ignoring Transaction.stop() call.", new IllegalStateException("No-op transaction"));
    }
    
    @Override
    public final void c() {
        dw.b("No-op transaction. Ignoring Transaction.fail() call.", new IllegalStateException("No-op transaction"));
    }
    
    @Override
    public final void d() {
        dw.b("No-op transaction. Ignoring Transaction.cancel() call.", new IllegalStateException("No-op transaction"));
    }
}
