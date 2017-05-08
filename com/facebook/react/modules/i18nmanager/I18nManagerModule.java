// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.i18nmanager;

import java.util.HashMap;
import java.util.Locale;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactMethod;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class I18nManagerModule extends ReactContextBaseJavaModule
{
    private final I18nUtil sharedI18nUtilInstance;
    
    public I18nManagerModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.sharedI18nUtilInstance = I18nUtil.getInstance();
    }
    
    @ReactMethod
    public void allowRTL(final boolean b) {
        this.sharedI18nUtilInstance.allowRTL((Context)this.getReactApplicationContext(), b);
    }
    
    @ReactMethod
    public void forceRTL(final boolean b) {
        this.sharedI18nUtilInstance.forceRTL((Context)this.getReactApplicationContext(), b);
    }
    
    @Override
    public Map<String, Object> getConstants() {
        final Locale locale = this.getReactApplicationContext().getBaseContext().getResources().getConfiguration().locale;
        final HashMap<String, String> hashMap = (HashMap<String, String>)MapBuilder.newHashMap();
        hashMap.put("isRTL", this.sharedI18nUtilInstance.isRTL((Context)this.getReactApplicationContext()));
        hashMap.put("localeIdentifier", locale.toString());
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "I18nManager";
    }
}
