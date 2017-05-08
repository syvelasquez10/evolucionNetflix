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
    public final void b() {
        dw.b("No-op transaction. Ignoring Transaction.stop() call.", new IllegalStateException("No-op transaction"));
    }
}
