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
    @Override
    public Map<String, Object> getConstants() {
        final HashMap<String, Integer> hashMap = (HashMap<String, Integer>)new HashMap<String, Object>();
        hashMap.put("Version", Build$VERSION.SDK_INT);
        hashMap.put("ServerHost", (Integer)AndroidInfoHelpers.getServerHost());
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "AndroidConstants";
    }
}
