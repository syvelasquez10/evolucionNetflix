// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

class RegisteredMediaRouteProviderWatcher$2 implements Runnable
{
    final /* synthetic */ RegisteredMediaRouteProviderWatcher this$0;
    
    RegisteredMediaRouteProviderWatcher$2(final RegisteredMediaRouteProviderWatcher this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.scanPackages();
    }
}
