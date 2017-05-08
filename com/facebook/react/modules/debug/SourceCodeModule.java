// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

import com.facebook.infer.annotation.Assertions;
import java.util.HashMap;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.BaseJavaModule;

public class SourceCodeModule extends BaseJavaModule
{
    public static final String NAME = "SourceCode";
    private final ReactContext mReactContext;
    
    public SourceCodeModule(final ReactContext mReactContext) {
        this.mReactContext = mReactContext;
    }
    
    @Override
    public Map<String, Object> getConstants() {
        final HashMap<String, String> hashMap = (HashMap<String, String>)new HashMap<String, Object>();
        hashMap.put("scriptURL", Assertions.assertNotNull(this.mReactContext.getCatalystInstance().getSourceURL(), "No source URL loaded, have you initialised the instance?"));
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    public String getName() {
        return "SourceCode";
    }
}
