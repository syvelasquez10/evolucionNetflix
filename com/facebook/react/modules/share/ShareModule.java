// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.share;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import android.app.Activity;
import com.facebook.react.bridge.Arguments;
import android.content.Intent;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ShareModule extends ReactContextBaseJavaModule
{
    static final String ACTION_SHARED = "sharedAction";
    static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";
    
    public ShareModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @Override
    public String getName() {
        return "ShareModule";
    }
    
    @ReactMethod
    public void share(final ReadableMap readableMap, final String s, final Promise promise) {
        if (readableMap == null) {
            promise.reject("E_INVALID_CONTENT", "Content cannot be null");
            return;
        }
        while (true) {
            while (true) {
                Intent chooser;
                try {
                    final Intent intent = new Intent("android.intent.action.SEND");
                    intent.setTypeAndNormalize("text/plain");
                    if (readableMap.hasKey("title")) {
                        intent.putExtra("android.intent.extra.SUBJECT", readableMap.getString("title"));
                    }
                    if (readableMap.hasKey("message")) {
                        intent.putExtra("android.intent.extra.TEXT", readableMap.getString("message"));
                    }
                    chooser = Intent.createChooser(intent, (CharSequence)s);
                    chooser.addCategory("android.intent.category.DEFAULT");
                    final Activity currentActivity = this.getCurrentActivity();
                    if (currentActivity != null) {
                        currentActivity.startActivity(chooser);
                        final WritableMap map = Arguments.createMap();
                        map.putString("action", "sharedAction");
                        promise.resolve(map);
                        return;
                    }
                }
                catch (Exception ex) {
                    promise.reject("E_UNABLE_TO_OPEN_DIALOG", "Failed to open share dialog");
                    return;
                }
                this.getReactApplicationContext().startActivity(chooser);
                continue;
            }
        }
    }
}
