// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.social;

import android.content.Intent;
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
        netflixActivity.runOnUiThread((Runnable)new FacebookAgent$1(this, netflixActivity, array2));
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
