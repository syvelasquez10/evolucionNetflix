// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.util.Log;
import android.view.KeyEvent;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class TransportMediatorJellybeanMR2$3 extends BroadcastReceiver
{
    final /* synthetic */ TransportMediatorJellybeanMR2 this$0;
    
    TransportMediatorJellybeanMR2$3(final TransportMediatorJellybeanMR2 this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        try {
            this.this$0.mTransportCallback.handleKey((KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
        }
        catch (ClassCastException ex) {
            Log.w("TransportController", (Throwable)ex);
        }
    }
}
