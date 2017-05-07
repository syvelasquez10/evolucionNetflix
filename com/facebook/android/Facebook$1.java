// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Collections;
import android.os.Bundle;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.TokenCachingStrategy;
import com.facebook.Settings;
import android.content.ContentResolver;
import java.util.Arrays;
import com.facebook.Session$OpenRequest;
import android.content.Context;
import com.facebook.Session$Builder;
import com.facebook.SessionLoginBehavior;
import java.util.List;
import android.app.Activity;
import android.net.Uri;
import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.Session$StatusCallback;

class Facebook$1 implements Session$StatusCallback
{
    final /* synthetic */ Facebook this$0;
    final /* synthetic */ Facebook$DialogListener val$listener;
    
    Facebook$1(final Facebook this$0, final Facebook$DialogListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void call(final Session session, final SessionState sessionState, final Exception ex) {
        this.this$0.onSessionCallback(session, sessionState, ex, this.val$listener);
    }
}
