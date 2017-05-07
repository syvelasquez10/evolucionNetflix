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

class RemotePlaybackClient$1 extends MediaRouter$ControlRequestCallback
{
    final /* synthetic */ RemotePlaybackClient this$0;
    final /* synthetic */ RemotePlaybackClient$ItemActionCallback val$callback;
    final /* synthetic */ Intent val$intent;
    final /* synthetic */ String val$itemId;
    final /* synthetic */ String val$sessionId;
    
    RemotePlaybackClient$1(final RemotePlaybackClient this$0, final String val$sessionId, final String val$itemId, final Intent val$intent, final RemotePlaybackClient$ItemActionCallback val$callback) {
        this.this$0 = this$0;
        this.val$sessionId = val$sessionId;
        this.val$itemId = val$itemId;
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
            final String access$101 = inferMissingResult(this.val$itemId, bundle.getString("android.media.intent.extra.ITEM_ID"));
            final MediaItemStatus fromBundle2 = MediaItemStatus.fromBundle(bundle.getBundle("android.media.intent.extra.ITEM_STATUS"));
            this.this$0.adoptSession(access$100);
            if (access$100 != null && access$101 != null && fromBundle2 != null) {
                if (RemotePlaybackClient.DEBUG) {
                    Log.d("RemotePlaybackClient", "Received result from " + this.val$intent.getAction() + ": data=" + bundleToString(bundle) + ", sessionId=" + access$100 + ", sessionStatus=" + fromBundle + ", itemId=" + access$101 + ", itemStatus=" + fromBundle2);
                }
                this.val$callback.onResult(bundle, access$100, fromBundle, access$101, fromBundle2);
                return;
            }
        }
        this.this$0.handleInvalidResult(this.val$intent, this.val$callback, bundle);
    }
}
