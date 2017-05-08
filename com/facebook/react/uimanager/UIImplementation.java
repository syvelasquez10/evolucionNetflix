// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.animation.Animation;
import java.util.Comparator;
import java.util.Arrays;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.yoga.YogaDirection;
import android.content.Context;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.Callback;
import com.facebook.infer.annotation.Assertions;
import java.util.List;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.EventDispatcher;

public class UIImplementation
{
    protected final EventDispatcher mEventDispatcher;
    private double mLayoutCount;
    private double mLayoutTimer;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    private final ReactApplicationContext mReactContext;
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;
    
    protected UIImplementation(final ReactApplicationContext mReactContext, final ViewManagerRegistry mViewManagers, final UIViewOperationQueue mOperationsQueue, final EventDispatcher mEventDispatcher) {
        this.mShadowNodeRegistry = new ShadowNodeRegistry();
        this.mMeasureBuffer = new int[4];
        this.mLayoutCount = 0.0;
        this.mLayoutTimer = 0.0;
        this.mReactContext = mReactContext;
        this.mViewManagers = mViewManagers;
        this.mOperationsQueue = mOperationsQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(this.mOperationsQueue, this.mShadowNodeRegistry);
        this.mEventDispatcher = mEventDispatcher;
    }
    
    private UIImplementation(final ReactApplicationContext reactApplicationContext, final ViewManagerRegistry viewManagerRegistry, final EventDispatcher eventDispatcher) {
        this(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry)), eventDispatcher);
    }
    
    public UIImplementation(final ReactApplicationContext reactApplicationContext, final List<ViewManager> list, final EventDispatcher eventDispatcher) {
        this(reactApplicationContext, new ViewManagerRegistry(list), eventDispatcher);
    }
    
    private void assertNodeDoesNotNeedCustomLayoutForChildren(final ReactShadowNode reactShadowNode) {
        final ViewManager viewManager = Assertions.assertNotNull(this.mViewManagers.get(reactShadowNode.getViewClass()));
        if (!(viewManager instanceof ViewGroupManager)) {
            throw new IllegalViewOperationException("Trying to use view " + reactShadowNode.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
        }
        final ViewGroupManager viewGroupManager = (ViewGroupManager)viewManager;
        if (viewGroupManager != null && viewGroupManager.needsCustomLayoutForChildren()) {
            throw new IllegalViewOperationException("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + reactShadowNode.getViewClass() + "). Use measure instead.");
        }
    }
    
    private void assertViewExists(final int n, final String s) {
        if (this.mShadowNodeRegistry.getNode(n) == null) {
            throw new IllegalViewOperationException("Unable to execute operation " + s + " on view with " + "tag: " + n + ", since the view does not exists");
        }
    }
    
    private void measureLayout(int n, final int n2, final int[] array) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        final ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(n2);
        if (node == null || node2 == null) {
            final StringBuilder append = new StringBuilder().append("Tag ");
            if (node != null) {
                n = n2;
            }
            throw new IllegalViewOperationException(append.append(n).append(" does not exist").toString());
        }
        if (node != node2) {
            for (ReactShadowNode reactShadowNode = node.getParent(); reactShadowNode != node2; reactShadowNode = reactShadowNode.getParent()) {
                if (reactShadowNode == null) {
                    throw new IllegalViewOperationException("Tag " + n2 + " is not an ancestor of tag " + n);
                }
            }
        }
        this.measureLayoutRelativeToVerifiedAncestor(node, node2, array);
    }
    
    private void measureLayoutRelativeToParent(final int n, final int[] array) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        if (node == null) {
            throw new IllegalViewOperationException("No native view for tag " + n + " exists!");
        }
        final ReactShadowNode parent = node.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("View with tag " + n + " doesn't have a parent!");
        }
        this.measureLayoutRelativeToVerifiedAncestor(node, parent, array);
    }
    
    private void measureLayoutRelativeToVerifiedAncestor(final ReactShadowNode reactShadowNode, final ReactShadowNode reactShadowNode2, final int[] array) {
        int round;
        int round2;
        if (reactShadowNode != reactShadowNode2) {
            round = Math.round(reactShadowNode.getLayoutX());
            round2 = Math.round(reactShadowNode.getLayoutY());
            int round3;
            int round4;
            for (ReactShadowNode reactShadowNode3 = reactShadowNode.getParent(); reactShadowNode3 != reactShadowNode2; reactShadowNode3 = reactShadowNode3.getParent(), round2 += round4, round += round3) {
                Assertions.assertNotNull(reactShadowNode3);
                this.assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode3);
                round3 = Math.round(reactShadowNode3.getLayoutX());
                round4 = Math.round(reactShadowNode3.getLayoutY());
            }
            this.assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        }
        else {
            round2 = 0;
            round = 0;
        }
        array[0] = round;
        array[1] = round2;
        array[2] = reactShadowNode.getScreenWidth();
        array[3] = reactShadowNode.getScreenHeight();
    }
    
    private void notifyOnBeforeLayoutRecursive(final ReactShadowNode reactShadowNode) {
        if (!reactShadowNode.hasUpdates()) {
            return;
        }
        for (int i = 0; i < reactShadowNode.getChildCount(); ++i) {
            this.notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(i));
        }
        reactShadowNode.onBeforeLayout();
    }
    
    private void removeShadowNodeRecursive(final ReactShadowNode reactShadowNode) {
        NativeViewHierarchyOptimizer.handleRemoveNode(reactShadowNode);
        this.mShadowNodeRegistry.removeNode(reactShadowNode.getReactTag());
        for (int i = reactShadowNode.getChildCount() - 1; i >= 0; --i) {
            this.removeShadowNodeRecursive(reactShadowNode.getChildAt(i));
        }
        reactShadowNode.removeAndDisposeAllChildren();
    }
    
    public void addAnimation(final int n, final int n2, final Callback callback) {
        this.assertViewExists(n, "addAnimation");
        this.mOperationsQueue.enqueueAddAnimation(n, n2, callback);
    }
    
    public void addUIBlock(final UIBlock uiBlock) {
        this.mOperationsQueue.enqueueUIBlock(uiBlock);
    }
    
    protected void applyUpdatesRecursive(final ReactShadowNode reactShadowNode, final float n, final float n2) {
        if (!reactShadowNode.hasUpdates()) {
            return;
        }
        if (!reactShadowNode.isVirtualAnchor()) {
            for (int i = 0; i < reactShadowNode.getChildCount(); ++i) {
                this.applyUpdatesRecursive(reactShadowNode.getChildAt(i), reactShadowNode.getLayoutX() + n, reactShadowNode.getLayoutY() + n2);
            }
        }
        final int reactTag = reactShadowNode.getReactTag();
        if (!this.mShadowNodeRegistry.isRootNode(reactTag) && reactShadowNode.dispatchUpdates(n, n2, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && reactShadowNode.shouldNotifyOnLayout()) {
            this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(reactTag, reactShadowNode.getScreenX(), reactShadowNode.getScreenY(), reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight()));
        }
        reactShadowNode.markUpdateSeen();
    }
    
    protected void calculateRootLayout(final ReactShadowNode reactShadowNode) {
        SystraceMessage.beginSection(0L, "cssRoot.calculateLayout").arg("rootTag", reactShadowNode.getReactTag()).flush();
        final double n = System.nanoTime();
        try {
            reactShadowNode.calculateLayout();
        }
        finally {
            Systrace.endSection(0L);
            this.mLayoutTimer += (System.nanoTime() - n) / 1.0E9;
            ++this.mLayoutCount;
        }
    }
    
    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }
    
    public void configureNextLayoutAnimation(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(readableMap, callback, callback2);
    }
    
    protected ReactShadowNode createRootShadowNode() {
        final ReactShadowNode reactShadowNode = new ReactShadowNode();
        if (I18nUtil.getInstance().isRTL((Context)this.mReactContext)) {
            reactShadowNode.setLayoutDirection(YogaDirection.RTL);
        }
        reactShadowNode.setViewClassName("Root");
        return reactShadowNode;
    }
    
    protected ReactShadowNode createShadowNode(final String s) {
        return this.mViewManagers.get(s).createShadowNodeInstance();
    }
    
    public void createView(final int reactTag, final String viewClassName, final int n, final ReadableMap readableMap) {
        final ReactShadowNode shadowNode = this.createShadowNode(viewClassName);
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        shadowNode.setReactTag(reactTag);
        shadowNode.setViewClassName(viewClassName);
        shadowNode.setRootNode(node);
        shadowNode.setThemedContext(node.getThemedContext());
        this.mShadowNodeRegistry.addNode(shadowNode);
        ReactStylesDiffMap reactStylesDiffMap = null;
        if (readableMap != null) {
            reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
            shadowNode.updateProperties(reactStylesDiffMap);
        }
        this.handleCreateView(shadowNode, n, reactStylesDiffMap);
    }
    
    public void dispatchViewManagerCommand(final int n, final int n2, final ReadableArray readableArray) {
        this.assertViewExists(n, "dispatchViewManagerCommand");
        this.mOperationsQueue.enqueueDispatchCommand(n, n2, readableArray);
    }
    
    public void dispatchViewUpdates(final int n) {
        this.updateViewHierarchy();
        this.mNativeViewHierarchyOptimizer.onBatchComplete();
        this.mOperationsQueue.dispatchViewUpdates(n);
    }
    
    public void findSubviewIn(final int n, final float n2, final float n3, final Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(n, n2, n3, callback);
    }
    
    public double getLayoutCount() {
        return this.mLayoutCount;
    }
    
    public double getLayoutTimer() {
        return this.mLayoutTimer;
    }
    
    protected void handleCreateView(final ReactShadowNode reactShadowNode, final int n, final ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleCreateView(reactShadowNode, reactShadowNode.getThemedContext(), reactStylesDiffMap);
        }
    }
    
    protected void handleUpdateView(final ReactShadowNode reactShadowNode, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleUpdateView(reactShadowNode, s, reactStylesDiffMap);
        }
    }
    
    public void manageChildren(int i, final ReadableArray readableArray, final ReadableArray readableArray2, final ReadableArray readableArray3, final ReadableArray readableArray4, final ReadableArray readableArray5) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        int size;
        if (readableArray == null) {
            size = 0;
        }
        else {
            size = readableArray.size();
        }
        int size2;
        if (readableArray3 == null) {
            size2 = 0;
        }
        else {
            size2 = readableArray3.size();
        }
        int size3;
        if (readableArray5 == null) {
            size3 = 0;
        }
        else {
            size3 = readableArray5.size();
        }
        if (size != 0 && (readableArray2 == null || size != readableArray2.size())) {
            throw new IllegalViewOperationException("Size of moveFrom != size of moveTo!");
        }
        if (size2 != 0 && (readableArray4 == null || size2 != readableArray4.size())) {
            throw new IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
        }
        final ViewAtIndex[] array = new ViewAtIndex[size + size2];
        final int[] array2 = new int[size + size3];
        final int[] array3 = new int[array2.length];
        final int[] array4 = new int[size3];
        if (size > 0) {
            Assertions.assertNotNull(readableArray);
            Assertions.assertNotNull(readableArray2);
            for (int j = 0; j < size; ++j) {
                final int int1 = readableArray.getInt(j);
                final int reactTag = node.getChildAt(int1).getReactTag();
                array[j] = new ViewAtIndex(reactTag, readableArray2.getInt(j));
                array2[j] = int1;
                array3[j] = reactTag;
            }
        }
        if (size2 > 0) {
            Assertions.assertNotNull(readableArray3);
            Assertions.assertNotNull(readableArray4);
            for (int k = 0; k < size2; ++k) {
                array[size + k] = new ViewAtIndex(readableArray3.getInt(k), readableArray4.getInt(k));
            }
        }
        if (size3 > 0) {
            Assertions.assertNotNull(readableArray5);
            for (int l = 0; l < size3; ++l) {
                final int int2 = readableArray5.getInt(l);
                final int reactTag2 = node.getChildAt(int2).getReactTag();
                array2[size + l] = int2;
                array4[l] = (array3[size + l] = reactTag2);
            }
        }
        Arrays.sort(array, ViewAtIndex.COMPARATOR);
        Arrays.sort(array2);
        int n = -1;
        for (int n2 = array2.length - 1; n2 >= 0; --n2) {
            if (array2[n2] == n) {
                throw new IllegalViewOperationException("Repeated indices in Removal list for view tag: " + i);
            }
            node.removeChildAt(array2[n2]);
            n = array2[n2];
        }
        ViewAtIndex viewAtIndex;
        ReactShadowNode node2;
        for (i = 0; i < array.length; ++i) {
            viewAtIndex = array[i];
            node2 = this.mShadowNodeRegistry.getNode(viewAtIndex.mTag);
            if (node2 == null) {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag);
            }
            node.addChildAt(node2, viewAtIndex.mIndex);
        }
        if (!node.isVirtual() && !node.isVirtualAnchor()) {
            this.mNativeViewHierarchyOptimizer.handleManageChildren(node, array2, array3, array, array4);
        }
        for (i = 0; i < array4.length; ++i) {
            this.removeShadowNode(this.mShadowNodeRegistry.getNode(array4[i]));
        }
    }
    
    public void measure(final int n, final Callback callback) {
        this.mOperationsQueue.enqueueMeasure(n, callback);
    }
    
    public void measureInWindow(final int n, final Callback callback) {
        this.mOperationsQueue.enqueueMeasureInWindow(n, callback);
    }
    
    public void measureLayout(final int n, final int n2, final Callback callback, final Callback callback2) {
        try {
            this.measureLayout(n, n2, this.mMeasureBuffer);
            callback2.invoke(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3]));
        }
        catch (IllegalViewOperationException ex) {
            callback.invoke(ex.getMessage());
        }
    }
    
    public void measureLayoutRelativeToParent(final int n, final Callback callback, final Callback callback2) {
        try {
            this.measureLayoutRelativeToParent(n, this.mMeasureBuffer);
            callback2.invoke(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2]), PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3]));
        }
        catch (IllegalViewOperationException ex) {
            callback.invoke(ex.getMessage());
        }
    }
    
    public void onHostDestroy() {
    }
    
    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }
    
    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }
    
    public void registerAnimation(final Animation animation) {
        this.mOperationsQueue.enqueueRegisterAnimation(animation);
    }
    
    public void registerRootView(final SizeMonitoringFrameLayout sizeMonitoringFrameLayout, final int reactTag, final int n, final int n2, final ThemedReactContext themedContext) {
        final ReactShadowNode rootShadowNode = this.createRootShadowNode();
        rootShadowNode.setReactTag(reactTag);
        rootShadowNode.setThemedContext(themedContext);
        rootShadowNode.setStyleWidth(n);
        rootShadowNode.setStyleHeight(n2);
        this.mShadowNodeRegistry.addRootNode(rootShadowNode);
        this.mOperationsQueue.addRootView(reactTag, sizeMonitoringFrameLayout, themedContext);
    }
    
    public void removeAnimation(final int n, final int n2) {
        this.assertViewExists(n, "removeAnimation");
        this.mOperationsQueue.enqueueRemoveAnimation(n2);
    }
    
    public void removeRootView(final int n) {
        this.mShadowNodeRegistry.removeRootNode(n);
        this.mOperationsQueue.enqueueRemoveRootView(n);
    }
    
    protected final void removeShadowNode(final ReactShadowNode reactShadowNode) {
        this.removeShadowNodeRecursive(reactShadowNode);
        reactShadowNode.dispose();
    }
    
    public void removeSubviewsFromContainerWithID(final int n) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        if (node == null) {
            throw new IllegalViewOperationException("Trying to remove subviews of an unknown view tag: " + n);
        }
        final WritableArray array = Arguments.createArray();
        for (int i = 0; i < node.getChildCount(); ++i) {
            array.pushInt(i);
        }
        this.manageChildren(n, null, null, null, null, array);
    }
    
    public void replaceExistingNonRootView(int index, final int n) {
        if (this.mShadowNodeRegistry.isRootNode(index) || this.mShadowNodeRegistry.isRootNode(n)) {
            throw new IllegalViewOperationException("Trying to add or replace a root tag!");
        }
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(index);
        if (node == null) {
            throw new IllegalViewOperationException("Trying to replace unknown view tag: " + index);
        }
        final ReactShadowNode parent = node.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("Node is not attached to a parent: " + index);
        }
        index = parent.indexOf(node);
        if (index < 0) {
            throw new IllegalStateException("Didn't find child tag in parent");
        }
        final WritableArray array = Arguments.createArray();
        array.pushInt(n);
        final WritableArray array2 = Arguments.createArray();
        array2.pushInt(index);
        final WritableArray array3 = Arguments.createArray();
        array3.pushInt(index);
        this.manageChildren(parent.getReactTag(), null, null, array, array2, array3);
    }
    
    public int resolveRootTagFromReactTag(int reactTag) {
        if (this.mShadowNodeRegistry.isRootNode(reactTag)) {
            return reactTag;
        }
        final ReactShadowNode resolveShadowNode = this.resolveShadowNode(reactTag);
        final int n = 0;
        if (resolveShadowNode != null) {
            reactTag = resolveShadowNode.getRootNode().getReactTag();
        }
        else {
            FLog.w("React", "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + reactTag);
            reactTag = n;
        }
        return reactTag;
    }
    
    protected final ReactShadowNode resolveShadowNode(final int n) {
        return this.mShadowNodeRegistry.getNode(n);
    }
    
    public void sendAccessibilityEvent(final int n, final int n2) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(n, n2);
    }
    
    public void setChildren(int i, final ReadableArray readableArray) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        ReactShadowNode node2;
        for (i = 0; i < readableArray.size(); ++i) {
            node2 = this.mShadowNodeRegistry.getNode(readableArray.getInt(i));
            if (node2 == null) {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i));
            }
            node.addChildAt(node2, i);
        }
        if (!node.isVirtual() && !node.isVirtualAnchor()) {
            this.mNativeViewHierarchyOptimizer.handleSetChildren(node, readableArray);
        }
    }
    
    public void setJSResponder(final int n, final boolean b) {
        this.assertViewExists(n, "setJSResponder");
        ReactShadowNode reactShadowNode;
        for (reactShadowNode = this.mShadowNodeRegistry.getNode(n); reactShadowNode.isVirtual() || reactShadowNode.isLayoutOnly(); reactShadowNode = reactShadowNode.getParent()) {}
        this.mOperationsQueue.enqueueSetJSResponder(reactShadowNode.getReactTag(), n, b);
    }
    
    public void setLayoutAnimationEnabledExperimental(final boolean b) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(b);
    }
    
    public void setViewHierarchyUpdateDebugListener(final NotThreadSafeViewHierarchyUpdateDebugListener viewHierarchyUpdateDebugListener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(viewHierarchyUpdateDebugListener);
    }
    
    public void showPopupMenu(final int n, final ReadableArray readableArray, final Callback callback, final Callback callback2) {
        this.assertViewExists(n, "showPopupMenu");
        this.mOperationsQueue.enqueueShowPopupMenu(n, readableArray, callback, callback2);
    }
    
    public void synchronouslyUpdateViewOnUIThread(final int n, final ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(n, reactStylesDiffMap);
    }
    
    public void updateNodeSize(final int n, final int n2, final int n3) {
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        node.setStyleWidth(n2);
        node.setStyleHeight(n3);
        if (this.mOperationsQueue.isEmpty()) {
            this.dispatchViewUpdates(-1);
        }
    }
    
    public void updateView(final int n, final String s, final ReadableMap readableMap) {
        if (this.mViewManagers.get(s) == null) {
            throw new IllegalViewOperationException("Got unknown view type: " + s);
        }
        final ReactShadowNode node = this.mShadowNodeRegistry.getNode(n);
        if (node == null) {
            throw new IllegalViewOperationException("Trying to update non-existent view with tag " + n);
        }
        if (readableMap != null) {
            final ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
            node.updateProperties(reactStylesDiffMap);
            this.handleUpdateView(node, s, reactStylesDiffMap);
        }
    }
    
    protected void updateViewHierarchy() {
        for (int i = 0; i < this.mShadowNodeRegistry.getRootNodeCount(); ++i) {
            final ReactShadowNode node = this.mShadowNodeRegistry.getNode(this.mShadowNodeRegistry.getRootTag(i));
            this.notifyOnBeforeLayoutRecursive(node);
            this.calculateRootLayout(node);
            this.applyUpdatesRecursive(node, 0.0f, 0.0f);
        }
    }
}
