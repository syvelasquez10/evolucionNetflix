// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.Messenger;
import java.util.Iterator;
import android.content.ActivityNotFoundException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.content.Intent;
import java.util.Collections;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;
import android.content.ServiceConnection;
import android.os.Message;
import android.os.Looper;
import java.lang.ref.WeakReference;
import android.os.Handler;

class Session$TokenRefreshRequestHandler extends Handler
{
    private final WeakReference<Session$TokenRefreshRequest> refreshRequestWeakReference;
    private final WeakReference<Session> sessionWeakReference;
    
    Session$TokenRefreshRequestHandler(final Session session, final Session$TokenRefreshRequest session$TokenRefreshRequest) {
        super(Looper.getMainLooper());
        this.sessionWeakReference = new WeakReference<Session>(session);
        this.refreshRequestWeakReference = new WeakReference<Session$TokenRefreshRequest>(session$TokenRefreshRequest);
    }
    
    public void handleMessage(final Message message) {
        final String string = message.getData().getString("access_token");
        final Session session = this.sessionWeakReference.get();
        if (session != null && string != null) {
            session.extendTokenCompleted(message.getData());
        }
        final Session$TokenRefreshRequest session$TokenRefreshRequest = this.refreshRequestWeakReference.get();
        if (session$TokenRefreshRequest != null) {
            Session.staticContext.unbindService((ServiceConnection)session$TokenRefreshRequest);
            session$TokenRefreshRequest.cleanup();
        }
    }
}
