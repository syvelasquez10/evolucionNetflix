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
import com.facebook.Session$Builder;
import com.facebook.SessionLoginBehavior;
import java.util.List;
import com.facebook.SessionState;
import com.facebook.Session;
import android.app.Activity;
import android.net.Uri;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.content.Context;
import android.content.ServiceConnection;

class Facebook$TokenRefreshServiceConnection implements ServiceConnection
{
    final Context applicationsContext;
    final Messenger messageReceiver;
    Messenger messageSender;
    final Facebook$ServiceListener serviceListener;
    final /* synthetic */ Facebook this$0;
    
    public Facebook$TokenRefreshServiceConnection(final Facebook this$0, final Context applicationsContext, final Facebook$ServiceListener serviceListener) {
        this.this$0 = this$0;
        this.messageReceiver = new Messenger((Handler)new Facebook$TokenRefreshConnectionHandler(this.this$0, this));
        this.messageSender = null;
        this.applicationsContext = applicationsContext;
        this.serviceListener = serviceListener;
    }
    
    private void refreshToken() {
        final Bundle data = new Bundle();
        data.putString("access_token", this.this$0.accessToken);
        final Message obtain = Message.obtain();
        obtain.setData(data);
        obtain.replyTo = this.messageReceiver;
        try {
            this.messageSender.send(obtain);
        }
        catch (RemoteException ex) {
            this.serviceListener.onError(new Error("Service connection error"));
        }
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.messageSender = new Messenger(binder);
        this.refreshToken();
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.serviceListener.onError(new Error("Service disconnected"));
        this.applicationsContext.unbindService((ServiceConnection)this);
    }
}
