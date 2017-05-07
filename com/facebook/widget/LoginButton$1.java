// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.Arrays;
import android.graphics.Canvas;
import android.app.Activity;
import android.content.Intent;
import com.facebook.FacebookException;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import com.facebook.Session$StatusCallback;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Request;
import android.view.View;
import com.facebook.android.R$string;
import com.facebook.android.R$drawable;
import android.graphics.Typeface;
import com.facebook.android.R$dimen;
import com.facebook.android.R$color;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;
import android.view.View$OnClickListener;
import android.widget.Button;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility$FetchedAppSettings;
import android.os.AsyncTask;

class LoginButton$1 extends AsyncTask<Void, Void, Utility$FetchedAppSettings>
{
    final /* synthetic */ LoginButton this$0;
    final /* synthetic */ String val$appId;
    
    LoginButton$1(final LoginButton this$0, final String val$appId) {
        this.this$0 = this$0;
        this.val$appId = val$appId;
    }
    
    protected Utility$FetchedAppSettings doInBackground(final Void... array) {
        return Utility.queryAppSettings(this.val$appId, false);
    }
    
    protected void onPostExecute(final Utility$FetchedAppSettings utility$FetchedAppSettings) {
        this.this$0.showNuxPerSettings(utility$FetchedAppSettings);
    }
}
