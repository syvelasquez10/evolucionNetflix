// 
// Decompiled by Procyon v0.5.30
// 

package com.BV.LinearGradient;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;

public class LinearGradientManager extends SimpleViewManager<LinearGradientView>
{
    public static final String PROP_BORDER_RADII = "borderRadii";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_END_POS = "end";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POS = "start";
    public static final String REACT_CLASS = "BVLinearGradient";
    
    @Override
    protected LinearGradientView createViewInstance(final ThemedReactContext themedReactContext) {
        return new LinearGradientView((Context)themedReactContext);
    }
    
    @Override
    public String getName() {
        return "BVLinearGradient";
    }
    
    @ReactProp(name = "borderRadii")
    public void setBorderRadii(final LinearGradientView linearGradientView, final ReadableArray borderRadii) {
        linearGradientView.setBorderRadii(borderRadii);
    }
    
    @ReactProp(name = "colors")
    public void setColors(final LinearGradientView linearGradientView, final ReadableArray colors) {
        linearGradientView.setColors(colors);
    }
    
    @ReactProp(name = "end")
    public void setEndPosition(final LinearGradientView linearGradientView, final ReadableArray endPosition) {
        linearGradientView.setEndPosition(endPosition);
    }
    
    @ReactProp(name = "locations")
    public void setLocations(final LinearGradientView linearGradientView, final ReadableArray locations) {
        if (locations != null) {
            linearGradientView.setLocations(locations);
        }
    }
    
    @ReactProp(name = "start")
    public void setStartPosition(final LinearGradientView linearGradientView, final ReadableArray startPosition) {
        linearGradientView.setStartPosition(startPosition);
    }
}
