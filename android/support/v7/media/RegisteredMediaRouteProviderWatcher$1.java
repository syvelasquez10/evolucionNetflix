// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.IntentFilter;
import android.content.pm.ServiceInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import android.os.Handler;
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
