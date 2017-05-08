// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import android.view.TextureView$SurfaceTextureListener;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.uimanager.BaseViewManager;

public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, ARTSurfaceViewShadowNode>
{
    private static final YogaMeasureFunction MEASURE_FUNCTION;
    protected static final String REACT_CLASS = "ARTSurfaceView";
    
    static {
        MEASURE_FUNCTION = new ARTSurfaceViewManager$1();
    }
    
    @Override
    public ARTSurfaceViewShadowNode createShadowNodeInstance() {
        final ARTSurfaceViewShadowNode artSurfaceViewShadowNode = new ARTSurfaceViewShadowNode();
        artSurfaceViewShadowNode.setMeasureFunction(ARTSurfaceViewManager.MEASURE_FUNCTION);
        return artSurfaceViewShadowNode;
    }
    
    @Override
    protected ARTSurfaceView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ARTSurfaceView((Context)themedReactContext);
    }
    
    @Override
    public String getName() {
        return "ARTSurfaceView";
    }
    
    @Override
    public Class<ARTSurfaceViewShadowNode> getShadowNodeClass() {
        return ARTSurfaceViewShadowNode.class;
    }
    
    @Override
    public void updateExtraData(final ARTSurfaceView artSurfaceView, final Object o) {
        artSurfaceView.setSurfaceTextureListener((TextureView$SurfaceTextureListener)o);
    }
}
