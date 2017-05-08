// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public abstract class di implements Runnable
{
    public abstract void a();
    
    @Override
    public final void run() {
        try {
            this.a();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
}
