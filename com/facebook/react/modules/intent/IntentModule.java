// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.intent;

import android.content.ComponentName;
import android.app.Activity;
import com.facebook.react.bridge.ReactMethod;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class IntentModule extends ReactContextBaseJavaModule
{
    public IntentModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @ReactMethod
    public void canOpenURL(final String s, final Promise promise) {
        if (s == null || s.isEmpty()) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + s));
            return;
        }
        while (true) {
            while (true) {
                try {
                    final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent.addFlags(268435456);
                    if (intent.resolveActivity(this.getReactApplicationContext().getPackageManager()) != null) {
                        final boolean b = true;
                        promise.resolve(b);
                        return;
                    }
                }
                catch (Exception ex) {
                    promise.reject(new JSApplicationIllegalArgumentException("Could not check if URL '" + s + "' can be opened: " + ex.getMessage()));
                    return;
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    @ReactMethod
    public void getInitialURL(final Promise promise) {
        try {
            final Activity currentActivity = this.getCurrentActivity();
            Object string;
            final Object o = string = null;
            if (currentActivity != null) {
                final Intent intent = currentActivity.getIntent();
                final String action = intent.getAction();
                final Uri data = intent.getData();
                string = o;
                if ("android.intent.action.VIEW".equals(action)) {
                    string = o;
                    if (data != null) {
                        string = data.toString();
                    }
                }
            }
            promise.resolve(string);
        }
        catch (Exception ex) {
            promise.reject(new JSApplicationIllegalArgumentException("Could not get the initial URL : " + ex.getMessage()));
        }
    }
    
    @Override
    public String getName() {
        return "IntentAndroid";
    }
    
    @ReactMethod
    public void openURL(final String s, final Promise promise) {
        if (s == null || s.isEmpty()) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + s));
            return;
        }
    Label_0130_Outer:
        while (true) {
            while (true) {
                Intent intent = null;
            Label_0193:
                while (true) {
                    try {
                        final Activity currentActivity = this.getCurrentActivity();
                        intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
                        final String packageName = this.getReactApplicationContext().getPackageName();
                        final ComponentName resolveActivity = intent.resolveActivity(this.getReactApplicationContext().getPackageManager());
                        if (resolveActivity != null) {
                            final String packageName2 = resolveActivity.getPackageName();
                            if (currentActivity == null || !packageName.equals(packageName2)) {
                                intent.addFlags(268435456);
                            }
                            if (currentActivity != null) {
                                currentActivity.startActivity(intent);
                                promise.resolve(true);
                                return;
                            }
                            break Label_0193;
                        }
                    }
                    catch (Exception ex) {
                        promise.reject(new JSApplicationIllegalArgumentException("Could not open URL '" + s + "': " + ex.getMessage()));
                        return;
                    }
                    final String packageName2 = "";
                    continue Label_0130_Outer;
                }
                this.getReactApplicationContext().startActivity(intent);
                continue;
            }
        }
    }
}
