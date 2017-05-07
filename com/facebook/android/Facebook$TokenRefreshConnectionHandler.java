// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Collections;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.TokenCachingStrategy;
import com.facebook.Settings;
import android.content.ContentResolver;
import java.util.Arrays;
import com.facebook.Session$StatusCallback;
import com.facebook.Session$OpenRequest;
import android.content.Context;
import com.facebook.Session$Builder;
import com.facebook.SessionLoginBehavior;
import java.util.List;
import com.facebook.SessionState;
import android.app.Activity;
import android.net.Uri;
import com.facebook.Session;
import android.content.ServiceConnection;
import android.os.Bundle;
import com.facebook.LegacyHelper;
import android.os.Message;
import java.lang.ref.WeakReference;
import android.os.Handler;

class Facebook$TokenRefreshConnectionHandler extends Handler
{
    WeakReference<Facebook$TokenRefreshServiceConnection> connectionWeakReference;
    WeakReference<Facebook> facebookWeakReference;
    
    Facebook$TokenRefreshConnectionHandler(final Facebook facebook, final Facebook$TokenRefreshServiceConnection facebook$TokenRefreshServiceConnection) {
        this.facebookWeakReference = new WeakReference<Facebook>(facebook);
        this.connectionWeakReference = new WeakReference<Facebook$TokenRefreshServiceConnection>(facebook$TokenRefreshServiceConnection);
    }
    
    public void handleMessage(final Message message) {
        final Facebook facebook = this.facebookWeakReference.get();
        final Facebook$TokenRefreshServiceConnection facebook$TokenRefreshServiceConnection = this.connectionWeakReference.get();
        if (facebook != null && facebook$TokenRefreshServiceConnection != null) {
            final String string = message.getData().getString("access_token");
            final long accessExpires = message.getData().getLong("expires_in") * 1000L;
            if (string != null) {
                facebook.setAccessToken(string);
                facebook.setAccessExpires(accessExpires);
                final Session access$200 = facebook.session;
                if (access$200 != null) {
                    LegacyHelper.extendTokenCompleted(access$200, message.getData());
                }
                if (facebook$TokenRefreshServiceConnection.serviceListener != null) {
                    final Bundle bundle = (Bundle)message.getData().clone();
                    bundle.putLong("expires_in", accessExpires);
                    facebook$TokenRefreshServiceConnection.serviceListener.onComplete(bundle);
                }
            }
            else if (facebook$TokenRefreshServiceConnection.serviceListener != null) {
                final String string2 = message.getData().getString("error");
                if (message.getData().containsKey("error_code")) {
                    facebook$TokenRefreshServiceConnection.serviceListener.onFacebookError(new FacebookError(string2, null, message.getData().getInt("error_code")));
                }
                else {
                    final Facebook$ServiceListener serviceListener = facebook$TokenRefreshServiceConnection.serviceListener;
                    String s;
                    if (string2 != null) {
                        s = string2;
                    }
                    else {
                        s = "Unknown service error";
                    }
                    serviceListener.onError(new Error(s));
                }
            }
            if (facebook$TokenRefreshServiceConnection != null) {
                facebook$TokenRefreshServiceConnection.applicationsContext.unbindService((ServiceConnection)facebook$TokenRefreshServiceConnection);
            }
        }
    }
}
