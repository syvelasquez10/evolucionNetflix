// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.util.Log;
import android.content.ActivityNotFoundException;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import android.content.Intent;
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.Date;
import android.os.Handler;
import android.os.Bundle;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;
import java.util.List;
import java.util.Iterator;

class Session$4 implements Runnable
{
    final /* synthetic */ Session this$0;
    final /* synthetic */ Exception val$exception;
    final /* synthetic */ SessionState val$newState;
    
    Session$4(final Session this$0, final SessionState val$newState, final Exception val$exception) {
        this.this$0 = this$0;
        this.val$newState = val$newState;
        this.val$exception = val$exception;
    }
    
    @Override
    public void run() {
        synchronized (this.this$0.callbacks) {
            final Iterator<Session$StatusCallback> iterator = this.this$0.callbacks.iterator();
            while (iterator.hasNext()) {
                runWithHandlerOrExecutor(this.this$0.handler, new Session$4$1(this, iterator.next()));
            }
        }
    }
    // monitorexit(list)
}
