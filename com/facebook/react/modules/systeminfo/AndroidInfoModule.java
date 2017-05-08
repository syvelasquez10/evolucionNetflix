// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.systeminfo;

import android.os.Build$VERSION;
import java.util.HashMap;
import java.util.Map;
import com.facebook.react.bridge.BaseJavaModule;

public class AndroidInfoModule extends BaseJavaModule
{
    private static final String IS_TESTING = "IS_TESTING";
    
    @Override
    public Map<String, Object> getConstants() {
        final HashMap<String, Integer> hashMap = (HashMap<String, Integer>)new HashMap<String, Object>();
        hashMap.put("Version", Build$VERSION.SDK_INT);
        hashMap.put("ServerHost", (Integer)AndroidInfoHelpers.getServerHost());
        hashMap.put("isTesting", (Integer)(Object)"true".equals(System.getProperty("IS_TESTING")));
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "AndroidConstants";
    }
}
