// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.switchview;

import android.graphics.PorterDuff$Mode;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.content.Context;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.facebook.react.uimanager.SimpleViewManager;

public class ReactSwitchManager extends SimpleViewManager<ReactSwitch>
{
    private static final CompoundButton$OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER;
    private static final String REACT_CLASS = "AndroidSwitch";
    
    static {
        ON_CHECKED_CHANGE_LISTENER = (CompoundButton$OnCheckedChangeListener)new ReactSwitchManager$1();
    }
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactSwitch reactSwitch) {
        reactSwitch.setOnCheckedChangeListener(ReactSwitchManager.ON_CHECKED_CHANGE_LISTENER);
    }
    
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSwitchManager$ReactSwitchShadowNode(null);
    }
    
    protected ReactSwitch createViewInstance(final ThemedReactContext themedReactContext) {
        final ReactSwitch reactSwitch = new ReactSwitch((Context)themedReactContext);
        reactSwitch.setShowText(false);
        return reactSwitch;
    }
    
    @Override
    public String getName() {
        return "AndroidSwitch";
    }
    
    @Override
    public Class getShadowNodeClass() {
        return ReactSwitchManager$ReactSwitchShadowNode.class;
    }
    
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(final ReactSwitch reactSwitch, final boolean enabled) {
        reactSwitch.setEnabled(enabled);
    }
    
    @ReactProp(name = "on")
    public void setOn(final ReactSwitch reactSwitch, final boolean on) {
        reactSwitch.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)null);
        reactSwitch.setOn(on);
        reactSwitch.setOnCheckedChangeListener(ReactSwitchManager.ON_CHECKED_CHANGE_LISTENER);
    }
    
    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(final ReactSwitch reactSwitch, final Integer n) {
        if (n == null) {
            reactSwitch.getThumbDrawable().clearColorFilter();
            return;
        }
        reactSwitch.getThumbDrawable().setColorFilter((int)n, PorterDuff$Mode.MULTIPLY);
    }
    
    @ReactProp(customType = "Color", name = "trackTintColor")
    public void setTrackTintColor(final ReactSwitch reactSwitch, final Integer n) {
        if (n == null) {
            reactSwitch.getTrackDrawable().clearColorFilter();
            return;
        }
        reactSwitch.getTrackDrawable().setColorFilter((int)n, PorterDuff$Mode.MULTIPLY);
    }
}
