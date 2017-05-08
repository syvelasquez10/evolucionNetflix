// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text.frescosupport;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import android.view.View;
import com.facebook.react.uimanager.ViewManager;

public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, FrescoBasedReactTextInlineImageShadowNode>
{
    protected static final String REACT_CLASS = "RCTTextInlineImage";
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    
    public FrescoBasedReactTextInlineImageViewManager() {
        this(null, null);
    }
    
    public FrescoBasedReactTextInlineImageViewManager(final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mCallerContext = mCallerContext;
    }
    
    @Override
    public FrescoBasedReactTextInlineImageShadowNode createShadowNodeInstance() {
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder;
        if (this.mDraweeControllerBuilder != null) {
            abstractDraweeControllerBuilder = this.mDraweeControllerBuilder;
        }
        else {
            abstractDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return new FrescoBasedReactTextInlineImageShadowNode(abstractDraweeControllerBuilder, this.mCallerContext);
    }
    
    public View createViewInstance(final ThemedReactContext themedReactContext) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }
    
    @Override
    public String getName() {
        return "RCTTextInlineImage";
    }
    
    @Override
    public Class<FrescoBasedReactTextInlineImageShadowNode> getShadowNodeClass() {
        return FrescoBasedReactTextInlineImageShadowNode.class;
    }
    
    @Override
    public void updateExtraData(final View view, final Object o) {
    }
}
