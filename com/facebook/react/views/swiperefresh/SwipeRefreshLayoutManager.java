// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout>
{
    protected static final String REACT_CLASS = "AndroidSwipeRefreshLayout";
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        reactSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutManager$1(this, themedReactContext, reactSwipeRefreshLayout));
    }
    
    @Override
    protected ReactSwipeRefreshLayout createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactSwipeRefreshLayout(themedReactContext);
    }
    
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return (Map<String, Object>)MapBuilder.builder().put("topRefresh", MapBuilder.of("registrationName", "onRefresh")).build();
    }
    
    @Override
    public Map<String, Object> getExportedViewConstants() {
        return (Map<String, Object>)MapBuilder.of("SIZE", MapBuilder.of("DEFAULT", 1, "LARGE", 0));
    }
    
    @Override
    public String getName() {
        return "AndroidSwipeRefreshLayout";
    }
    
    @ReactProp(customType = "ColorArray", name = "colors")
    public void setColors(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final ReadableArray readableArray) {
        int i = 0;
        if (readableArray != null) {
            final int[] colorSchemeColors = new int[readableArray.size()];
            while (i < readableArray.size()) {
                colorSchemeColors[i] = readableArray.getInt(i);
                ++i;
            }
            reactSwipeRefreshLayout.setColorSchemeColors(colorSchemeColors);
            return;
        }
        reactSwipeRefreshLayout.setColorSchemeColors(new int[0]);
    }
    
    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final boolean enabled) {
        reactSwipeRefreshLayout.setEnabled(enabled);
    }
    
    @ReactProp(customType = "Color", defaultInt = 0, name = "progressBackgroundColor")
    public void setProgressBackgroundColor(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final int progressBackgroundColorSchemeColor) {
        reactSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(progressBackgroundColorSchemeColor);
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final float progressViewOffset) {
        reactSwipeRefreshLayout.setProgressViewOffset(progressViewOffset);
    }
    
    @ReactProp(name = "refreshing")
    public void setRefreshing(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final boolean refreshing) {
        reactSwipeRefreshLayout.setRefreshing(refreshing);
    }
    
    @ReactProp(defaultInt = 1, name = "size")
    public void setSize(final ReactSwipeRefreshLayout reactSwipeRefreshLayout, final int size) {
        reactSwipeRefreshLayout.setSize(size);
    }
}
