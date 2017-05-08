// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class Realm$2$1$1 implements Runnable
{
    final /* synthetic */ Realm$2$1 this$2;
    
    Realm$2$1$1(final Realm$2$1 this$2) {
        this.this$2 = this$2;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.val$onSuccess.onSuccess();
    }
}
