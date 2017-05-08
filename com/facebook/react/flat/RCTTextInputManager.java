// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.views.textinput.ReactTextInputManager;

public class RCTTextInputManager extends ReactTextInputManager
{
    @Override
    public RCTTextInput createShadowNodeInstance() {
        return new RCTTextInput();
    }
    
    @Override
    public Class<RCTTextInput> getShadowNodeClass() {
        return RCTTextInput.class;
    }
}
