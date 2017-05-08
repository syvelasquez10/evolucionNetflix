// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.animation.Animation;
import com.facebook.systrace.SystraceMessage;
import android.content.ComponentCallbacks;
import java.util.HashMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.ReactMarker;
import android.content.Context;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Map;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class UIManagerModule extends ReactContextBaseJavaModule implements LifecycleEventListener, OnBatchCompleteListener
{
    private static final boolean DEBUG = false;
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private int mBatchId;
    private final EventDispatcher mEventDispatcher;
    private final UIManagerModule$MemoryTrimCallback mMemoryTrimCallback;
    private final Map<String, Object> mModuleConstants;
    private int mNextRootViewTag;
    private final UIImplementation mUIImplementation;
    
    public UIManagerModule(final ReactApplicationContext reactApplicationContext, final List<ViewManager> list, final UIImplementationProvider uiImplementationProvider) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new UIManagerModule$MemoryTrimCallback(this, null);
        this.mNextRootViewTag = 1;
        this.mBatchId = 0;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized((Context)reactApplicationContext);
        this.mEventDispatcher = new EventDispatcher(reactApplicationContext);
        this.mModuleConstants = createConstants(list);
        this.mUIImplementation = uiImplementationProvider.createUIImplementation(reactApplicationContext, list, this.mEventDispatcher);
        reactApplicationContext.addLifecycleEventListener(this);
    }
    
    private static Map<String, Object> createConstants(final List<ViewManager> list) {
        ReactMarker.logMarker("CREATE_UI_MANAGER_MODULE_CONSTANTS_START");
        Systrace.beginSection(0L, "CreateUIManagerConstants");
        try {
            return UIManagerModuleConstantsHelper.createConstants(list);
        }
        finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker("CREATE_UI_MANAGER_MODULE_CONSTANTS_END");
        }
    }
    
    public void addAnimation(final int n, final int n2, final Callback callback) {
        this.mUIImplementation.addAnimation(n, n2, callback);
    }
    
    public int addMeasuredRootView(final SizeMonitoringFrameLayout sizeMonitoringFrameLayout) {
        final int mNextRootViewTag = this.mNextRootViewTag;
        this.mNextRootViewTag += 10;
        int n;
        int n2;
        if (sizeMonitoringFrameLayout.getLayoutParams() != null && sizeMonitoringFrameLayout.getLayoutParams().width > 0 && sizeMonitoringFrameLayout.getLayoutParams().height > 0) {
            n = sizeMonitoringFrameLayout.getLayoutParams().width;
            n2 = sizeMonitoringFrameLayout.getLayoutParams().height;
        }
        else {
            n = sizeMonitoringFrameLayout.getWidth();
            n2 = sizeMonitoringFrameLayout.getHeight();
        }
        this.mUIImplementation.registerRootView(sizeMonitoringFrameLayout, mNextRootViewTag, n, n2, new ThemedReactContext(this.getReactApplicationContext(), sizeMonitoringFrameLayout.getContext()));
        sizeMonitoringFrameLayout.setOnSizeChangedListener(new UIManagerModule$1(this, mNextRootViewTag));
        return mNextRootViewTag;
    }
    
    public void addUIBlock(final UIBlock uiBlock) {
        this.mUIImplementation.addUIBlock(uiBlock);
    }
    
    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.clearJSResponder();
    }
    
    @ReactMethod
    public void configureNextLayoutAnimation(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        this.mUIImplementation.configureNextLayoutAnimation(readableMap, callback, callback2);
    }
    
    @ReactMethod
    public void createView(final int n, final String s, final int n2, final ReadableMap readableMap) {
        this.mUIImplementation.createView(n, s, n2, readableMap);
    }
    
    @ReactMethod
    public void dispatchViewManagerCommand(final int n, final int n2, final ReadableArray readableArray) {
        this.mUIImplementation.dispatchViewManagerCommand(n, n2, readableArray);
    }
    
    @ReactMethod
    public void findSubviewIn(final int n, final ReadableArray readableArray, final Callback callback) {
        this.mUIImplementation.findSubviewIn(n, Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), callback);
    }
    
    @Override
    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }
    
    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }
    
    @Override
    public String getName() {
        return "RKUIManager";
    }
    
    public Map<String, Double> getPerformanceCounters() {
        final HashMap<String, Double> hashMap = new HashMap<String, Double>();
        hashMap.put("LayoutCount", this.mUIImplementation.getLayoutCount());
        hashMap.put("LayoutTimer", this.mUIImplementation.getLayoutTimer());
        return hashMap;
    }
    
    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }
    
    @Override
    public void initialize() {
        this.getReactApplicationContext().registerComponentCallbacks((ComponentCallbacks)this.mMemoryTrimCallback);
    }
    
    @ReactMethod
    public void manageChildren(final int n, final ReadableArray readableArray, final ReadableArray readableArray2, final ReadableArray readableArray3, final ReadableArray readableArray4, final ReadableArray readableArray5) {
        this.mUIImplementation.manageChildren(n, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }
    
    @ReactMethod
    public void measure(final int n, final Callback callback) {
        this.mUIImplementation.measure(n, callback);
    }
    
    @ReactMethod
    public void measureInWindow(final int n, final Callback callback) {
        this.mUIImplementation.measureInWindow(n, callback);
    }
    
    @ReactMethod
    public void measureLayout(final int n, final int n2, final Callback callback, final Callback callback2) {
        this.mUIImplementation.measureLayout(n, n2, callback, callback2);
    }
    
    @ReactMethod
    public void measureLayoutRelativeToParent(final int n, final Callback callback, final Callback callback2) {
        this.mUIImplementation.measureLayoutRelativeToParent(n, callback, callback2);
    }
    
    @Override
    public void onBatchComplete() {
        final int mBatchId = this.mBatchId;
        ++this.mBatchId;
        SystraceMessage.beginSection(0L, "onBatchCompleteUI").arg("BatchId", mBatchId).flush();
        try {
            this.mUIImplementation.dispatchViewUpdates(mBatchId);
        }
        finally {
            Systrace.endSection(0L);
        }
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.onCatalystInstanceDestroyed();
        this.getReactApplicationContext().unregisterComponentCallbacks((ComponentCallbacks)this.mMemoryTrimCallback);
        YogaNodePool.get().clear();
    }
    
    public void onHostDestroy() {
        this.mUIImplementation.onHostDestroy();
    }
    
    @Override
    public void onHostPause() {
        this.mUIImplementation.onHostPause();
    }
    
    @Override
    public void onHostResume() {
        this.mUIImplementation.onHostResume();
    }
    
    public void registerAnimation(final Animation animation) {
        this.mUIImplementation.registerAnimation(animation);
    }
    
    public void removeAnimation(final int n, final int n2) {
        this.mUIImplementation.removeAnimation(n, n2);
    }
    
    @ReactMethod
    public void removeRootView(final int n) {
        this.mUIImplementation.removeRootView(n);
    }
    
    @ReactMethod
    public void removeSubviewsFromContainerWithID(final int n) {
        this.mUIImplementation.removeSubviewsFromContainerWithID(n);
    }
    
    @ReactMethod
    public void replaceExistingNonRootView(final int n, final int n2) {
        this.mUIImplementation.replaceExistingNonRootView(n, n2);
    }
    
    public int resolveRootTagFromReactTag(final int n) {
        return this.mUIImplementation.resolveRootTagFromReactTag(n);
    }
    
    @ReactMethod
    public void sendAccessibilityEvent(final int n, final int n2) {
        this.mUIImplementation.sendAccessibilityEvent(n, n2);
    }
    
    @ReactMethod
    public void setChildren(final int n, final ReadableArray readableArray) {
        this.mUIImplementation.setChildren(n, readableArray);
    }
    
    @ReactMethod
    public void setJSResponder(final int n, final boolean b) {
        this.mUIImplementation.setJSResponder(n, b);
    }
    
    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(final boolean layoutAnimationEnabledExperimental) {
        this.mUIImplementation.setLayoutAnimationEnabledExperimental(layoutAnimationEnabledExperimental);
    }
    
    public void setViewHierarchyUpdateDebugListener(final NotThreadSafeViewHierarchyUpdateDebugListener viewHierarchyUpdateDebugListener) {
        this.mUIImplementation.setViewHierarchyUpdateDebugListener(viewHierarchyUpdateDebugListener);
    }
    
    @ReactMethod
    public void showPopupMenu(final int n, final ReadableArray readableArray, final Callback callback, final Callback callback2) {
        this.mUIImplementation.showPopupMenu(n, readableArray, callback, callback2);
    }
    
    public void updateNodeSize(final int n, final int n2, final int n3) {
        this.getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.updateNodeSize(n, n2, n3);
    }
    
    @ReactMethod
    public void updateView(final int n, final String s, final ReadableMap readableMap) {
        this.mUIImplementation.updateView(n, s, readableMap);
    }
}
