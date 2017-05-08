// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer;

import com.facebook.common.logging.FLog;
import android.os.Build$VERSION;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout$DrawerListener;
import android.support.v4.widget.DrawerLayout;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout>
{
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    protected static final String REACT_CLASS = "AndroidDrawerLayout";
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final ReactDrawerLayout reactDrawerLayout) {
        reactDrawerLayout.setDrawerListener(new ReactDrawerLayoutManager$DrawerEventEmitter(reactDrawerLayout, themedReactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()));
    }
    
    @Override
    public void addView(final ReactDrawerLayout reactDrawerLayout, final View view, final int n) {
        if (this.getChildCount(reactDrawerLayout) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        }
        if (n != 0 && n != 1) {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + n + " instead.");
        }
        reactDrawerLayout.addView(view, n);
        reactDrawerLayout.setDrawerProperties();
    }
    
    @Override
    protected ReactDrawerLayout createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactDrawerLayout(themedReactContext);
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("openDrawer", 1, "closeDrawer", 2);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void getDrawerWidth(final ReactDrawerLayout reactDrawerLayout, final float n) {
        int round;
        if (Float.isNaN(n)) {
            round = -1;
        }
        else {
            round = Math.round(PixelUtil.toPixelFromDIP(n));
        }
        reactDrawerLayout.setDrawerWidth(round);
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topDrawerSlide", MapBuilder.of("registrationName", "onDrawerSlide"), "topDrawerOpened", MapBuilder.of("registrationName", "onDrawerOpen"), "topDrawerClosed", MapBuilder.of("registrationName", "onDrawerClose"), "topDrawerStateChanged", MapBuilder.of("registrationName", "onDrawerStateChanged"));
    }
    
    @Override
    public Map getExportedViewConstants() {
        return MapBuilder.of("DrawerPosition", MapBuilder.of("Left", 8388611, "Right", 8388613));
    }
    
    @Override
    public String getName() {
        return "AndroidDrawerLayout";
    }
    
    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }
    
    public void receiveCommand(final ReactDrawerLayout reactDrawerLayout, final int n, final ReadableArray readableArray) {
        switch (n) {
            default: {}
            case 1: {
                reactDrawerLayout.openDrawer();
            }
            case 2: {
                reactDrawerLayout.closeDrawer();
            }
        }
    }
    
    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(final ReactDrawerLayout reactDrawerLayout, final String s) {
        if (s == null || "unlocked".equals(s)) {
            reactDrawerLayout.setDrawerLockMode(0);
            return;
        }
        if ("locked-closed".equals(s)) {
            reactDrawerLayout.setDrawerLockMode(1);
            return;
        }
        if ("locked-open".equals(s)) {
            reactDrawerLayout.setDrawerLockMode(2);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Unknown drawerLockMode " + s);
    }
    
    @ReactProp(defaultInt = 8388611, name = "drawerPosition")
    public void setDrawerPosition(final ReactDrawerLayout reactDrawerLayout, final int drawerPosition) {
        if (8388611 == drawerPosition || 8388613 == drawerPosition) {
            reactDrawerLayout.setDrawerPosition(drawerPosition);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Unknown drawerPosition " + drawerPosition);
    }
    
    public void setElevation(final ReactDrawerLayout reactDrawerLayout, final float n) {
        if (Build$VERSION.SDK_INT < 21) {
            return;
        }
        try {
            ReactDrawerLayout.class.getMethod("setDrawerElevation", Float.TYPE).invoke(reactDrawerLayout, PixelUtil.toPixelFromDIP(n));
        }
        catch (Exception ex) {
            FLog.w("React", "setDrawerElevation is not available in this version of the support lib.", ex);
        }
    }
}
