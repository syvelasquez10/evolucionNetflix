// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.app.Activity;
import android.content.Intent;
import com.facebook.FacebookException;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import com.facebook.android.R$string;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import com.facebook.internal.Utility;
import com.facebook.Session$StatusCallback;
import android.view.View$OnClickListener;
import com.facebook.Request;
import com.facebook.android.R$drawable;
import com.facebook.android.R$dimen;
import com.facebook.android.R$color;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;
import android.widget.Button;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.facebook.Session;
import com.facebook.Request$GraphUserCallback;

class LoginButton$1 implements Request$GraphUserCallback
{
    final /* synthetic */ LoginButton this$0;
    final /* synthetic */ Session val$currentSession;
    
    LoginButton$1(final LoginButton this$0, final Session val$currentSession) {
        this.this$0 = this$0;
        this.val$currentSession = val$currentSession;
    }
    
    @Override
    public void onCompleted(final GraphUser graphUser, final Response response) {
        if (this.val$currentSession == this.this$0.sessionTracker.getOpenSession()) {
            this.this$0.user = graphUser;
            if (this.this$0.userInfoChangedCallback != null) {
                this.this$0.userInfoChangedCallback.onUserInfoFetched(this.this$0.user);
            }
        }
        if (response.getError() != null) {
            this.this$0.handleError(response.getError().getException());
        }
    }
}
