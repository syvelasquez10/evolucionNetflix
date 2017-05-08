// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class RegisteredMediaRouteProviderWatcher$1 extends BroadcastReceiver
{
    final /* synthetic */ RegisteredMediaRouteProviderWatcher this$0;
    
    RegisteredMediaRouteProviderWatcher$1(final RegisteredMediaRouteProviderWatcher this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.scanPackages();
    }
}
