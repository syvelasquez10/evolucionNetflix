// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.social;

import android.content.Intent;
import android.app.Activity;
import com.facebook.android.FacebookError;
import com.netflix.mediaclient.event.service.AuthorizationFailed;
import com.facebook.android.DialogError;
import com.netflix.mediaclient.event.service.FacebookAuthorized;
import android.os.Bundle;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.service.AuthorizationCanceled;
import com.netflix.mediaclient.event.service.Service;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.Log;
import com.facebook.android.Facebook;

public class FacebookAgent
{
    private static final String FACEBOOK_APP_ID = "com.facebook.katana";
    private static final String TAG = "FacebookAgent";
    private boolean authorized;
    private final Facebook facebook;
    private boolean resultReported;
    
    public FacebookAgent() {
        Log.d("FacebookAgent", "Creating Facebook proxy...");
        this.facebook = new Facebook(SecurityRepository.getFacebookId());
        Log.d("FacebookAgent", "Created Facebook proxy.");
        this.resultReported = false;
    }
    
    public boolean isNativeServiceAppAvailable(final NetflixActivity netflixActivity) {
        return AndroidUtils.getPackageInfo((Context)netflixActivity, "com.facebook.katana") != null;
    }
    
    public void login(final NetflixActivity netflixActivity, final String[] array) {
        ThreadUtils.assertNotOnMain();
        Log.d("FacebookAgent", "Facebook::authorize: started, always go to Facebook, do not store anything");
        if (this.facebook == null) {
            Log.e("FacebookAgent", "Facebook::authorize: destroyed");
            return;
        }
        this.resultReported = false;
        String[] array2;
        if ((array2 = array) == null) {
            array2 = new String[0];
        }
        netflixActivity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                FacebookAgent.this.facebook.authorize(netflixActivity, array2, (Facebook.DialogListener)new Facebook.DialogListener() {
                    @Override
                    public void onCancel() {
                        Log.e("FacebookAgent", "onCancel:: user canceled facebook SSO.");
                        if (!FacebookAgent.this.resultReported) {
                            netflixActivity.getNetflixApplication().publishEvent(new AuthorizationCanceled(Service.facebook));
                            FacebookAgent.this.resultReported = true;
                            return;
                        }
                        Log.w("FacebookAgent", "Result already reported!");
                    }
                    
                    @Override
                    public void onComplete(final Bundle bundle) {
                        if (Log.isLoggable("FacebookAgent", 3)) {
                            Log.d("FacebookAgent", "onComplete:: we are authorized to access Facebook. Save credentials and send event to UI");
                            Log.d("FacebookAgent", "Access token from Facebook " + FacebookAgent.this.facebook.getAccessToken());
                            Log.d("FacebookAgent", "Expires from Facebook " + FacebookAgent.this.facebook.getAccessExpires());
                        }
                        if (!FacebookAgent.this.resultReported) {
                            netflixActivity.getNetflixApplication().publishEvent(new FacebookAuthorized(FacebookAgent.this.facebook.getAccessToken()));
                            FacebookAgent.this.resultReported = true;
                            return;
                        }
                        Log.w("FacebookAgent", "Result already reported!");
                    }
                    
                    @Override
                    public void onError(final DialogError dialogError) {
                        Log.e("FacebookAgent", "onError:: facebook client is not installed, webview is used for SSO.", dialogError);
                        if (!FacebookAgent.this.resultReported) {
                            netflixActivity.getNetflixApplication().publishEvent(new AuthorizationFailed(Service.facebook));
                            FacebookAgent.this.resultReported = true;
                            return;
                        }
                        Log.w("FacebookAgent", "Result already reported!");
                    }
                    
                    @Override
                    public void onFacebookError(final FacebookError facebookError) {
                        Log.e("FacebookAgent", "onFacebookError:: Facebook client was used for SSO", facebookError);
                        if (!FacebookAgent.this.resultReported) {
                            netflixActivity.getNetflixApplication().publishEvent(new AuthorizationFailed(Service.facebook));
                            FacebookAgent.this.resultReported = true;
                            return;
                        }
                        Log.w("FacebookAgent", "Result already reported!");
                    }
                });
            }
        });
        Log.d("FacebookAgent", "Facebook::authorize: started done.");
    }
    
    public void loginCallback(final int n, final int n2, final Intent intent) {
        if (this.facebook != null) {
            try {
                this.facebook.authorizeCallback(n, n2, intent);
                return;
            }
            catch (Exception ex) {
                Log.e("FacebookAgent", "Login failed. Ignore exception, it was already reported", ex);
                return;
            }
        }
        Log.e("FacebookAgent", "Facebook::authorizeCallback: destroyed");
    }
    
    public void logout(final NetflixActivity netflixActivity) {
        Log.d("FacebookAgent", "Facebook::deauthorize: started");
        if (this.facebook == null) {
            Log.e("FacebookAgent", "Facebook::deauthorize: destroyed");
            return;
        }
        if (!this.authorized) {
            Log.e("FacebookAgent", "Facebook::deauthorize: Not authorized!");
            return;
        }
        while (true) {
            try {
                this.facebook.logout((Context)netflixActivity);
                this.authorized = false;
                Log.d("FacebookAgent", "Facebook::deauthorize: started done.");
            }
            catch (Exception ex) {
                Log.e("FacebookAgent", "Failed to logout", ex);
                continue;
            }
            break;
        }
    }
}
