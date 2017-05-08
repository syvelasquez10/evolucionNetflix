// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class MdxAgent$4 extends BroadcastReceiver
{
    final /* synthetic */ MdxAgent this$0;
    
    MdxAgent$4(final MdxAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.v("nf_mdx_MdxAgent", "Null intent");
        }
        else {
            final String action = intent.getAction();
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
                Log.d("nf_mdx_MdxAgent", "useprofile is active");
                this.this$0.notifyIsUserLogin(true);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE".equals(action)) {
                Log.d("nf_mdx_MdxAgent", "useprofile is not active");
                this.this$0.notifyIsUserLogin(false);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE".equals(action)) {
                Log.d("nf_mdx_MdxAgent", "user account is activated");
                return;
            }
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE".equals(action)) {
                Log.d("nf_mdx_MdxAgent", "user account is deactivated");
            }
        }
    }
}
