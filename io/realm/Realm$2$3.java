// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.exceptions.RealmException;

class Realm$2$3 implements Runnable
{
    final /* synthetic */ Realm$2 this$1;
    final /* synthetic */ Throwable val$backgroundException;
    
    Realm$2$3(final Realm$2 this$1, final Throwable val$backgroundException) {
        this.this$1 = this$1;
        this.val$backgroundException = val$backgroundException;
    }
    
    @Override
    public void run() {
        if (this.val$backgroundException instanceof RuntimeException) {
            throw (RuntimeException)this.val$backgroundException;
        }
        if (this.val$backgroundException instanceof Exception) {
            throw new RealmException("Async transaction failed", this.val$backgroundException);
        }
        if (this.val$backgroundException instanceof Error) {
            throw (Error)this.val$backgroundException;
        }
    }
}
