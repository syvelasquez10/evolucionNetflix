// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.dialog;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import android.os.Bundle;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.common.logging.FLog;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import java.io.Serializable;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class DialogModule extends ReactContextBaseJavaModule implements LifecycleEventListener
{
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    static final Map<String, Object> CONSTANTS;
    static final String FRAGMENT_TAG = "com.facebook.catalyst.react.dialog.DialogModule";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_TITLE = "title";
    static final String NAME = "DialogManagerAndroid";
    private boolean mIsInForeground;
    
    static {
        CONSTANTS = MapBuilder.of("buttonClicked", "buttonClicked", "dismissed", "dismissed", "buttonPositive", -1, "buttonNegative", -2, "buttonNeutral", -3);
    }
    
    public DialogModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private DialogModule$FragmentManagerHelper getFragmentManagerHelper() {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        if (currentActivity instanceof FragmentActivity) {
            return new DialogModule$FragmentManagerHelper(this, ((FragmentActivity)currentActivity).getSupportFragmentManager());
        }
        return new DialogModule$FragmentManagerHelper(this, currentActivity.getFragmentManager());
    }
    
    @Override
    public Map<String, Object> getConstants() {
        return DialogModule.CONSTANTS;
    }
    
    @Override
    public String getName() {
        return "DialogManagerAndroid";
    }
    
    @Override
    public void initialize() {
        this.getReactApplicationContext().addLifecycleEventListener(this);
    }
    
    @Override
    public void onHostDestroy() {
    }
    
    @Override
    public void onHostPause() {
        this.mIsInForeground = false;
    }
    
    @Override
    public void onHostResume() {
        this.mIsInForeground = true;
        final DialogModule$FragmentManagerHelper fragmentManagerHelper = this.getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.showPendingAlert();
            return;
        }
        FLog.w(DialogModule.class, "onHostResume called but no FragmentManager found");
    }
    
    @ReactMethod
    public void showAlert(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        int i = 0;
        final DialogModule$FragmentManagerHelper fragmentManagerHelper = this.getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            callback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        final Bundle bundle = new Bundle();
        if (readableMap.hasKey("title")) {
            bundle.putString("title", readableMap.getString("title"));
        }
        if (readableMap.hasKey("message")) {
            bundle.putString("message", readableMap.getString("message"));
        }
        if (readableMap.hasKey("buttonPositive")) {
            bundle.putString("button_positive", readableMap.getString("buttonPositive"));
        }
        if (readableMap.hasKey("buttonNegative")) {
            bundle.putString("button_negative", readableMap.getString("buttonNegative"));
        }
        if (readableMap.hasKey("buttonNeutral")) {
            bundle.putString("button_neutral", readableMap.getString("buttonNeutral"));
        }
        if (readableMap.hasKey("items")) {
            final ReadableArray array = readableMap.getArray("items");
            final CharSequence[] array2 = new CharSequence[array.size()];
            while (i < array.size()) {
                array2[i] = array.getString(i);
                ++i;
            }
            bundle.putCharSequenceArray("items", array2);
        }
        if (readableMap.hasKey("cancelable")) {
            bundle.putBoolean("cancelable", readableMap.getBoolean("cancelable"));
        }
        fragmentManagerHelper.showNewAlert(this.mIsInForeground, bundle, callback2);
    }
}
