// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.uimanager.ReactShadowNode;

public final class RCTVirtualTextManager extends VirtualViewManager<RCTVirtualText>
{
    @Override
    public RCTVirtualText createShadowNodeInstance() {
        return new RCTVirtualText();
    }
    
    @Override
    public String getName() {
        return "RCTVirtualText";
    }
    
    @Override
    public Class<RCTVirtualText> getShadowNodeClass() {
        return RCTVirtualText.class;
    }
}
