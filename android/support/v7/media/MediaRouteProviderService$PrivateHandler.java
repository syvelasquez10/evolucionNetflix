// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import android.app.Service;
import android.os.Messenger;
import android.os.Message;
import android.os.Handler;

final class MediaRouteProviderService$PrivateHandler extends Handler
{
    final /* synthetic */ MediaRouteProviderService this$0;
    
    private MediaRouteProviderService$PrivateHandler(final MediaRouteProviderService this$0) {
        this.this$0 = this$0;
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {}
            case 1: {
                this.this$0.onBinderDied((Messenger)message.obj);
            }
        }
    }
}
