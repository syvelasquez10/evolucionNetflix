// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactContext;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ReactScrollViewCommandHelper$ScrollCommandHandler<ReactScrollView>
{
    protected static final String REACT_CLASS = "RCTScrollView";
    private FpsListener mFpsListener;
    
    public ReactScrollViewManager() {
        this(null);
    }
    
    public ReactScrollViewManager(final FpsListener mFpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = mFpsListener;
    }
    
    public static Map createExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.of("registrationName", "onScroll")).put(ScrollEventType.BEGIN_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollBeginDrag")).put(ScrollEventType.END_DRAG.getJSEventName(), MapBuilder.of("registrationName", "onScrollEndDrag")).put(ScrollEventType.ANIMATION_END.getJSEventName(), MapBuilder.of("registrationName", "onScrollAnimationEnd")).put(ScrollEventType.MOMENTUM_BEGIN.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollBegin")).put(ScrollEventType.MOMENTUM_END.getJSEventName(), MapBuilder.of("registrationName", "onMomentumScrollEnd")).build();
    }
    
    public ReactScrollView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactScrollView(themedReactContext, this.mFpsListener);
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return ReactScrollViewCommandHelper.getCommandsMap();
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }
    
    @Override
    public String getName() {
        return "RCTScrollView";
    }
    
    public void receiveCommand(final ReactScrollView reactScrollView, final int n, final ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, reactScrollView, n, readableArray);
    }
    
    @Override
    public void scrollTo(final ReactScrollView reactScrollView, final ReactScrollViewCommandHelper$ScrollToCommandData reactScrollViewCommandHelper$ScrollToCommandData) {
        if (reactScrollViewCommandHelper$ScrollToCommandData.mAnimated) {
            reactScrollView.smoothScrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
            return;
        }
        reactScrollView.scrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
    }
    
    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(final ReactScrollView reactScrollView, final int endFillColor) {
        reactScrollView.setEndFillColor(endFillColor);
    }
    
    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(final ReactScrollView reactScrollView, final boolean removeClippedSubviews) {
        reactScrollView.setRemoveClippedSubviews(removeClippedSubviews);
    }
    
    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(final ReactScrollView reactScrollView, final boolean scrollEnabled) {
        reactScrollView.setScrollEnabled(scrollEnabled);
    }
    
    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(final ReactScrollView reactScrollView, final String scrollPerfTag) {
        reactScrollView.setScrollPerfTag(scrollPerfTag);
    }
    
    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(final ReactScrollView reactScrollView, final boolean sendMomentumEvents) {
        reactScrollView.setSendMomentumEvents(sendMomentumEvents);
    }
    
    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(final ReactScrollView reactScrollView, final boolean verticalScrollBarEnabled) {
        reactScrollView.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
    }
}
