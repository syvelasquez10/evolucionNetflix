// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.LayerDrawable;
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
    
    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(final ReactSlider reactSlider, final Integer n) {
        final Drawable drawableByLayerId = ((LayerDrawable)reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (n == null) {
            drawableByLayerId.clearColorFilter();
            return;
        }
        drawableByLayerId.setColorFilter((int)n, PorterDuff$Mode.SRC_IN);
    }
    
    @ReactProp(defaultDouble = 1.0, name = "maximumValue")
    public void setMaximumValue(final ReactSlider reactSlider, final double maxValue) {
        reactSlider.setMaxValue(maxValue);
    }
    
    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(final ReactSlider reactSlider, final Integer n) {
        final Drawable drawableByLayerId = ((LayerDrawable)reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (n == null) {
            drawableByLayerId.clearColorFilter();
            return;
        }
        drawableByLayerId.setColorFilter((int)n, PorterDuff$Mode.SRC_IN);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "minimumValue")
    public void setMinimumValue(final ReactSlider reactSlider, final double minValue) {
        reactSlider.setMinValue(minValue);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "step")
    public void setStep(final ReactSlider reactSlider, final double step) {
        reactSlider.setStep(step);
    }
    
    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(final ReactSlider reactSlider, final Integer n) {
        if (n == null) {
            reactSlider.getThumb().clearColorFilter();
            return;
        }
        reactSlider.getThumb().setColorFilter((int)n, PorterDuff$Mode.SRC_IN);
    }
    
    @ReactProp(defaultDouble = 0.0, name = "value")
    public void setValue(final ReactSlider reactSlider, final double value) {
        reactSlider.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
        reactSlider.setValue(value);
        reactSlider.setOnSeekBarChangeListener(ReactSliderManager.ON_CHANGE_LISTENER);
    }
}
