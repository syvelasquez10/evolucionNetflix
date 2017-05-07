// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Messenger;
import android.os.IBinder;
import java.util.List;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import android.content.ComponentName;
import android.content.ServiceConnection;

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
