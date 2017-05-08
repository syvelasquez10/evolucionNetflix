// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.net.Uri$Builder;
import android.content.Context;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.io.UnsupportedEncodingException;
import com.netflix.mediaclient.util.LogUtils;
import java.net.URLDecoder;
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
    private final NetflixActivity mContext;
    
    public AccountHandler(final NetflixActivity mContext) {
        this.mContext = mContext;
    }
    
    public static String createLink(String buildUpon, final String s) {
        buildUpon = (String)Uri.parse(buildUpon).buildUpon();
        try {
            ((Uri$Builder)buildUpon).appendQueryParameter("nftoken", URLDecoder.decode(s, "UTF-8"));
            return ((Uri$Builder)buildUpon).build().toString();
        }
        catch (UnsupportedEncodingException ex) {
            LogUtils.reportErrorSafely("should not happen", (Throwable)ex);
            return ((Uri$Builder)buildUpon).build().toString();
        }
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
