// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.internal.NativeProtocol;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.content.BroadcastReceiver;

public class FacebookBroadcastReceiver extends BroadcastReceiver
{
    protected void onFailedAppCall(final String s, final String s2, final Bundle bundle) {
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String stringExtra = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
        final String stringExtra2 = intent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION");
        if (stringExtra != null && stringExtra2 != null) {
            final Bundle extras = intent.getExtras();
            if (!NativeProtocol.isErrorResult(intent)) {
                this.onSuccessfulAppCall(stringExtra, stringExtra2, extras);
                return;
            }
            this.onFailedAppCall(stringExtra, stringExtra2, extras);
        }
    }
    
    protected void onSuccessfulAppCall(final String s, final String s2, final Bundle bundle) {
    }
}
