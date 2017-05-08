// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactHorizontalScrollViewManager extends ViewGroupManager<ReactHorizontalScrollView> implements ReactScrollViewCommandHelper$ScrollCommandHandler<ReactHorizontalScrollView>
{
    protected static final String REACT_CLASS = "AndroidHorizontalScrollView";
    private FpsListener mFpsListener;
    
    public ReactHorizontalScrollViewManager() {
        this(null);
    }
    
    public ReactHorizontalScrollViewManager(final FpsListener mFpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = mFpsListener;
    }
    
    public ReactHorizontalScrollView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactHorizontalScrollView((Context)themedReactContext, this.mFpsListener);
    }
    
    @Override
    public String getName() {
        return "AndroidHorizontalScrollView";
    }
    
    public void receiveCommand(final ReactHorizontalScrollView reactHorizontalScrollView, final int n, final ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, reactHorizontalScrollView, n, readableArray);
    }
    
    @Override
    public void scrollTo(final ReactHorizontalScrollView reactHorizontalScrollView, final ReactScrollViewCommandHelper$ScrollToCommandData reactScrollViewCommandHelper$ScrollToCommandData) {
        if (reactScrollViewCommandHelper$ScrollToCommandData.mAnimated) {
            reactHorizontalScrollView.smoothScrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
            return;
        }
        reactHorizontalScrollView.scrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
    }
    
    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(final ReactHorizontalScrollView reactHorizontalScrollView, final int endFillColor) {
        reactHorizontalScrollView.setEndFillColor(endFillColor);
    }
    
    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(final ReactHorizontalScrollView reactHorizontalScrollView, final boolean pagingEnabled) {
        reactHorizontalScrollView.setPagingEnabled(pagingEnabled);
    }
    
    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(final ReactHorizontalScrollView reactHorizontalScrollView, final boolean removeClippedSubviews) {
        reactHorizontalScrollView.setRemoveClippedSubviews(removeClippedSubviews);
    }
    
    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(final ReactHorizontalScrollView reactHorizontalScrollView, final boolean scrollEnabled) {
        reactHorizontalScrollView.setScrollEnabled(scrollEnabled);
    }
    
    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(final ReactHorizontalScrollView reactHorizontalScrollView, final String scrollPerfTag) {
        reactHorizontalScrollView.setScrollPerfTag(scrollPerfTag);
    }
    
    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(final ReactHorizontalScrollView reactHorizontalScrollView, final boolean sendMomentumEvents) {
        reactHorizontalScrollView.setSendMomentumEvents(sendMomentumEvents);
    }
    
    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(final ReactHorizontalScrollView reactHorizontalScrollView, final boolean horizontalScrollBarEnabled) {
        reactHorizontalScrollView.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
    }
}
