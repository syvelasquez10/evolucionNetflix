// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import java.util.Collection;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import android.view.Choreographer$FrameCallback;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ReactChoreographer;
import java.util.ArrayList;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, OnBatchCompleteListener
{
    private GuardedChoreographerFrameCallback mAnimatedFrameCallback;
    private ArrayList<NativeAnimatedModule$UIThreadOperation> mOperations;
    private final Object mOperationsCopyLock;
    private ReactChoreographer mReactChoreographer;
    private volatile ArrayList<NativeAnimatedModule$UIThreadOperation> mReadyOperations;
    
    public NativeAnimatedModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mOperationsCopyLock = new Object();
        this.mOperations = new ArrayList<NativeAnimatedModule$UIThreadOperation>();
        this.mReadyOperations = null;
    }
    
    private void clearFrameCallback() {
        Assertions.assertNotNull(this.mReactChoreographer).removeFrameCallback(ReactChoreographer$CallbackType.NATIVE_ANIMATED_MODULE, (Choreographer$FrameCallback)this.mAnimatedFrameCallback);
    }
    
    private void enqueueFrameCallback() {
        Assertions.assertNotNull(this.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.NATIVE_ANIMATED_MODULE, (Choreographer$FrameCallback)this.mAnimatedFrameCallback);
    }
    
    @ReactMethod
    public void addAnimatedEventToView(final int n, final String s, final ReadableMap readableMap) {
        this.mOperations.add(new NativeAnimatedModule$17(this, n, s, readableMap));
    }
    
    @ReactMethod
    public void connectAnimatedNodeToView(final int n, final int n2) {
        this.mOperations.add(new NativeAnimatedModule$15(this, n, n2));
    }
    
    @ReactMethod
    public void connectAnimatedNodes(final int n, final int n2) {
        this.mOperations.add(new NativeAnimatedModule$13(this, n, n2));
    }
    
    @ReactMethod
    public void createAnimatedNode(final int n, final ReadableMap readableMap) {
        this.mOperations.add(new NativeAnimatedModule$2(this, n, readableMap));
    }
    
    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int n, final int n2) {
        this.mOperations.add(new NativeAnimatedModule$16(this, n, n2));
    }
    
    @ReactMethod
    public void disconnectAnimatedNodes(final int n, final int n2) {
        this.mOperations.add(new NativeAnimatedModule$14(this, n, n2));
    }
    
    @ReactMethod
    public void dropAnimatedNode(final int n) {
        this.mOperations.add(new NativeAnimatedModule$6(this, n));
    }
    
    @ReactMethod
    public void extractAnimatedNodeOffset(final int n) {
        this.mOperations.add(new NativeAnimatedModule$10(this, n));
    }
    
    @ReactMethod
    public void flattenAnimatedNodeOffset(final int n) {
        this.mOperations.add(new NativeAnimatedModule$9(this, n));
    }
    
    @Override
    public String getName() {
        return "NativeAnimatedModule";
    }
    
    @Override
    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        final ReactApplicationContext reactApplicationContext = this.getReactApplicationContext();
        this.mAnimatedFrameCallback = new NativeAnimatedModule$1(this, reactApplicationContext, new NativeAnimatedNodesManager(reactApplicationContext.getNativeModule(UIManagerModule.class)));
        reactApplicationContext.addLifecycleEventListener(this);
    }
    
    @Override
    public void onBatchComplete() {
        Label_0049: {
            if (!this.mOperations.isEmpty()) {
                break Label_0049;
            }
            final ArrayList<NativeAnimatedModule$UIThreadOperation> mReadyOperations = null;
            while (true) {
                if (mReadyOperations == null) {
                    return;
                }
                this.mOperations = new ArrayList<NativeAnimatedModule$UIThreadOperation>();
                synchronized (this.mOperationsCopyLock) {
                    if (this.mReadyOperations == null) {
                        this.mReadyOperations = mReadyOperations;
                    }
                    else {
                        this.mReadyOperations.addAll(mReadyOperations);
                    }
                    return;
                    final ArrayList<NativeAnimatedModule$UIThreadOperation> mOperations = this.mOperations;
                    continue;
                }
                break;
            }
        }
    }
    
    public void onHostDestroy() {
    }
    
    @Override
    public void onHostPause() {
        this.clearFrameCallback();
    }
    
    @Override
    public void onHostResume() {
        this.enqueueFrameCallback();
    }
    
    @ReactMethod
    public void removeAnimatedEventFromView(final int n, final String s) {
        this.mOperations.add(new NativeAnimatedModule$18(this, n, s));
    }
    
    @ReactMethod
    public void setAnimatedNodeOffset(final int n, final double n2) {
        this.mOperations.add(new NativeAnimatedModule$8(this, n, n2));
    }
    
    @ReactMethod
    public void setAnimatedNodeValue(final int n, final double n2) {
        this.mOperations.add(new NativeAnimatedModule$7(this, n, n2));
    }
    
    @ReactMethod
    public void startAnimatingNode(final int n, final int n2, final ReadableMap readableMap, final Callback callback) {
        this.mOperations.add(new NativeAnimatedModule$11(this, n, n2, readableMap, callback));
    }
    
    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int n) {
        this.mOperations.add(new NativeAnimatedModule$4(this, n, new NativeAnimatedModule$3(this, n)));
    }
    
    @ReactMethod
    public void stopAnimation(final int n) {
        this.mOperations.add(new NativeAnimatedModule$12(this, n));
    }
    
    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int n) {
        this.mOperations.add(new NativeAnimatedModule$5(this, n));
    }
}
