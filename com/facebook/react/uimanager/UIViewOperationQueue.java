// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.SoftAssertions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import com.facebook.react.animation.AnimationRegistry;

public class UIViewOperationQueue
{
    private final AnimationRegistry mAnimationRegistry;
    private final Object mDispatchRunnablesLock;
    private final UIViewOperationQueue$DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final ArrayList<Runnable> mDispatchUIRunnables;
    private boolean mIsDispatchUIFrameCallbackEnqueued;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;
    private ArrayDeque<UIViewOperationQueue$UIOperation> mNonBatchedOperations;
    private final Object mNonBatchedOperationsLock;
    private ArrayList<UIViewOperationQueue$UIOperation> mOperations;
    private final ReactApplicationContext mReactApplicationContext;
    private NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;
    
    public UIViewOperationQueue(final ReactApplicationContext mReactApplicationContext, final NativeViewHierarchyManager mNativeViewHierarchyManager) {
        this.mMeasureBuffer = new int[4];
        this.mDispatchRunnablesLock = new Object();
        this.mNonBatchedOperationsLock = new Object();
        this.mDispatchUIRunnables = new ArrayList<Runnable>();
        this.mOperations = new ArrayList<UIViewOperationQueue$UIOperation>();
        this.mNonBatchedOperations = new ArrayDeque<UIViewOperationQueue$UIOperation>();
        this.mIsDispatchUIFrameCallbackEnqueued = false;
        this.mNativeViewHierarchyManager = mNativeViewHierarchyManager;
        this.mAnimationRegistry = mNativeViewHierarchyManager.getAnimationRegistry();
        this.mDispatchUIFrameCallback = new UIViewOperationQueue$DispatchUIFrameCallback(this, mReactApplicationContext, null);
        this.mReactApplicationContext = mReactApplicationContext;
    }
    
    private void flushPendingBatches() {
        final Object mDispatchRunnablesLock = this.mDispatchRunnablesLock;
        // monitorenter(mDispatchRunnablesLock)
        int i = 0;
        try {
            while (i < this.mDispatchUIRunnables.size()) {
                this.mDispatchUIRunnables.get(i).run();
                ++i;
            }
            this.mDispatchUIRunnables.clear();
        }
        finally {
        }
        // monitorexit(mDispatchRunnablesLock)
    }
    
