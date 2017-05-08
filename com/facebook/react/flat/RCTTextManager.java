// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;

public final class RCTTextManager extends FlatViewManager
{
    @Override
    public RCTText createShadowNodeInstance() {
        return new RCTText();
    }
    
    @Override
    public String getName() {
        return "RCTText";
    }
    
    @Override
    public Class<RCTText> getShadowNodeClass() {
        return RCTText.class;
    }
}
