// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class Realm$2$2 implements Runnable
{
    final /* synthetic */ Realm$2 this$1;
    final /* synthetic */ Throwable val$backgroundException;
    
    Realm$2$2(final Realm$2 this$1, final Throwable val$backgroundException) {
        this.this$1 = this$1;
        this.val$backgroundException = val$backgroundException;
    }
    
    @Override
    public void run() {
        this.this$1.val$onError.onError(this.val$backgroundException);
    }
}
