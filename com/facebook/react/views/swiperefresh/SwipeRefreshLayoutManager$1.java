// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener;

class SwipeRefreshLayoutManager$1 implements SwipeRefreshLayout$OnRefreshListener
{
    final /* synthetic */ SwipeRefreshLayoutManager this$0;
    final /* synthetic */ ThemedReactContext val$reactContext;
    final /* synthetic */ ReactSwipeRefreshLayout val$view;
    
    SwipeRefreshLayoutManager$1(final SwipeRefreshLayoutManager this$0, final ThemedReactContext val$reactContext, final ReactSwipeRefreshLayout val$view) {
        this.this$0 = this$0;
        this.val$reactContext = val$reactContext;
        this.val$view = val$view;
    }
    
    @Override
    public void onRefresh() {
        this.val$reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new RefreshEvent(this.val$view.getId()));
    }
}
