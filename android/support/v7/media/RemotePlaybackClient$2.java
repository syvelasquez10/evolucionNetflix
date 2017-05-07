// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Parcelable;
import android.net.Uri;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.Intent;

class RemotePlaybackClient$2 extends MediaRouter$ControlRequestCallback
{
    final /* synthetic */ RemotePlaybackClient this$0;
    final /* synthetic */ RemotePlaybackClient$SessionActionCallback val$callback;
    final /* synthetic */ Intent val$intent;
    final /* synthetic */ String val$sessionId;
    
    RemotePlaybackClient$2(final RemotePlaybackClient this$0, final String val$sessionId, final Intent val$intent, final RemotePlaybackClient$SessionActionCallback val$callback) {
        this.this$0 = this$0;
        this.val$sessionId = val$sessionId;
        this.val$intent = val$intent;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onError(final String s, final Bundle bundle) {
        this.this$0.handleError(this.val$intent, this.val$callback, s, bundle);
    }
    
    @Override
    public void onResult(final Bundle bundle) {
        if (bundle != null) {
            final String access$100 = inferMissingResult(this.val$sessionId, bundle.getString("android.media.intent.extra.SESSION_ID"));
            final MediaSessionStatus fromBundle = MediaSessionStatus.fromBundle(bundle.getBundle("android.media.intent.extra.SESSION_STATUS"));
            this.this$0.adoptSession(access$100);
            if (access$100 != null) {
                if (RemotePlaybackClient.DEBUG) {
                    Log.d("RemotePlaybackClient", "Received result from " + this.val$intent.getAction() + ": data=" + bundleToString(bundle) + ", sessionId=" + access$100 + ", sessionStatus=" + fromBundle);
                }
                try {
                    this.val$callback.onResult(bundle, access$100, fromBundle);
                    return;
                }
                finally {
                    if (this.val$intent.getAction().equals("android.media.intent.action.END_SESSION") && access$100.equals(this.this$0.mSessionId)) {
                        this.this$0.setSessionId(null);
                    }
                }
            }
        }
        this.this$0.handleInvalidResult(this.val$intent, this.val$callback, bundle);
    }
}
