// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.util.Log;
import java.util.Collection;
import java.util.Collections;
import com.facebook.internal.SessionAuthorizationType;
import java.util.Arrays;
import android.graphics.Canvas;
import com.facebook.FacebookException;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Request;
import android.view.View;
import com.facebook.internal.Utility;
import com.facebook.android.R$string;
import com.facebook.internal.Utility$FetchedAppSettings;
import com.facebook.android.R$drawable;
import android.graphics.Typeface;
import com.facebook.android.R$dimen;
import com.facebook.android.R$color;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.model.GraphUser;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;
import android.view.View$OnClickListener;
import android.widget.Button;
import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.Session$StatusCallback;

class LoginButton$LoginButtonCallback implements Session$StatusCallback
{
    final /* synthetic */ LoginButton this$0;
    
    private LoginButton$LoginButtonCallback(final LoginButton this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void call(final Session session, final SessionState sessionState, final Exception ex) {
        this.this$0.fetchUserInfo();
        this.this$0.setButtonText();
        if (this.this$0.properties.sessionStatusCallback != null) {
            this.this$0.properties.sessionStatusCallback.call(session, sessionState, ex);
        }
        else if (ex != null) {
            this.this$0.handleError(ex);
        }
    }
}
