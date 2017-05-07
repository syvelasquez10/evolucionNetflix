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
import android.content.Intent;
import android.content.BroadcastReceiver;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.content.Context;

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
