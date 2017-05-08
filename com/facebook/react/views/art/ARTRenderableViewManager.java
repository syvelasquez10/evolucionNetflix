// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;
import android.view.View;
import com.facebook.react.uimanager.ViewManager;

public class ARTRenderableViewManager extends ViewManager<View, ReactShadowNode>
{
    static final String CLASS_GROUP = "ARTGroup";
    static final String CLASS_SHAPE = "ARTShape";
    static final String CLASS_TEXT = "ARTText";
    private final String mClassName;
    
    private ARTRenderableViewManager(final String mClassName) {
        this.mClassName = mClassName;
    }
    
    public static ARTRenderableViewManager createARTGroupViewManager() {
        return new ARTRenderableViewManager("ARTGroup");
    }
    
    public static ARTRenderableViewManager createARTShapeViewManager() {
        return new ARTRenderableViewManager("ARTShape");
    }
    
    public static ARTRenderableViewManager createARTTextViewManager() {
        return new ARTRenderableViewManager("ARTText");
    }
    
    @Override
    public ReactShadowNode createShadowNodeInstance() {
        if ("ARTGroup".equals(this.mClassName)) {
            return new ARTGroupShadowNode();
        }
        if ("ARTShape".equals(this.mClassName)) {
            return new ARTShapeShadowNode();
        }
        if ("ARTText".equals(this.mClassName)) {
            return new ARTTextShadowNode();
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }
    
    @Override
    protected View createViewInstance(final ThemedReactContext themedReactContext) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
    
    @Override
    public String getName() {
        return this.mClassName;
    }
    
    @Override
    public Class<? extends ReactShadowNode> getShadowNodeClass() {
        if ("ARTGroup".equals(this.mClassName)) {
            return ARTGroupShadowNode.class;
        }
        if ("ARTShape".equals(this.mClassName)) {
            return ARTShapeShadowNode.class;
        }
        if ("ARTText".equals(this.mClassName)) {
            return ARTTextShadowNode.class;
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }
    
    @Override
    public void updateExtraData(final View view, final Object o) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
}
