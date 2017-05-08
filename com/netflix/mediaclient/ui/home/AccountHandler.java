// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import android.net.Uri$Builder;
import android.net.Uri;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class AccountHandler
{
    public static final String ACCOUNT_URL = "https://www.netflix.com/youraccount";
    public static final long AUTOLOGIN_TOKEN_EXPIRATION_IN_MS = 3600000L;
    public static final long AUTOLOGIN_TOKEN_TIMEOUT_IN_MS = 10000L;
    private static final String TAG = "AccountHandler";
    public static final String TOKEN_PARAMETER_NAME = "nftoken";
    private boolean mCompleted;
    private NetflixActivity mContext;
    
    public AccountHandler(final NetflixActivity mContext) {
        this.mContext = mContext;
    }
    
    private static String createLink(final String s, final String s2) {
        final Uri$Builder buildUpon = Uri.parse(s).buildUpon();
        buildUpon.appendQueryParameter("nftoken", s2);
        return buildUpon.build().toString();
    }
    
    public void handle(final String s, final Status status) {
        synchronized (this) {
            this.handle(s, status, "https://www.netflix.com/youraccount");
        }
    }
    
    public void handle(String link, final Status status, final String s) {
        while (true) {
            Label_0116: {
                synchronized (this) {
                    if (this.mCompleted) {
                        Log.w("AccountHandler", "Account handler already executed because of timeout. Do nothing...");
                    }
                    else {
                        this.mCompleted = true;
                        if (!status.isSucces() || !StringUtils.isNotEmpty(link)) {
                            break Label_0116;
                        }
                        link = createLink(s, link);
                        if (Log.isLoggable()) {
                            Log.d("AccountHandler", "Autologin token created urlWithToken: '" + link + "'");
                        }
                        this.mContext.getHandler().post((Runnable)new LaunchBrowser((Context)this.mContext, link));
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
