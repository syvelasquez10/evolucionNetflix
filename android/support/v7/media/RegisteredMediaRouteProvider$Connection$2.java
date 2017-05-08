// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

class RegisteredMediaRouteProvider$Connection$2 implements Runnable
{
    final /* synthetic */ RegisteredMediaRouteProvider$Connection this$1;
    
    RegisteredMediaRouteProvider$Connection$2(final RegisteredMediaRouteProvider$Connection this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.onConnectionDied(this.this$1);
    }
}
