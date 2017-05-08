// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.views.modal.ReactModalHostManager;

public class RCTModalHostManager extends ReactModalHostManager
{
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new FlatReactModalShadowNode();
    }
    
    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return FlatReactModalShadowNode.class;
    }
}
