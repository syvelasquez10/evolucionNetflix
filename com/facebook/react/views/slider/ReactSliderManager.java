// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.facebook.react.uimanager.SimpleViewManager;

public class ReactSliderManager extends SimpleViewManager<ReactSlider>
{
    private static final SeekBar$OnSeekBarChangeListener ON_CHANGE_LISTENER;
    private static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;
    
    static {
        ON_CHANGE_LISTENER = (SeekBar$OnSeekBarChangeListener)new ReactSliderManager$1();
    }
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactSlider reactSlider) {
        reactSlider.setOnSeekBarChangeListener(ReactSliderManager.ON_CHANGE_LISTENER);
    }
    
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSliderManager$ReactSliderShadowNode(null);
    }
    
    protected ReactSlider createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactSlider((Context)themedReactContext, null, 16842875);
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topSlidingComplete", MapBuilder.of("registrationName", "onSlidingComplete"));
    }
    
    @Override
    public String getName() {
        return "RCTSlider";
    }
    
    @Override
    public Class getShadowNodeClass() {
        return ReactSliderManager$ReactSliderShadowNode.class;
    }
    
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(final ReactSlider reactSlider, final boolean enabled) {
        reactSlider.setEnabled(enabled);
    }
    
    @ReactProp(defaultDouble = 1.0, name = "maximumValue")
    public void setMaximumValue(final ReactSlider reactSlider, final double maxValue) {
        reactSlider.setMaxValue(maxValue);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "minimumValue")
    public void setMinimumValue(final ReactSlider reactSlider, final double minValue) {
        reactSlider.setMinValue(minValue);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "step")
    public void setStep(final ReactSlider reactSlider, final double step) {
        reactSlider.setStep(step);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "value")
    public void setValue(final ReactSlider reactSlider, final double value) {
        reactSlider.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
        reactSlider.setValue(value);
        reactSlider.setOnSeekBarChangeListener(ReactSliderManager.ON_CHANGE_LISTENER);
    }
}
