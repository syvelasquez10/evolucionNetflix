// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableArray;
import java.util.Map;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.bridge.BaseJavaModule;
import android.view.View;

public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule
{
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final T t) {
    }
    
    public abstract C createShadowNodeInstance();
    
    public final T createView(final ThemedReactContext themedReactContext, final JSResponderHandler onInterceptTouchEventListener) {
        final ReactInterceptingViewGroup viewInstance = this.createViewInstance(themedReactContext);
        this.addEventEmitters(themedReactContext, (T)viewInstance);
        if (viewInstance instanceof ReactInterceptingViewGroup) {
            viewInstance.setOnInterceptTouchEventListener(onInterceptTouchEventListener);
        }
        return (T)viewInstance;
    }
    
    protected abstract T createViewInstance(final ThemedReactContext p0);
    
    public Map<String, Integer> getCommandsMap() {
        return null;
    }
    
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }
    
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }
    
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }
    
    @Override
    public abstract String getName();
    
    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(this.getClass(), this.getShadowNodeClass());
    }
    
    public abstract Class<? extends C> getShadowNodeClass();
    
    protected void onAfterUpdateTransaction(final T t) {
    }
    
    public void onDropViewInstance(final T t) {
    }
    
    public void receiveCommand(final T t, final int n, final ReadableArray readableArray) {
    }
    
    public abstract void updateExtraData(final T p0, final Object p1);
    
    public final void updateProperties(final T t, final ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerPropertyUpdater.updateProps(this, t, reactStylesDiffMap);
        this.onAfterUpdateTransaction(t);
    }
}
