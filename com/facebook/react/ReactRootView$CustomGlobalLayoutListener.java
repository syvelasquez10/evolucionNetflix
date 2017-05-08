// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.UiThreadUtil;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.common.logging.FLog;
import android.view.MotionEvent;
import com.facebook.infer.annotation.Assertions;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Bundle;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import android.view.WindowManager;
import com.facebook.react.uimanager.PixelUtil;
import android.graphics.Rect;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class ReactRootView$CustomGlobalLayoutListener implements ViewTreeObserver$OnGlobalLayoutListener
{
    private int mDeviceRotation;
    private int mKeyboardHeight;
    private final int mMinKeyboardHeightDetected;
    private final Rect mVisibleViewArea;
    final /* synthetic */ ReactRootView this$0;
    
    ReactRootView$CustomGlobalLayoutListener(final ReactRootView this$0) {
        this.this$0 = this$0;
        this.mKeyboardHeight = 0;
        this.mDeviceRotation = 0;
        this.mVisibleViewArea = new Rect();
        this.mMinKeyboardHeightDetected = (int)PixelUtil.toPixelFromDIP(60.0f);
    }
    
    private void checkForDeviceOrientationChanges() {
        final int rotation = ((WindowManager)this.this$0.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.mDeviceRotation == rotation) {
            return;
        }
        this.mDeviceRotation = rotation;
        DisplayMetricsHolder.initDisplayMetrics(this.this$0.getContext());
        this.emitUpdateDimensionsEvent();
        this.emitOrientationChanged(rotation);
    }
    
    private void checkForKeyboardEvents() {
        this.this$0.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
        final int mKeyboardHeight = DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom;
        if (this.mKeyboardHeight != mKeyboardHeight && mKeyboardHeight > this.mMinKeyboardHeightDetected) {
            this.mKeyboardHeight = mKeyboardHeight;
            final WritableMap map = Arguments.createMap();
            final WritableMap map2 = Arguments.createMap();
            map2.putDouble("screenY", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.bottom));
            map2.putDouble("screenX", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left));
            map2.putDouble("width", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()));
            map2.putDouble("height", PixelUtil.toDIPFromPixel(this.mKeyboardHeight));
            map.putMap("endCoordinates", map2);
            this.sendEvent("keyboardDidShow", map);
        }
        else if (this.mKeyboardHeight != 0 && mKeyboardHeight <= this.mMinKeyboardHeightDetected) {
            this.mKeyboardHeight = 0;
            this.sendEvent("keyboardDidHide", null);
        }
    }
    
    private void emitOrientationChanged(final int n) {
        boolean b = false;
        String s = null;
        double n2 = 0.0;
        switch (n) {
            default: {
                return;
            }
            case 0: {
                s = "portrait-primary";
                n2 = 0.0;
                break;
            }
            case 1: {
                n2 = -90.0;
                s = "landscape-primary";
                b = true;
                break;
            }
            case 2: {
                s = "portrait-secondary";
                n2 = 180.0;
                break;
            }
            case 3: {
                n2 = 90.0;
                s = "landscape-secondary";
                b = true;
                break;
            }
        }
        final WritableMap map = Arguments.createMap();
        map.putString("name", s);
        map.putDouble("rotationDegrees", n2);
        map.putBoolean("isLandscape", b);
        this.sendEvent("namedOrientationDidChange", map);
    }
    
    private void emitUpdateDimensionsEvent() {
        final DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        final DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
        final WritableMap map = Arguments.createMap();
        map.putInt("width", windowDisplayMetrics.widthPixels);
        map.putInt("height", windowDisplayMetrics.heightPixels);
        map.putDouble("scale", windowDisplayMetrics.density);
        map.putDouble("fontScale", windowDisplayMetrics.scaledDensity);
        map.putDouble("densityDpi", windowDisplayMetrics.densityDpi);
        final WritableMap map2 = Arguments.createMap();
        map2.putInt("width", screenDisplayMetrics.widthPixels);
        map2.putInt("height", screenDisplayMetrics.heightPixels);
        map2.putDouble("scale", screenDisplayMetrics.density);
        map2.putDouble("fontScale", screenDisplayMetrics.scaledDensity);
        map2.putDouble("densityDpi", screenDisplayMetrics.densityDpi);
        final WritableMap map3 = Arguments.createMap();
        map3.putMap("windowPhysicalPixels", map);
        map3.putMap("screenPhysicalPixels", map2);
        this.sendEvent("didUpdateDimensions", map3);
    }
    
    private void sendEvent(final String s, final WritableMap writableMap) {
        if (this.this$0.mReactInstanceManager != null) {
            this.this$0.mReactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit(s, writableMap);
        }
    }
    
    public void onGlobalLayout() {
        if (this.this$0.mReactInstanceManager == null || !this.this$0.mIsAttachedToInstance || this.this$0.mReactInstanceManager.getCurrentReactContext() == null) {
            return;
        }
        this.checkForKeyboardEvents();
        this.checkForDeviceOrientationChanges();
    }
}
