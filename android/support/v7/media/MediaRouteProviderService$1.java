// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.Message;
import android.os.Handler;
import java.util.ArrayList;
import android.app.Service;
import android.util.Log;
import android.os.Bundle;
import android.os.Messenger;
import android.content.Intent;

class MediaRouteProviderService$1 extends MediaRouter$ControlRequestCallback
{
    final /* synthetic */ MediaRouteProviderService this$0;
    final /* synthetic */ MediaRouteProviderService$ClientRecord val$client;
    final /* synthetic */ int val$controllerId;
    final /* synthetic */ Intent val$intent;
    final /* synthetic */ Messenger val$messenger;
    final /* synthetic */ int val$requestId;
    
    MediaRouteProviderService$1(final MediaRouteProviderService this$0, final MediaRouteProviderService$ClientRecord val$client, final int val$controllerId, final Intent val$intent, final Messenger val$messenger, final int val$requestId) {
        this.this$0 = this$0;
        this.val$client = val$client;
        this.val$controllerId = val$controllerId;
        this.val$intent = val$intent;
        this.val$messenger = val$messenger;
        this.val$requestId = val$requestId;
    }
    
    @Override
    public void onError(final String s, final Bundle bundle) {
        if (MediaRouteProviderService.DEBUG) {
            Log.d("MediaRouteProviderSrv", this.val$client + ": Route control request failed" + ", controllerId=" + this.val$controllerId + ", intent=" + this.val$intent + ", error=" + s + ", data=" + bundle);
        }
        if (this.this$0.findClient(this.val$messenger) >= 0) {
            if (s == null) {
                sendReply(this.val$messenger, 4, this.val$requestId, 0, bundle, null);
                return;
            }
            final Bundle bundle2 = new Bundle();
            bundle2.putString("error", s);
            sendReply(this.val$messenger, 4, this.val$requestId, 0, bundle, bundle2);
        }
    }
    
    @Override
    public void onResult(final Bundle bundle) {
        if (MediaRouteProviderService.DEBUG) {
            Log.d("MediaRouteProviderSrv", this.val$client + ": Route control request succeeded" + ", controllerId=" + this.val$controllerId + ", intent=" + this.val$intent + ", data=" + bundle);
        }
        if (this.this$0.findClient(this.val$messenger) >= 0) {
            sendReply(this.val$messenger, 3, this.val$requestId, 0, bundle, null);
        }
    }
}
