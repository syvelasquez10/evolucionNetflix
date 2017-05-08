// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;

public final class RCTImageViewManager extends FlatViewManager
{
    private final Object mCallerContext;
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    
    public RCTImageViewManager() {
        this(null, null);
    }
    
    public RCTImageViewManager(final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mCallerContext = mCallerContext;
    }
    
    @Override
    public RCTImageView createShadowNodeInstance() {
        return new RCTImageView((T)new DrawImageWithDrawee());
    }
    
    public Object getCallerContext() {
        return this.mCallerContext;
    }
    
    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }
    
    @Override
    public String getName() {
        return "RCTImageView";
    }
    
    @Override
    public Class<RCTImageView> getShadowNodeClass() {
        return RCTImageView.class;
    }
}
