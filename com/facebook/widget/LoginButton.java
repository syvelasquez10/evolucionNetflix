// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.view.View;
import android.util.Log;
import java.util.Collection;
import java.util.Collections;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.SessionState;
import android.app.Activity;
import android.content.Intent;
import com.facebook.FacebookException;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import android.content.res.TypedArray;
import com.facebook.internal.Utility;
import android.view.View$OnClickListener;
import com.facebook.Response;
import com.facebook.Request;
import com.facebook.android.R;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;
import android.widget.Button;

public class LoginButton extends Button
{
    private static final String TAG;
    private String applicationId;
    private boolean confirmLogout;
    private boolean fetchUserInfo;
    private String loginText;
    private String logoutText;
    private Fragment parentFragment;
    private LoginButtonProperties properties;
    private SessionTracker sessionTracker;
    private GraphUser user;
    private UserInfoChangedCallback userInfoChangedCallback;
    private Session userInfoSession;
    
    static {
        TAG = LoginButton.class.getName();
    }
    
    public LoginButton(final Context context) {
        super(context);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        this.initializeActiveSessionWithCachedToken(context);
        this.finishInit();
    }
    
    public LoginButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        if (set.getStyleAttribute() == 0) {
            this.setTextColor(this.getResources().getColor(R.color.com_facebook_loginview_text_color));
            this.setTextSize(0, this.getResources().getDimension(R.dimen.com_facebook_loginview_text_size));
            this.setPadding(this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_left), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_top), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_right), this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_padding_bottom));
            this.setWidth(this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_width));
            this.setHeight(this.getResources().getDimensionPixelSize(R.dimen.com_facebook_loginview_height));
            this.setGravity(17);
            if (this.isInEditMode()) {
                this.setBackgroundColor(this.getResources().getColor(R.color.com_facebook_blue));
                this.loginText = "Log in";
            }
            else {
                this.setBackgroundResource(R.drawable.com_facebook_loginbutton_blue);
            }
        }
        this.parseAttributes(set);
        if (!this.isInEditMode()) {
            this.initializeActiveSessionWithCachedToken(context);
        }
    }
    
    public LoginButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.applicationId = null;
        this.user = null;
        this.userInfoSession = null;
        this.properties = new LoginButtonProperties();
        this.parseAttributes(set);
        this.initializeActiveSessionWithCachedToken(context);
    }
    
    private void fetchUserInfo() {
        if (this.fetchUserInfo) {
            final Session openSession = this.sessionTracker.getOpenSession();
            if (openSession != null) {
                if (openSession != this.userInfoSession) {
                    Request.executeBatchAsync(Request.newMeRequest(openSession, (Request.GraphUserCallback)new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(final GraphUser graphUser, final Response response) {
                            if (openSession == LoginButton.this.sessionTracker.getOpenSession()) {
                                LoginButton.this.user = graphUser;
                                if (LoginButton.this.userInfoChangedCallback != null) {
                                    LoginButton.this.userInfoChangedCallback.onUserInfoFetched(LoginButton.this.user);
                                }
                            }
                            if (response.getError() != null) {
                                LoginButton.this.handleError(response.getError().getException());
                            }
                        }
                    }));
                    this.userInfoSession = openSession;
                }
            }
            else {
                this.user = null;
                if (this.userInfoChangedCallback != null) {
                    this.userInfoChangedCallback.onUserInfoFetched(this.user);
                }
            }
        }
    }
    
    private void finishInit() {
        this.setOnClickListener((View$OnClickListener)new LoginClickListener());
        this.setButtonText();
        if (!this.isInEditMode()) {
            this.sessionTracker = new SessionTracker(this.getContext(), new LoginButtonCallback(), null, false);
            this.fetchUserInfo();
        }
    }
    
    private boolean initializeActiveSessionWithCachedToken(final Context context) {
        if (context != null) {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                return activeSession.isOpened();
            }
            if (Utility.getMetadataApplicationId(context) != null && Session.openActiveSessionFromCache(context) != null) {
                return true;
            }
        }
        return false;
    }
    
    private void parseAttributes(final AttributeSet set) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.com_facebook_login_view);
        this.confirmLogout = obtainStyledAttributes.getBoolean(0, true);
        this.fetchUserInfo = obtainStyledAttributes.getBoolean(1, true);
        this.loginText = obtainStyledAttributes.getString(2);
        this.logoutText = obtainStyledAttributes.getString(3);
        obtainStyledAttributes.recycle();
    }
    
    private void setButtonText() {
        if (this.sessionTracker != null && this.sessionTracker.getOpenSession() != null) {
            String text;
            if (this.logoutText != null) {
                text = this.logoutText;
            }
            else {
                text = this.getResources().getString(R.string.com_facebook_loginview_log_out_button);
            }
            this.setText((CharSequence)text);
            return;
        }
        String text2;
        if (this.loginText != null) {
            text2 = this.loginText;
        }
        else {
            text2 = this.getResources().getString(R.string.com_facebook_loginview_log_in_button);
        }
        this.setText((CharSequence)text2);
    }
    
    public void clearPermissions() {
        this.properties.clearPermissions();
    }
    
    public SessionDefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }
    
    public SessionLoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }
    
    public OnErrorListener getOnErrorListener() {
        return this.properties.getOnErrorListener();
    }
    
    List<String> getPermissions() {
        return this.properties.getPermissions();
    }
    
    public Session.StatusCallback getSessionStatusCallback() {
        return this.properties.getSessionStatusCallback();
    }
    
    public UserInfoChangedCallback getUserInfoChangedCallback() {
        return this.userInfoChangedCallback;
    }
    
    void handleError(final Exception ex) {
        if (this.properties.onErrorListener != null) {
            if (!(ex instanceof FacebookException)) {
                this.properties.onErrorListener.onError(new FacebookException(ex));
                return;
            }
            this.properties.onErrorListener.onError((FacebookException)ex);
        }
    }
    
    public boolean onActivityResult(final int n, final int n2, final Intent intent) {
        final Session session = this.sessionTracker.getSession();
        return session != null && session.onActivityResult((Activity)this.getContext(), n, n2, intent);
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.sessionTracker != null && !this.sessionTracker.isTracking()) {
            this.sessionTracker.startTracking();
            this.fetchUserInfo();
            this.setButtonText();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.sessionTracker != null) {
            this.sessionTracker.stopTracking();
        }
    }
    
    public void onFinishInflate() {
        super.onFinishInflate();
        this.finishInit();
    }
    
    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }
    
    public void setDefaultAudience(final SessionDefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }
    
    public void setFragment(final Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }
    
    public void setLoginBehavior(final SessionLoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }
    
    public void setOnErrorListener(final OnErrorListener onErrorListener) {
        this.properties.setOnErrorListener(onErrorListener);
    }
    
    void setProperties(final LoginButtonProperties properties) {
        this.properties = properties;
    }
    
    public void setPublishPermissions(final List<String> list) {
        this.properties.setPublishPermissions(list, this.sessionTracker.getSession());
    }
    
    public void setReadPermissions(final List<String> list) {
        this.properties.setReadPermissions(list, this.sessionTracker.getSession());
    }
    
    public void setSession(final Session session) {
        this.sessionTracker.setSession(session);
        this.fetchUserInfo();
        this.setButtonText();
    }
    
    public void setSessionStatusCallback(final Session.StatusCallback sessionStatusCallback) {
        this.properties.setSessionStatusCallback(sessionStatusCallback);
    }
    
    public void setUserInfoChangedCallback(final UserInfoChangedCallback userInfoChangedCallback) {
        this.userInfoChangedCallback = userInfoChangedCallback;
    }
    
    private class LoginButtonCallback implements StatusCallback
    {
        @Override
        public void call(final Session session, final SessionState sessionState, final Exception ex) {
            LoginButton.this.fetchUserInfo();
            LoginButton.this.setButtonText();
            if (ex != null) {
                LoginButton.this.handleError(ex);
            }
            if (LoginButton.this.properties.sessionStatusCallback != null) {
                LoginButton.this.properties.sessionStatusCallback.call(session, sessionState, ex);
            }
        }
    }
    
    static class LoginButtonProperties
    {
        private SessionAuthorizationType authorizationType;
        private SessionDefaultAudience defaultAudience;
        private SessionLoginBehavior loginBehavior;
        private OnErrorListener onErrorListener;
        private List<String> permissions;
        private Session.StatusCallback sessionStatusCallback;
        
        LoginButtonProperties() {
            this.defaultAudience = SessionDefaultAudience.FRIENDS;
            this.permissions = Collections.emptyList();
            this.authorizationType = null;
            this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        }
        
        private boolean validatePermissions(final List<String> list, final SessionAuthorizationType sessionAuthorizationType, final Session session) {
            if (SessionAuthorizationType.PUBLISH.equals(sessionAuthorizationType) && Utility.isNullOrEmpty((Collection<Object>)list)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            }
            if (session != null && session.isOpened() && !Utility.isSubset(list, session.getPermissions())) {
                Log.e(LoginButton.TAG, "Cannot set additional permissions when session is already open.");
                return false;
            }
            return true;
        }
        
        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }
        
        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }
        
        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
        
        public OnErrorListener getOnErrorListener() {
            return this.onErrorListener;
        }
        
        List<String> getPermissions() {
            return this.permissions;
        }
        
        public Session.StatusCallback getSessionStatusCallback() {
            return this.sessionStatusCallback;
        }
        
        public void setDefaultAudience(final SessionDefaultAudience defaultAudience) {
            this.defaultAudience = defaultAudience;
        }
        
        public void setLoginBehavior(final SessionLoginBehavior loginBehavior) {
            this.loginBehavior = loginBehavior;
        }
        
        public void setOnErrorListener(final OnErrorListener onErrorListener) {
            this.onErrorListener = onErrorListener;
        }
        
        public void setPublishPermissions(final List<String> permissions, final Session session) {
            if (SessionAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            }
            if (this.validatePermissions(permissions, SessionAuthorizationType.PUBLISH, session)) {
                this.permissions = permissions;
                this.authorizationType = SessionAuthorizationType.PUBLISH;
            }
        }
        
        public void setReadPermissions(final List<String> permissions, final Session session) {
            if (SessionAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            if (this.validatePermissions(permissions, SessionAuthorizationType.READ, session)) {
                this.permissions = permissions;
                this.authorizationType = SessionAuthorizationType.READ;
            }
        }
        
        public void setSessionStatusCallback(final Session.StatusCallback sessionStatusCallback) {
            this.sessionStatusCallback = sessionStatusCallback;
        }
    }
    
    private class LoginClickListener implements View$OnClickListener
    {
        public void onClick(final View view) {
            final Context context = LoginButton.this.getContext();
            final Session openSession = LoginButton.this.sessionTracker.getOpenSession();
            if (openSession != null) {
                if (!LoginButton.this.confirmLogout) {
                    openSession.closeAndClearTokenInformation();
                    return;
                }
                final String string = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_log_out_action);
                final String string2 = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_cancel_action);
                String message;
                if (LoginButton.this.user != null && LoginButton.this.user.getName() != null) {
                    message = String.format(LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_as), LoginButton.this.user.getName());
                }
                else {
                    message = LoginButton.this.getResources().getString(R.string.com_facebook_loginview_logged_in_using_facebook);
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
                alertDialog$Builder.setMessage((CharSequence)message).setCancelable(true).setPositiveButton((CharSequence)string, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        openSession.closeAndClearTokenInformation();
                    }
                }).setNegativeButton((CharSequence)string2, (DialogInterface$OnClickListener)null);
                alertDialog$Builder.create().show();
            }
            else {
                final Session session = LoginButton.this.sessionTracker.getSession();
                Session build = null;
                Label_0257: {
                    if (session != null) {
                        build = session;
                        if (!session.getState().isClosed()) {
                            break Label_0257;
                        }
                    }
                    LoginButton.this.sessionTracker.setSession(null);
                    build = new Session.Builder(context).setApplicationId(LoginButton.this.applicationId).build();
                    Session.setActiveSession(build);
                }
                if (!build.isOpened()) {
                    Session.OpenRequest openRequest = null;
                    if (LoginButton.this.parentFragment != null) {
                        openRequest = new Session.OpenRequest(LoginButton.this.parentFragment);
                    }
                    else if (context instanceof Activity) {
                        openRequest = new Session.OpenRequest((Activity)context);
                    }
                    if (openRequest != null) {
                        openRequest.setDefaultAudience(LoginButton.this.properties.defaultAudience);
                        openRequest.setPermissions(LoginButton.this.properties.permissions);
                        openRequest.setLoginBehavior(LoginButton.this.properties.loginBehavior);
                        if (SessionAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                            build.openForPublish(openRequest);
                            return;
                        }
                        build.openForRead(openRequest);
                    }
                }
            }
        }
    }
    
    public interface OnErrorListener
    {
        void onError(final FacebookException p0);
    }
    
    public interface UserInfoChangedCallback
    {
        void onUserInfoFetched(final GraphUser p0);
    }
}