    public void addRootView(final int n, final SizeMonitoringFrameLayout sizeMonitoringFrameLayout, final ThemedReactContext themedReactContext) {
        if (UiThreadUtil.isOnUiThread()) {
            this.mNativeViewHierarchyManager.addRootView(n, sizeMonitoringFrameLayout, themedReactContext);
            return;
        }
        final Semaphore semaphore = new Semaphore(0);
        this.mReactApplicationContext.runOnUiQueueThread(new UIViewOperationQueue$1(this, n, sizeMonitoringFrameLayout, themedReactContext, semaphore));
        try {
            SoftAssertions.assertCondition(semaphore.tryAcquire(5000L, TimeUnit.MILLISECONDS), "Timed out adding root view");
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    void dispatchViewUpdates(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aload_0        
        //     3: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mOperations:Ljava/util/ArrayList;
        //     6: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //     9: ifeq            145
        //    12: aconst_null    
        //    13: astore_2       
        //    14: aload_2        
        //    15: ifnull          29
        //    18: aload_0        
        //    19: new             Ljava/util/ArrayList;
        //    22: dup            
        //    23: invokespecial   java/util/ArrayList.<init>:()V
        //    26: putfield        com/facebook/react/uimanager/UIViewOperationQueue.mOperations:Ljava/util/ArrayList;
        //    29: aload_0        
        //    30: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mNonBatchedOperationsLock:Ljava/lang/Object;
        //    33: astore          4
        //    35: aload           4
        //    37: monitorenter   
        //    38: aload_0        
        //    39: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mNonBatchedOperations:Ljava/util/ArrayDeque;
        //    42: invokevirtual   java/util/ArrayDeque.isEmpty:()Z
        //    45: ifne            76
        //    48: aload_0        
        //    49: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mNonBatchedOperations:Ljava/util/ArrayDeque;
        //    52: aload_0        
        //    53: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mNonBatchedOperations:Ljava/util/ArrayDeque;
        //    56: invokevirtual   java/util/ArrayDeque.size:()I
        //    59: anewarray       Lcom/facebook/react/uimanager/UIViewOperationQueue$UIOperation;
        //    62: invokevirtual   java/util/ArrayDeque.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //    65: checkcast       [Lcom/facebook/react/uimanager/UIViewOperationQueue$UIOperation;
        //    68: astore_3       
        //    69: aload_0        
        //    70: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mNonBatchedOperations:Ljava/util/ArrayDeque;
        //    73: invokevirtual   java/util/ArrayDeque.clear:()V
        //    76: aload           4
        //    78: monitorexit    
        //    79: aload_0        
        //    80: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mViewHierarchyUpdateDebugListener:Lcom/facebook/react/uimanager/debug/NotThreadSafeViewHierarchyUpdateDebugListener;
        //    83: ifnull          95
        //    86: aload_0        
        //    87: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mViewHierarchyUpdateDebugListener:Lcom/facebook/react/uimanager/debug/NotThreadSafeViewHierarchyUpdateDebugListener;
        //    90: invokeinterface com/facebook/react/uimanager/debug/NotThreadSafeViewHierarchyUpdateDebugListener.onViewHierarchyUpdateEnqueued:()V
        //    95: aload_0        
        //    96: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mDispatchRunnablesLock:Ljava/lang/Object;
        //    99: astore          4
        //   101: aload           4
        //   103: monitorenter   
        //   104: aload_0        
        //   105: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mDispatchUIRunnables:Ljava/util/ArrayList;
        //   108: new             Lcom/facebook/react/uimanager/UIViewOperationQueue$2;
        //   111: dup            
        //   112: aload_0        
        //   113: iload_1        
        //   114: aload_3        
        //   115: aload_2        
        //   116: invokespecial   com/facebook/react/uimanager/UIViewOperationQueue$2.<init>:(Lcom/facebook/react/uimanager/UIViewOperationQueue;I[Lcom/facebook/react/uimanager/UIViewOperationQueue$UIOperation;Ljava/util/ArrayList;)V
        //   119: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   122: pop            
        //   123: aload           4
        //   125: monitorexit    
        //   126: aload_0        
        //   127: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mIsDispatchUIFrameCallbackEnqueued:Z
        //   130: ifne            144
        //   133: new             Lcom/facebook/react/uimanager/UIViewOperationQueue$3;
        //   136: dup            
        //   137: aload_0        
        //   138: invokespecial   com/facebook/react/uimanager/UIViewOperationQueue$3.<init>:(Lcom/facebook/react/uimanager/UIViewOperationQueue;)V
        //   141: invokestatic    com/facebook/react/bridge/UiThreadUtil.runOnUiThread:(Ljava/lang/Runnable;)V
        //   144: return         
        //   145: aload_0        
        //   146: getfield        com/facebook/react/uimanager/UIViewOperationQueue.mOperations:Ljava/util/ArrayList;
        //   149: astore_2       
        //   150: goto            14
        //   153: astore_2       
        //   154: aload           4
        //   156: monitorexit    
        //   157: aload_2        
        //   158: athrow         
        //   159: astore_2       
        //   160: aload           4
        //   162: monitorexit    
        //   163: aload_2        
        //   164: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     76     153    159    Any
        //  76     79     153    159    Any
        //  104    126    159    165    Any
        //  154    157    153    159    Any
        //  160    163    159    165    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0144:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void enqueueAddAnimation(final int n, final int n2, final Callback callback) {
        this.mOperations.add(new UIViewOperationQueue$AddAnimationOperation(this, n, n2, callback, null));
    }
    
    public void enqueueClearJSResponder() {
        this.mOperations.add(new UIViewOperationQueue$ChangeJSResponderOperation(this, 0, 0, true, false));
    }
    
    public void enqueueConfigureLayoutAnimation(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        this.mOperations.add(new UIViewOperationQueue$ConfigureLayoutAnimationOperation(this, readableMap, null));
    }
    
    public void enqueueCreateView(final ThemedReactContext themedReactContext, final int n, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        synchronized (this.mNonBatchedOperationsLock) {
            this.mNonBatchedOperations.addLast(new UIViewOperationQueue$CreateViewOperation(this, themedReactContext, n, s, reactStylesDiffMap));
        }
    }
    
    public void enqueueDispatchCommand(final int n, final int n2, final ReadableArray readableArray) {
        this.mOperations.add(new UIViewOperationQueue$DispatchCommandOperation(this, n, n2, readableArray));
    }
    
    public void enqueueFindTargetForTouch(final int n, final float n2, final float n3, final Callback callback) {
        this.mOperations.add(new UIViewOperationQueue$FindTargetForTouchOperation(this, n, n2, n3, callback, null));
    }
    
    public void enqueueManageChildren(final int n, final int[] array, final ViewAtIndex[] array2, final int[] array3) {
        this.mOperations.add(new UIViewOperationQueue$ManageChildrenOperation(this, n, array, array2, array3));
    }
    
    public void enqueueMeasure(final int n, final Callback callback) {
        this.mOperations.add(new UIViewOperationQueue$MeasureOperation(this, n, callback, null));
    }
    
    public void enqueueMeasureInWindow(final int n, final Callback callback) {
        this.mOperations.add(new UIViewOperationQueue$MeasureInWindowOperation(this, n, callback, null));
    }
    
    public void enqueueRegisterAnimation(final Animation animation) {
        this.mOperations.add(new UIViewOperationQueue$RegisterAnimationOperation(this, animation, null));
    }
    
    public void enqueueRemoveAnimation(final int n) {
        this.mOperations.add(new UIViewOperationQueue$RemoveAnimationOperation(this, n, null));
    }
    
    public void enqueueRemoveRootView(final int n) {
        this.mOperations.add(new UIViewOperationQueue$RemoveRootViewOperation(this, n));
    }
    
    public void enqueueSendAccessibilityEvent(final int n, final int n2) {
        this.mOperations.add(new UIViewOperationQueue$SendAccessibilityEvent(this, n, n2, null));
    }
    
    public void enqueueSetJSResponder(final int n, final int n2, final boolean b) {
        this.mOperations.add(new UIViewOperationQueue$ChangeJSResponderOperation(this, n, n2, false, b));
    }
    
    public void enqueueSetLayoutAnimationEnabled(final boolean b) {
        this.mOperations.add(new UIViewOperationQueue$SetLayoutAnimationEnabledOperation(this, b, null));
    }
    
    public void enqueueShowPopupMenu(final int n, final ReadableArray readableArray, final Callback callback, final Callback callback2) {
        this.mOperations.add(new UIViewOperationQueue$ShowPopupMenuOperation(this, n, readableArray, callback2));
    }
    
    public void enqueueUIBlock(final UIBlock uiBlock) {
        this.mOperations.add(new UIViewOperationQueue$UIBlockOperation(this, uiBlock));
    }
    
    public void enqueueUpdateExtraData(final int n, final Object o) {
        this.mOperations.add(new UIViewOperationQueue$UpdateViewExtraData(this, n, o));
    }
    
    public void enqueueUpdateLayout(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.mOperations.add(new UIViewOperationQueue$UpdateLayoutOperation(this, n, n2, n3, n4, n5, n6));
    }
    
    public void enqueueUpdateProperties(final int n, final String s, final ReactStylesDiffMap reactStylesDiffMap) {
        this.mOperations.add(new UIViewOperationQueue$UpdatePropertiesOperation(this, n, reactStylesDiffMap, null));
    }
    
    NativeViewHierarchyManager getNativeViewHierarchyManager() {
        return this.mNativeViewHierarchyManager;
    }
    
    public boolean isEmpty() {
        return this.mOperations.isEmpty();
    }
    
    void pauseFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = false;
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer$CallbackType.DISPATCH_UI, (Choreographer$FrameCallback)this.mDispatchUIFrameCallback);
        this.flushPendingBatches();
    }
    
    void resumeFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer$CallbackType.DISPATCH_UI, (Choreographer$FrameCallback)this.mDispatchUIFrameCallback);
    }
    
    public void setViewHierarchyUpdateDebugListener(final NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener) {
        this.mViewHierarchyUpdateDebugListener = mViewHierarchyUpdateDebugListener;
    }
}
