// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

class Realm$2$1 implements Runnable
{
    final /* synthetic */ Realm$2 this$1;
    
    Realm$2$1(final Realm$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        final HandlerController handlerController = this.this$1.this$0.handlerController;
        Realm$2$1$1 realm$2$1$1;
        if (this.this$1.val$onSuccess != null) {
            realm$2$1$1 = new Realm$2$1$1(this);
        }
        else {
            realm$2$1$1 = null;
        }
        handlerController.handleAsyncTransactionCompleted(realm$2$1$1);
    }
}
