// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.uimanager.ReactShadowNode;

public final class RCTRawTextManager extends VirtualViewManager<RCTRawText>
{
    @Override
    public RCTRawText createShadowNodeInstance() {
        return new RCTRawText();
    }
    
    @Override
    public String getName() {
        return "RCTRawText";
    }
    
    @Override
    public Class<RCTRawText> getShadowNodeClass() {
        return RCTRawText.class;
    }
}
