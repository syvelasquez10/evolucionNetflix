// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.content.Context;
import com.facebook.react.bridge.ReactMethod;
import android.app.Activity;
import com.facebook.react.bridge.UiThreadUtil;
import android.os.Build$VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Map;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class StatusBarModule extends ReactContextBaseJavaModule
{
    private static final String HEIGHT_KEY = "HEIGHT";
    
    public StatusBarModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @Override
    public Map<String, Object> getConstants() {
        final ReactApplicationContext reactApplicationContext = this.getReactApplicationContext();
        final int identifier = ((Context)reactApplicationContext).getResources().getIdentifier("status_bar_height", "dimen", "android");
        float dipFromPixel;
        if (identifier > 0) {
            dipFromPixel = PixelUtil.toDIPFromPixel(((Context)reactApplicationContext).getResources().getDimensionPixelSize(identifier));
        }
        else {
            dipFromPixel = 0.0f;
        }
        return (Map<String, Object>)MapBuilder.of("HEIGHT", dipFromPixel);
    }
    
    @Override
    public String getName() {
        return "StatusBarManager";
    }
    
    @ReactMethod
    public void setColor(final int n, final boolean b) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new StatusBarModule$1(this, b, currentActivity, n));
        }
    }
    
    @ReactMethod
    public void setHidden(final boolean b) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
            return;
        }
        UiThreadUtil.runOnUiThread(new StatusBarModule$3(this, b, currentActivity));
    }
    
    @ReactMethod
    public void setStyle(final String s) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            UiThreadUtil.runOnUiThread(new StatusBarModule$4(this, currentActivity, s));
        }
    }
    
    @ReactMethod
    public void setTranslucent(final boolean b) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            FLog.w("React", "StatusBarModule: Ignored status bar change, current activity is null.");
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new StatusBarModule$2(this, currentActivity, b));
        }
    }
}
