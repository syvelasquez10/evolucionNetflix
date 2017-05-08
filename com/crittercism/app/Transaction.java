// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import com.crittercism.internal.bc;
import com.crittercism.internal.dw;
import com.crittercism.internal.ax;

public abstract class Transaction
{
    public ax a;
    
    public static Transaction a(final String s) {
        Label_0015: {
            if (s == null) {
                break Label_0015;
            }
            Label_0037: {
                if (s == null) {
                    break Label_0037;
                }
                try {
                    if (s.length() == 0) {
                        dw.b("Transaction was created with a null/zero-length name. Returning no-op transaction", new IllegalStateException("Transaction created with null/zero-length name"));
                        return new bc();
                    }
                    final ax c = ax.C();
                    if (c.b) {
                        if (c.g.a()) {
                            return new bc();
                        }
                        goto Label_0071;
                    }
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dw.b(t);
                    return new bc();
                }
            }
        }
        dw.b("Transaction was created before Crittercism.initialize() was called. Returning no-op transaction", new IllegalStateException("Transaction created before Crittercism.initialize()"));
        return new bc();
    }
    
    public abstract void a();
    
    public abstract void b();
}
