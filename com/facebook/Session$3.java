// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

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
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.Date;
import android.os.Handler;
import java.util.List;
import android.os.Bundle;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;
import java.util.Iterator;

class Session$3 implements Runnable
{
    final /* synthetic */ Session this$0;
    final /* synthetic */ Exception val$exception;
    final /* synthetic */ SessionState val$newState;
    
    Session$3(final Session this$0, final SessionState val$newState, final Exception val$exception) {
        this.this$0 = this$0;
        this.val$newState = val$newState;
        this.val$exception = val$exception;
    }
    
    @Override
    public void run() {
        final Iterator<Session$StatusCallback> iterator = this.this$0.callbacks.iterator();
        while (iterator.hasNext()) {
            runWithHandlerOrExecutor(this.this$0.handler, new Session$3$1(this, iterator.next()));
        }
    }
}
