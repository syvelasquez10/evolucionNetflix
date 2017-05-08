// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

import java.util.HashMap;
import java.util.Map;
import com.facebook.react.bridge.BaseJavaModule;

public class SourceCodeModule extends BaseJavaModule
{
    private final String mSourceUrl;
    
    public SourceCodeModule(final String mSourceUrl) {
        this.mSourceUrl = mSourceUrl;
    }
    
    @Override
    public Map<String, Object> getConstants() {
        final HashMap<String, String> hashMap = (HashMap<String, String>)new HashMap<String, Object>();
        hashMap.put("scriptURL", this.mSourceUrl);
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "RCTSourceCode";
    }
}
