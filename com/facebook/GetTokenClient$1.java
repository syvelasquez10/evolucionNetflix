// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.Messenger;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Message;
import android.os.Handler;

class GetTokenClient$1 extends Handler
{
    final /* synthetic */ GetTokenClient this$0;
    
    GetTokenClient$1(final GetTokenClient this$0) {
        this.this$0 = this$0;
    }
    
    public void handleMessage(final Message message) {
        this.this$0.handleMessage(message);
    }
}
