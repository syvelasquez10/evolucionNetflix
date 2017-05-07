// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.util.Log;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import android.graphics.Canvas;
import android.content.Intent;
import com.facebook.FacebookException;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import com.facebook.Session$StatusCallback;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Request;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility$FetchedAppSettings;
import com.facebook.android.R$drawable;
import android.graphics.Typeface;
import com.facebook.android.R$dimen;
import com.facebook.android.R$color;
import android.util.AttributeSet;
import com.facebook.model.GraphUser;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.content.Context;
import android.os.Bundle;
import com.facebook.AppEventsLogger;
import com.facebook.internal.SessionAuthorizationType;
import java.util.List;
import android.content.ContextWrapper;
import android.app.Activity;
import com.facebook.Session$OpenRequest;
import com.facebook.Session$Builder;
import com.facebook.Session;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.facebook.android.R$string;
import android.view.View;
import android.view.View$OnClickListener;

class LoginButton$LoginClickListener implements View$OnClickListener
{
    final /* synthetic */ LoginButton this$0;
    
    private LoginButton$LoginClickListener(final LoginButton this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final Context context = this.this$0.getContext();
        final Session openSession = this.this$0.sessionTracker.getOpenSession();
        if (openSession != null) {
            if (this.this$0.confirmLogout) {
                final String string = this.this$0.getResources().getString(R$string.com_facebook_loginview_log_out_action);
                final String string2 = this.this$0.getResources().getString(R$string.com_facebook_loginview_cancel_action);
                String message;
                if (this.this$0.user != null && this.this$0.user.getName() != null) {
                    message = String.format(this.this$0.getResources().getString(R$string.com_facebook_loginview_logged_in_as), this.this$0.user.getName());
                }
                else {
                    message = this.this$0.getResources().getString(R$string.com_facebook_loginview_logged_in_using_facebook);
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
                alertDialog$Builder.setMessage((CharSequence)message).setCancelable(true).setPositiveButton((CharSequence)string, (DialogInterface$OnClickListener)new LoginButton$LoginClickListener$1(this, openSession)).setNegativeButton((CharSequence)string2, (DialogInterface$OnClickListener)null);
                alertDialog$Builder.create().show();
            }
            else {
                openSession.closeAndClearTokenInformation();
            }
        }
        else {
            final Session session = this.this$0.sessionTracker.getSession();
            Session build = null;
            Label_0344: {
                if (session != null) {
                    build = session;
                    if (!session.getState().isClosed()) {
                        break Label_0344;
                    }
                }
                this.this$0.sessionTracker.setSession(null);
                build = new Session$Builder(context).setApplicationId(this.this$0.applicationId).build();
                Session.setActiveSession(build);
            }
            if (!build.isOpened()) {
                Session$OpenRequest session$OpenRequest = null;
                Label_0377: {
                    if (this.this$0.parentFragment != null) {
                        session$OpenRequest = new Session$OpenRequest(this.this$0.parentFragment);
                    }
                    else if (context instanceof Activity) {
                        session$OpenRequest = new Session$OpenRequest((Activity)context);
                    }
                    else {
                        if (context instanceof ContextWrapper) {
                            final Context baseContext = ((ContextWrapper)context).getBaseContext();
                            if (baseContext instanceof Activity) {
                                session$OpenRequest = new Session$OpenRequest((Activity)baseContext);
                                break Label_0377;
                            }
                        }
                        session$OpenRequest = null;
                    }
                }
                if (session$OpenRequest != null) {
                    session$OpenRequest.setDefaultAudience(this.this$0.properties.defaultAudience);
                    session$OpenRequest.setPermissions(this.this$0.properties.permissions);
                    session$OpenRequest.setLoginBehavior(this.this$0.properties.loginBehavior);
                    if (SessionAuthorizationType.PUBLISH.equals(this.this$0.properties.authorizationType)) {
                        build.openForPublish(session$OpenRequest);
                    }
                    else {
                        build.openForRead(session$OpenRequest);
                    }
                }
            }
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger(this.this$0.getContext());
        final Bundle bundle = new Bundle();
        int n;
        if (openSession != null) {
            n = 0;
        }
        else {
            n = 1;
        }
        bundle.putInt("logging_in", n);
        logger.logSdkEvent(this.this$0.loginLogoutEventName, null, bundle);
        if (this.this$0.listenerCallback != null) {
            this.this$0.listenerCallback.onClick(view);
        }
    }
}
