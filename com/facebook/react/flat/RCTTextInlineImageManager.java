// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.uimanager.ReactShadowNode;

public final class RCTTextInlineImageManager extends VirtualViewManager<RCTTextInlineImage>
{
    @Override
    public RCTTextInlineImage createShadowNodeInstance() {
        return new RCTTextInlineImage();
    }
    
    @Override
    public String getName() {
        return "RCTTextInlineImage";
    }
    
    @Override
    public Class<RCTTextInlineImage> getShadowNodeClass() {
        return RCTTextInlineImage.class;
    }
}
