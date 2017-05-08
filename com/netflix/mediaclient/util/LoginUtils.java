// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.android.app.Status;
import com.google.android.gms.auth.api.credentials.Credential;

public class LoginUtils
{
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_STATUS = "status";
    private static final String TAG = "nf_login_utils";
    
    public static void addCredentialsToIntent(final Credential credential, final Status status, final Intent intent) {
        if (credential != null) {
            final String id = credential.getId();
            final String password = credential.getPassword();
            if (Log.isLoggable()) {
                Log.d("nf_login_utils", "Credentials received " + credential);
                Log.d("nf_login_utils", "Email in credentials: " + id);
                Log.d("nf_login_utils", "Password in credentials: " + password);
            }
            if (StringUtils.isNotEmpty(id)) {
                intent.putExtra("email", id);
                if (StringUtils.isNotEmpty(password)) {
                    intent.putExtra("password", password);
                }
            }
            if (status != null) {
                intent.putExtra("status", (Serializable)status);
            }
        }
    }
    
    private static boolean isAutoLoginEnabled() {
        return true;
    }
    
    public static boolean shouldUseAutoLogin(final Context context) {
        return isAutoLoginEnabled() && DeviceUtils.canUseGooglePlayServices(context);
    }
}
