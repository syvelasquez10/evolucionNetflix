// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;

public class ReactRawTextManager extends ReactTextViewManager
{
    public static final String REACT_CLASS = "RCTRawText";
    
    @Override
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactVirtualTextShadowNode();
    }
    
    @Override
    public ReactTextView createViewInstance(final ThemedReactContext themedReactContext) {
        throw new IllegalStateException("RKRawText doesn't map into a native view");
    }
    
    @Override
    public String getName() {
        return "RCTRawText";
    }
    
    @Override
    public void updateExtraData(final ReactTextView reactTextView, final Object o) {
    }
}
