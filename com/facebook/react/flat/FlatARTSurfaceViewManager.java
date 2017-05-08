// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.TextureView$SurfaceTextureListener;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.views.art.ARTSurfaceView;
import com.facebook.react.uimanager.BaseViewManager;

public class FlatARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, FlatARTSurfaceViewShadowNode>
{
    private static final YogaMeasureFunction MEASURE_FUNCTION;
    private static final String REACT_CLASS = "ARTSurfaceView";
    
    static {
        MEASURE_FUNCTION = new FlatARTSurfaceViewManager$1();
    }
    
    @Override
    public FlatARTSurfaceViewShadowNode createShadowNodeInstance() {
        final FlatARTSurfaceViewShadowNode flatARTSurfaceViewShadowNode = new FlatARTSurfaceViewShadowNode();
        flatARTSurfaceViewShadowNode.setMeasureFunction(FlatARTSurfaceViewManager.MEASURE_FUNCTION);
        return flatARTSurfaceViewShadowNode;
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
    public Class<FlatARTSurfaceViewShadowNode> getShadowNodeClass() {
        return FlatARTSurfaceViewShadowNode.class;
    }
    
    @Override
    public void updateExtraData(final ARTSurfaceView artSurfaceView, final Object o) {
        artSurfaceView.setSurfaceTextureListener((TextureView$SurfaceTextureListener)o);
    }
}
