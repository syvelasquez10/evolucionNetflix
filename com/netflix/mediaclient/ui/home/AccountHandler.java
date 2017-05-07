// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class AccountHandler
{
    private static final String TAG = "AccountHandler";
    private boolean mCompleted;
    private NetflixActivity mContext;
    
    AccountHandler(final NetflixActivity mContext) {
        this.mContext = mContext;
    }
    
    private static String createLink(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("https://www.netflix.com/youraccount").append("?nftoken=").append(s);
        return sb.toString();
    }
    
    void handle(final String s, final Status status) {
        while (true) {
            Label_0113: {
                synchronized (this) {
                    if (this.mCompleted) {
                        Log.w("AccountHandler", "Account handler already executed because of timeout. Do nothing...");
                    }
                    else {
                        this.mCompleted = true;
                        if (!status.isSucces() || !StringUtils.isNotEmpty(s)) {
                            break Label_0113;
                        }
                        if (Log.isLoggable()) {
                            Log.d("AccountHandler", "Autologin token created + '" + s + "'");
                        }
                        this.mContext.getHandler().post((Runnable)new LaunchBrowser((Context)this.mContext, createLink(s)));
                    }
                    return;
                }
            }
            if (Log.isLoggable()) {
                final String s2;
                Log.e("AccountHandler", "Failed to create autologin token " + status + ", token " + s2);
            }
            this.mContext.getHandler().post((Runnable)new AccountHandler$1(this));
        }
    }
}
