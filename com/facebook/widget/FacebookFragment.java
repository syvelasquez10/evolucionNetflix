// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.SessionState;
import java.util.Date;
import android.content.Context;
import com.facebook.Session;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.SessionLoginBehavior;
import java.util.List;
import com.facebook.internal.SessionTracker;
import android.support.v4.app.Fragment;

class FacebookFragment extends Fragment
{
    private SessionTracker sessionTracker;
    
    private void openSession(final String applicationId, final List<String> permissions, final SessionLoginBehavior loginBehavior, final int requestCode, final SessionAuthorizationType sessionAuthorizationType) {
        if (this.sessionTracker != null) {
            final Session session = this.sessionTracker.getSession();
            Session build = null;
            Label_0061: {
                if (session != null) {
                    build = session;
                    if (!session.getState().isClosed()) {
                        break Label_0061;
                    }
                }
                build = new Session.Builder((Context)this.getActivity()).setApplicationId(applicationId).build();
                Session.setActiveSession(build);
            }
            if (!build.isOpened()) {
                final Session.OpenRequest setRequestCode = new Session.OpenRequest(this).setPermissions(permissions).setLoginBehavior(loginBehavior).setRequestCode(requestCode);
                if (!SessionAuthorizationType.PUBLISH.equals(sessionAuthorizationType)) {
                    build.openForRead(setRequestCode);
                    return;
                }
                build.openForPublish(setRequestCode);
            }
        }
    }
    
    protected final void closeSession() {
        if (this.sessionTracker != null) {
            final Session openSession = this.sessionTracker.getOpenSession();
            if (openSession != null) {
                openSession.close();
            }
        }
    }
    
    protected final void closeSessionAndClearTokenInformation() {
        if (this.sessionTracker != null) {
            final Session openSession = this.sessionTracker.getOpenSession();
            if (openSession != null) {
                openSession.closeAndClearTokenInformation();
            }
        }
    }
    
    protected final String getAccessToken() {
        String accessToken = null;
        if (this.sessionTracker != null) {
            final Session openSession = this.sessionTracker.getOpenSession();
            accessToken = accessToken;
            if (openSession != null) {
                accessToken = openSession.getAccessToken();
            }
        }
        return accessToken;
    }
    
    protected final Date getExpirationDate() {
        Date expirationDate = null;
        if (this.sessionTracker != null) {
            final Session openSession = this.sessionTracker.getOpenSession();
            expirationDate = expirationDate;
            if (openSession != null) {
                expirationDate = openSession.getExpirationDate();
            }
        }
        return expirationDate;
    }
    
    protected final Session getSession() {
        if (this.sessionTracker != null) {
            return this.sessionTracker.getSession();
        }
        return null;
    }
    
    protected final List<String> getSessionPermissions() {
        List<String> permissions = null;
        if (this.sessionTracker != null) {
            final Session session = this.sessionTracker.getSession();
            permissions = permissions;
            if (session != null) {
                permissions = session.getPermissions();
            }
        }
        return permissions;
    }
    
    protected final SessionState getSessionState() {
        SessionState state = null;
        if (this.sessionTracker != null) {
            final Session session = this.sessionTracker.getSession();
            state = state;
            if (session != null) {
                state = session.getState();
            }
        }
        return state;
    }
    
    protected final boolean isSessionOpen() {
        boolean b = false;
        if (this.sessionTracker != null) {
            b = b;
            if (this.sessionTracker.getOpenSession() != null) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.sessionTracker = new SessionTracker((Context)this.getActivity(), new DefaultSessionStatusCallback());
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.sessionTracker.getSession().onActivityResult(this.getActivity(), n, n2, intent);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.sessionTracker.stopTracking();
    }
    
    protected void onSessionStateChange(final SessionState sessionState, final Exception ex) {
    }
    
    protected final void openSession() {
        this.openSessionForRead(null, null);
    }
    
    protected final void openSessionForPublish(final String s, final List<String> list) {
        this.openSessionForPublish(s, list, SessionLoginBehavior.SSO_WITH_FALLBACK, 64206);
    }
    
    protected final void openSessionForPublish(final String s, final List<String> list, final SessionLoginBehavior sessionLoginBehavior, final int n) {
        this.openSession(s, list, sessionLoginBehavior, n, SessionAuthorizationType.PUBLISH);
    }
    
    protected final void openSessionForRead(final String s, final List<String> list) {
        this.openSessionForRead(s, list, SessionLoginBehavior.SSO_WITH_FALLBACK, 64206);
    }
    
    protected final void openSessionForRead(final String s, final List<String> list, final SessionLoginBehavior sessionLoginBehavior, final int n) {
        this.openSession(s, list, sessionLoginBehavior, n, SessionAuthorizationType.READ);
    }
    
    public void setSession(final Session session) {
        if (this.sessionTracker != null) {
            this.sessionTracker.setSession(session);
        }
    }
    
    private class DefaultSessionStatusCallback implements StatusCallback
    {
        @Override
        public void call(final Session session, final SessionState sessionState, final Exception ex) {
            FacebookFragment.this.onSessionStateChange(sessionState, ex);
        }
    }
}
