// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper$ScrollToCommandData;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper$ScrollCommandHandler;
import com.facebook.react.uimanager.ViewGroupManager;

public class RecyclerViewBackedScrollViewManager extends ViewGroupManager<RecyclerViewBackedScrollView> implements ReactScrollViewCommandHelper$ScrollCommandHandler<RecyclerViewBackedScrollView>
{
    private static final String REACT_CLASS = "AndroidRecyclerViewBackedScrollView";
    
    @Override
    public void addView(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final View view, final int n) {
        recyclerViewBackedScrollView.addViewToAdapter(view, n);
    }
    
    @Override
    protected RecyclerViewBackedScrollView createViewInstance(final ThemedReactContext themedReactContext) {
        return new RecyclerViewBackedScrollView((Context)themedReactContext);
    }
    
    @Override
    public View getChildAt(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final int n) {
        return recyclerViewBackedScrollView.getChildAtFromAdapter(n);
    }
    
    @Override
    public int getChildCount(final RecyclerViewBackedScrollView recyclerViewBackedScrollView) {
        return recyclerViewBackedScrollView.getChildCountFromAdapter();
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.of("registrationName", "onScroll")).build();
    }
    
    @Override
    public String getName() {
        return "AndroidRecyclerViewBackedScrollView";
    }
    
    public void receiveCommand(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final int n, final ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, recyclerViewBackedScrollView, n, readableArray);
    }
    
    @Override
    public void removeViewAt(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final int n) {
        recyclerViewBackedScrollView.removeViewFromAdapter(n);
    }
    
    @Override
    public void scrollTo(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final ReactScrollViewCommandHelper$ScrollToCommandData reactScrollViewCommandHelper$ScrollToCommandData) {
        recyclerViewBackedScrollView.scrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY, reactScrollViewCommandHelper$ScrollToCommandData.mAnimated);
    }
    
    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(final RecyclerViewBackedScrollView recyclerViewBackedScrollView, final boolean sendContentSizeChangeEvents) {
        recyclerViewBackedScrollView.setSendContentSizeChangeEvents(sendContentSizeChangeEvents);
    }
}
