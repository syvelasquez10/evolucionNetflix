// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import android.content.Context;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.content.DialogInterface$OnShowListener;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView>
{
    protected static final String REACT_CLASS = "RCTModalHostView";
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactModalHostView reactModalHostView) {
        final EventDispatcher eventDispatcher = themedReactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        reactModalHostView.setOnRequestCloseListener(new ReactModalHostManager$1(this, eventDispatcher, reactModalHostView));
        reactModalHostView.setOnShowListener((DialogInterface$OnShowListener)new ReactModalHostManager$2(this, eventDispatcher, reactModalHostView));
    }
    
    @Override
    public LayoutShadowNode createShadowNodeInstance() {
        return new ModalHostShadowNode();
    }
    
    @Override
    protected ReactModalHostView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactModalHostView((Context)themedReactContext);
    }
    
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return (Map<String, Object>)MapBuilder.builder().put("topRequestClose", MapBuilder.of("registrationName", "onRequestClose")).put("topShow", MapBuilder.of("registrationName", "onShow")).build();
    }
    
    @Override
    public String getName() {
        return "RCTModalHostView";
    }
    
    @Override
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ModalHostShadowNode.class;
    }
    
    protected void onAfterUpdateTransaction(final ReactModalHostView reactModalHostView) {
        super.onAfterUpdateTransaction((T)reactModalHostView);
        reactModalHostView.showOrUpdate();
    }
    
    public void onDropViewInstance(final ReactModalHostView reactModalHostView) {
        super.onDropViewInstance((T)reactModalHostView);
        reactModalHostView.onDropInstance();
    }
    
    @ReactProp(name = "animationType")
    public void setAnimationType(final ReactModalHostView reactModalHostView, final String animationType) {
        reactModalHostView.setAnimationType(animationType);
    }
    
    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(final ReactModalHostView reactModalHostView, final boolean hardwareAccelerated) {
        reactModalHostView.setHardwareAccelerated(hardwareAccelerated);
    }
    
    @ReactProp(name = "transparent")
    public void setTransparent(final ReactModalHostView reactModalHostView, final boolean transparent) {
        reactModalHostView.setTransparent(transparent);
    }
}
