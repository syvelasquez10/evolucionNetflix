// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ThemedReactContext;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager>
{
    public static final int COMMAND_SET_PAGE = 1;
    public static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    protected static final String REACT_CLASS = "AndroidViewPager";
    
    @Override
    public void addView(final ReactViewPager reactViewPager, final View view, final int n) {
        reactViewPager.addViewToAdapter(view, n);
    }
    
    @Override
    protected ReactViewPager createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactViewPager(themedReactContext);
    }
    
    @Override
    public View getChildAt(final ReactViewPager reactViewPager, final int n) {
        return reactViewPager.getViewFromAdapter(n);
    }
    
    @Override
    public int getChildCount(final ReactViewPager reactViewPager) {
        return reactViewPager.getViewCountInAdapter();
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("setPage", 1, "setPageWithoutAnimation", 2);
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topPageScroll", MapBuilder.of("registrationName", "onPageScroll"), "topPageScrollStateChanged", MapBuilder.of("registrationName", "onPageScrollStateChanged"), "topPageSelected", MapBuilder.of("registrationName", "onPageSelected"));
    }
    
    @Override
    public String getName() {
        return "AndroidViewPager";
    }
    
    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }
    
    public void receiveCommand(final ReactViewPager reactViewPager, final int n, final ReadableArray readableArray) {
        Assertions.assertNotNull(reactViewPager);
        Assertions.assertNotNull(readableArray);
        switch (n) {
            default: {
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", n, this.getClass().getSimpleName()));
            }
            case 1: {
                reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), true);
            }
            case 2: {
                reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), false);
            }
        }
    }
    
    @Override
    public void removeViewAt(final ReactViewPager reactViewPager, final int n) {
        reactViewPager.removeViewFromAdapter(n);
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "pageMargin")
    public void setPageMargin(final ReactViewPager reactViewPager, final float n) {
        reactViewPager.setPageMargin((int)PixelUtil.toPixelFromDIP(n));
    }
    
    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(final ReactViewPager reactViewPager, final boolean scrollEnabled) {
        reactViewPager.setScrollEnabled(scrollEnabled);
    }
}
