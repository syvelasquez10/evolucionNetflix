// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Parcelable;
import android.net.Uri;
import android.os.Bundle;
import android.content.IntentFilter;
import android.app.PendingIntent;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class RemotePlaybackClient$StatusReceiver extends BroadcastReceiver
{
    public static final String ACTION_ITEM_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED";
    public static final String ACTION_SESSION_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED";
    final /* synthetic */ RemotePlaybackClient this$0;
    
    private RemotePlaybackClient$StatusReceiver(final RemotePlaybackClient this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String stringExtra = intent.getStringExtra("android.media.intent.extra.SESSION_ID");
        if (stringExtra == null || !stringExtra.equals(this.this$0.mSessionId)) {
            Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing or invalid session id: sessionId=" + stringExtra);
        }
        else {
            final MediaSessionStatus fromBundle = MediaSessionStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.SESSION_STATUS"));
            final String action = intent.getAction();
            if (action.equals("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED")) {
                final String stringExtra2 = intent.getStringExtra("android.media.intent.extra.ITEM_ID");
                if (stringExtra2 == null) {
                    Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
                    return;
                }
                final MediaItemStatus fromBundle2 = MediaItemStatus.fromBundle(intent.getBundleExtra("android.media.intent.extra.ITEM_STATUS"));
                if (fromBundle2 == null) {
                    Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
                    return;
                }
                if (RemotePlaybackClient.DEBUG) {
                    Log.d("RemotePlaybackClient", "Received item status callback: sessionId=" + stringExtra + ", sessionStatus=" + fromBundle + ", itemId=" + stringExtra2 + ", itemStatus=" + fromBundle2);
                }
                if (this.this$0.mStatusCallback != null) {
                    this.this$0.mStatusCallback.onItemStatusChanged(intent.getExtras(), stringExtra, fromBundle, stringExtra2, fromBundle2);
                }
            }
            else if (action.equals("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED")) {
                if (fromBundle == null) {
                    Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
                    return;
                }
                if (RemotePlaybackClient.DEBUG) {
                    Log.d("RemotePlaybackClient", "Received session status callback: sessionId=" + stringExtra + ", sessionStatus=" + fromBundle);
                }
                if (this.this$0.mStatusCallback != null) {
                    this.this$0.mStatusCallback.onSessionStatusChanged(intent.getExtras(), stringExtra, fromBundle);
                }
            }
        }
    }
}
