// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup.react.manager;

import android.content.Intent;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class TransitionManager extends ReactContextBaseJavaModule
{
    private static final String REGISTRATION_URL = "/getstarted";
    private static final String TAG = "TransitionManager";
    private ReactApplicationContext mContext;
    
    public TransitionManager(final ReactApplicationContext mContext) {
        super(mContext);
        this.mContext = mContext;
    }
    
    @Override
    public String getName() {
        return "TransitionManager";
    }
    
    @ReactMethod
    public void showLogin(final boolean b) {
        Log.d("TransitionManager", "Redirecting to Login screen");
        final Activity currentActivity = this.mContext.getCurrentActivity();
        currentActivity.startActivity(LoginActivity.createStartIntent((Context)currentActivity));
    }
    
    @ReactMethod
    public void showRegistration(final boolean b, String string) {
        final Activity currentActivity = this.mContext.getCurrentActivity();
        string = "/getstarted" + "?locale=" + string;
        final Intent intent = new Intent((Context)currentActivity, (Class)SignupActivity.class);
        intent.putExtra("nextUrl", string);
        currentActivity.startActivity(intent);
    }
}
