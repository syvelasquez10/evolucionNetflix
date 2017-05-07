// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Intent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.SparseArray;
import android.os.IBinder$DeathRecipient;

class RegisteredMediaRouteProvider$Connection$1 implements Runnable
{
    final /* synthetic */ RegisteredMediaRouteProvider$Connection this$1;
    
    RegisteredMediaRouteProvider$Connection$1(final RegisteredMediaRouteProvider$Connection this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.failPendingCallbacks();
    }
}
