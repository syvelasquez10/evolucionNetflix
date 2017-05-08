// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.toast;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.HashMap;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ToastModule extends ReactContextBaseJavaModule
{
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";
    
    public ToastModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @Override
    public Map<String, Object> getConstants() {
        final HashMap<String, Integer> hashMap = (HashMap<String, Integer>)MapBuilder.newHashMap();
        hashMap.put("SHORT", 0);
        hashMap.put("LONG", 1);
        hashMap.put("TOP", 49);
        hashMap.put("BOTTOM", 81);
        hashMap.put("CENTER", 17);
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "ToastAndroid";
    }
    
    @ReactMethod
    public void show(final String s, final int n) {
        UiThreadUtil.runOnUiThread(new ToastModule$1(this, s, n));
    }
    
    @ReactMethod
    public void showWithGravity(final String s, final int n, final int n2) {
        UiThreadUtil.runOnUiThread(new ToastModule$2(this, s, n, n2));
    }
}
